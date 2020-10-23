import Axios from "axios";
import setJWTToken from "./securityUtils/setJWTToken";
import jwt_decode from "jwt-decode";

//POSSIBLE ACTIONS

export const ACTION_openCommunity = "ACTION_openCommunity";
export const ACTION_openUser = "ACTION_openUser";
export const ACTION_openArticle = "ACTION_openArticle";
export const ACTION_logIn = "ACTION_logIn";
export const ACTION_logOut = "ACTION_logOut";
export const ACTION_register = "ACTION_register";
export const ACTION_createCommunity = "ACTION_createCommunity";
export const ACTION_createArticle = "ACTION_createArticle";
export const ACTION_init = "ACTION_init";
export const ACTION_joinCommunity = "ACTION_joinCommunity";
export const ACTION_addBookmark = "ACTION_addBookmark";
export const ACTION_openCreateArticleTab = "ACTION_openCreateArticleTab";
export const ACTION_removeOpenTabById = "ACTION_removeOpenTabById";
export const ACTION_addCommunityToLoggedInUser =
  "ACTION_addCommunityToLoggedInUser";

export const ACTION_getSpotlightContent = "ACTION_getSpotlightContent";

//ACTION CREATORS

/*
gets featured content for front page carousels.
 */
export function DISPATCH_getSpotlightContent() {
  return (dispatch) => {
    Axios.get("/getSpotlight").then((res) => {
      dispatch({
        type: ACTION_getSpotlightContent,
        data: res.data,
      });
    });
  };
}

export async function DISPATCH_getSpotlightContentASYNC() {
  let res = await Axios.get("/getSpotlight");

  return (dispatch) => {
    dispatch({
      type: ACTION_getSpotlightContent,
      data: res.data,
    });
  };
}

export function DISPATCH_removeOpenTabById(tabId) {
  return (dispatch) => {
    dispatch({
      type: ACTION_removeOpenTabById,
      id: tabId,
    });
  };
}

export function DISPATCH_openCreateArticleTab(communityId) {
  return (dispatch) => {
    dispatch({
      type: ACTION_openCreateArticleTab,
      id: communityId,
    });
  };
}

export function DISPATCH_addBookmark(userId, articleId, name) {
  return (dispatch) => {
    Axios.post("/addBookmark", {
      userId: userId,
      articleId: articleId,
    }).then((res) => {
      dispatch({
        type: ACTION_addBookmark,
        name: name,
      });
    });
  };
}

export function DISPATCH_addBookmarkASYNC(userId, articleId, name) {
  let res = Axios.post("/addBookmark", {
    userId: userId,
    articleId: articleId,
  });

  return (dispatch) => {
    dispatch({
      type: ACTION_addBookmark,
      name: name,
    });
  };
}

export function DISPATCH_createArticle(payload, user, community, sectionData) {
  return (dispatch) => {
    Axios.post("/createArticle", {
      articleName: payload.articleName,
      description: payload.articleDescription,
      //userID: user,
      communityID: community,
      articleSectionDOList: sectionData,
    }).then((res) => {
      dispatch({
        type: ACTION_openArticle,
        payload: res.data,
      });

      // dispatch({
      //   type: ACTION_addCommunityToLoggedInUser,
      //   id: res.data._id,
      // });
    });
  };
}

export function DISPATCH_joinCommunity(payload) {
  return (dispatch) => {
    Axios.post("/joinCommunity", {
      userId: payload.userId,
      communityId: payload.communityId,
    }).then((res) => {
      console.log("JOIN COMMUNITY LOG");
      console.log(res);

      dispatch({
        type: ACTION_addCommunityToLoggedInUser,
        data: payload.communityId,
      });
    });
  };
}

export function DISPATCH_init() {
  return (dispatch) => {
    Axios.get("/getHomeStreams").then((res) => {
      let base = res.data._embedded.collectionModels;

      let articles = [];
      let profiles = [];
      let communities = [];

      try {
        articles = base[0]._embedded.articleInfoComponentCoes;
      } catch {
        articles = [];
      }

      try {
        profiles = base[1]._embedded.profileInfoComponentCoes;
      } catch {
        profiles = [];
      }

      try {
        communities = base[2]._embedded.communityInfoComponentCoes;
      } catch {
        communities = [];
      }

      dispatch({
        type: ACTION_init,
        payload: {
          articles: articles,
          users: profiles,
          communities: communities,
        },
      });
    });
  };
}

export function DISPATCH_openCommunity(com_url) {
  return (dispatch, getState) => {
    Axios.get(com_url).then((res) => {
      // Axios.get("https" + com_url.slice(4, com_url.length)).then((res) => {
      console.log(res);

      let articles = [];

      try {
        articles =
          res.data.articleInfoComponentCOS._embedded.articleInfoComponentCoes;
        if (articles == null) articles = [];
      } catch {
        articles = [];
      }

      getState().tabs.open.find((x) => {
        return x.id === res.data._id;
      })
        ? $("#tabA" + res.data._id).tab("show")
        : dispatch({
            type: ACTION_openCommunity,
            payload: res.data,
            articles: articles,
          });
    });
  };
}

