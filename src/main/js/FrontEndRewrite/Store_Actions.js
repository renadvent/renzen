import Axios from "axios";

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

//ACTION CREATORS

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

export function DISPATCH_createArticle(payload, user, community, sectionData) {
  return (dispatch) => {
    Axios.post("/createArticle", {
      name: payload.articleName,
      description: payload.articleDescription,
      userID: user,
      communityID: community,
      articleSectionDOList: sectionData,
    }).then((res) => {
      dispatch({
        type: ACTION_openArticle,
        payload: res.data,
      });
    });
  };
}

export function DISPATCH_joinCommunity(payload) {
  return (dispatch) => {
    Axios.post("/joinCommunity", {
      userId: payload.userId,
      communityId: payload.communityId,
    }).then((res) => {
      dispatch({
        type: ACTION_joinCommunity,
        payload: res.data,
      });
    });
  };
}

export function DISPATCH_init() {
  return (dispatch) => {
    Axios.get("/getHomeStreams").then((res) => {
      let base = res.data._embedded.collectionModels;

      // console.log(base[0]);

      let articles = !Object.keys(base[0]).length
        ? null
        : base[0]._embedded.articleStreamComponentCoes;
      let profiles = !Object.keys(base[1]).length
        ? null
        : base[1]._embedded.profileStreamComponentCoes;
      let communities = !Object.keys(base[2]).length
        ? null
        : base[2]._embedded.communityStreamComponentCoes;

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
      getState().tabs.open.find((x) => {
        return x.id === res.data._id;
      })
        ? $("#tabA" + res.data._id).tab("show")
        : dispatch({
            type: ACTION_openCommunity,
            payload: res.data,
          });
    });
  };
}

export function DISPATCH_openUser(url) {
  //USING getstate
  return (dispatch, getState) => {
    Axios.get(url).then((res) => {
      // console.log("PAYLOAD");
      // console.log(res.data);

      //check if already open
      getState().tabs.open.find((x) => {
        return x.id === res.data._id;
      })
        ? $("#tabA" + res.data._id).tab("show")
        : dispatch({
            type: ACTION_openUser,
            payload: res.data,
          });
    });
  };
}

export function DISPATCH_openArticle(url) {
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
    }).then((res) => {
      //TODO will still open a second tab if not logged on first, and then logs on
      // getState().tabs.open.find((x) => {
      //   return (x.id === res.data._id)
      // }) && getState().user.logged_in ? $("#tabA"+res.data._id).tab("show") :

      // console.log("I AM WORKING ON HERE");
      // console.log(res.data);
      let base = res.data;

      // console.log(base[0]);

      //annoying HATEOS COLLECTIONMODEL logic

      let articles = jQuery.isEmptyObject(res.data.articleHomePageCOList)
        ? []
        : res.data.articleHomePageCOList._embedded.articleStreamComponentCoes;

      let communities = jQuery.isEmptyObject(
        res.data.communityStreamComponentCOList
      )
        ? []
        : res.data.communityStreamComponentCOList._embedded
            .communityStreamComponentCoes;

      let bookmarks = jQuery.isEmptyObject(res.data.articleBookmarksCM)
        ? []
        : res.data.articleBookmarksCM._embedded.articleStreamComponentCoes;

      dispatch({
        type: ACTION_logIn,
        payload: res.data,
        articles: articles,
        // users: profiles,
        communities: communities,
        bookmarks: bookmarks,
      });
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
  return (dispatch) => {
    Axios.post("/register", {
      password: payload.password,
      username: payload.username,
    }).then((res) => {
      dispatch({
        type: ACTION_register,
        payload: res.data,
      });
      dispatch({
        type: ACTION_openUser,
        payload: res.data,
      });
    });
  };
}

export function DISPATCH_createCommunity(payload) {
  return (dispatch, getState) => {
    Axios.post("/createCommunity", {
      name: payload.name,
      creatorID: payload.creatorID,
    }).then((res) => {
      dispatch({
        type: ACTION_openCommunity,
        payload: res.data,
      });
    });
  };
}