export function DISPATCH_openUser(url) {
  //USING getstate
  console.log(url);
  return (dispatch, getState) => {
    Axios.get(url).then((res) => {
      console.log("data");
      console.log(res.data);

      let articles = [];
      let profiles = [];
      let communities = [];

      let base = res.data;

      try {
        articles =
          base.articleInfoComponentCOS._embedded.articleInfoComponentCoes;
        if (articles === undefined) articles = [];
      } catch {
        articles = [];
      }

      try {
        profiles = base.profileInfoComponentCoes._embedded;
        if (profiles === undefined) profiles = [];
      } catch {
        profiles = [];
      }

      try {
        communities =
          base.communityInfoComponentCOS._embedded.communityInfoComponentCoes;
        if (communities === undefined) communities = [];
      } catch {
        communities = [];
      }

      //check if already open
      getState().tabs.open.find((x) => {
        return x.id === res.data._id;
      })
        ? $("#tabA" + res.data._id).tab("show")
        : dispatch({
            type: ACTION_openUser,
            data: res.data,

            articles: articles,
            profiles: profiles,
            communities: communities,
            //payload: res.data,
          });
    });
  };
}

export function DISPATCH_openArticle(url) {
  console.log("open article");
  console.log(url);

  return (dispatch, getState) => {
    Axios.get(url).then((res) => {
      //check if already open
      getState().tabs.open.find((x) => {
        return x.id === res.data._id;
      })
        ? $("#tabA" + res.data._id).tab("show")
        : dispatch({
            type: ACTION_openArticle,
            payload: res.data,
          });
    });
  };
}

export function DISPATCH_logIn(payload) {
  return (dispatch, getState) => {
    Axios.post("/login", {
      password: payload.password,
      username: payload.username,
    })
      .then((res) => {
        //TODO will still open a second tab if not logged on first, and then logs on
        // getState().tabs.open.find((x) => {
        //   return (x.id === res.data._id)
        // }) && getState().user.logged_in ? $("#tabA"+res.data._id).tab("show") :

        //annoying HATEOS COLLECTIONMODEL logic

        //TODO get auth token etc
        const { token } = res.data;
        localStorage.setItem("jwtToken", token);
        setJWTToken(token);
        const decoded = jwt_decode(token);
        console.log("decoded");
        console.log(decoded);

        return decoded;
        //TODO set current user using decoded token??

        //ALSO NEEDS TO RETURN AN URL

        //DISPATCH_openUser("/getProfileTabComponentCO/" + decoded.id);
        //
        // let articles = jQuery.isEmptyObject(res.data.articleHomePageCOList)
        //   ? []
        //   : res.data.articleHomePageCOList._embedded.articleStreamComponentCoes;
        //
        // let communities = jQuery.isEmptyObject(
        //   res.data.communityStreamComponentCOList
        // )
        //   ? []
        //   : res.data.communityStreamComponentCOList._embedded
        //       .communityStreamComponentCoes;
        //
        // let bookmarks = jQuery.isEmptyObject(res.data.articleBookmarksCM)
        //   ? []
        //   : res.data.articleBookmarksCM._embedded.articleStreamComponentCoes;
        //
        // //TODO change to openuser
        // dispatch({
        //   type: ACTION_logIn,
        //   payload: res.data,
        //   articles: articles,
        //   communities: communities,
        //   bookmarks: bookmarks,
        // });
      })
      .then((decoded) => {
        Axios.get("/getProfileTabComponentCO/" + decoded.id).then(
          (secondRes) => {
            console.log("data");
            console.log(secondRes.data);

            let articles = [];
            let profiles = [];
            let communities = [];

            let base = secondRes.data;

            try {
              articles =
                base.articleInfoComponentCOS._embedded.articleInfoComponentCoes;
              if (articles === undefined) articles = [];
            } catch {
              articles = [];
            }

            try {
              profiles = base.profileInfoComponentCOS._embedded;
              if (profiles === undefined) profiles = [];
            } catch {
              profiles = [];
            }

            try {
              communities =
                base.communityInfoComponentCOS._embedded
                  .communityInfoComponentCoes;
              if (communities === undefined) communities = [];
            } catch {
              communities = [];
            }

            dispatch({
              type: ACTION_logIn,
              payload: secondRes.data,
              articles: articles,
              communities: communities,
              //bookmarks: bookmarks,
            });
          }
        );
      });
  };
}

//TODO working on changing tab when created
// function activaTab(someTab){
//     console.log("ACTIVATAB")
//     console.log(someTab)
//     $(someTab).tab('show')
//     return null
//     // $('.nav-tabs a[href="#' + tab + '"]').tab('show');
// };

export function DISPATCH_logOut() {
  $("#home-tab").tab("show");

  return {
    type: ACTION_logOut,
  };
}

export function DISPATCH_register(payload) {
  //TODO also needs to dispatch login after register to get token
  //NEED global Axios

  return (dispatch) => {
    Axios.post("/register", {
      password: payload.password,
      username: payload.username,
    }).then((res) => {
      console.log("register data");
      console.log(res.data);

      dispatch({
        type: ACTION_register,
        payload: res.data,
      });
      dispatch({
        type: ACTION_openUser,
        payload: res.data,
        data: res.data,
      });
    });
  };
}

export function DISPATCH_createCommunity(payload) {
  return (dispatch, getState) => {
    Axios.post("/createCommunity", {
      name: payload.name,
    }).then((res) => {
      dispatch({
        type: ACTION_openCommunity,
        payload: res.data,
      });

      dispatch({
        type: ACTION_addCommunityToLoggedInUser,
        data: res.data._id,
      });
    });
  };
}
