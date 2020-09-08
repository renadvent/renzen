import Axios from "axios";

export const ACTION_openCommunity = "ACTION_openCommunity";
export const ACTION_openUser = "ACTION_openUser";
export const ACTION_openArticle = "ACTION_openArticle";
export const ACTION_logIn = "ACTION_logIn";
export const ACTION_logOut = "ACTION_logOut";
export const ACTION_register = "ACTION_register";
export const ACTION_createCommunity = "ACTION_createCommunity";

export const ACTION_createArticle = "ACTION_createArticle";
export const ACTION_createPost = "ACTION_createPost";
export const ACTION_createReply = "ACTION_createReply";

export const ACTION_init = "ACTION_init";

export const ACTION_joinCommunity = "ACTION_joinCommunity";

//ACTION CREATORS

export function DISPATCH_createArticle(payload, user, community, sectionData) {
  return (dispatch) => {
    //code here
    console.log("creating article");
    console.log(payload);
    console.log(user);
    console.log(community);
    console.log(sectionData);

    Axios.post("/createArticle", {
      //payload
      name: payload.articleName,
      description: payload.articleDescription,
      userID: user,
      communityID: community,
      articleSectionDOList: sectionData, //[]//payload.articleAddToSection
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
    //code here
    console.log("joining community");

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
      console.log(res);
      console.log(res.data._embedded.collectionModels[1]._embedded);

      console.log("UNDER");

      let base = res.data._embedded.collectionModels;

      console.log(base[0]);
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
      console.log("PAYLOAD");
      console.log(res.data);

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

      dispatch({
        type: ACTION_logIn,
        payload: res.data,
      });
      // .then(()=>{
      //     $('#app-tabs li:last-child a').tab('show')
      // })
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
    });
  };
}

export function DISPATCH_createCommunity(payload) {
  return (dispatch, getState) => {
    Axios.post("/createCommunity", {
      name: payload.name,
      creatorID: payload.creatorID,
      // params: {
      //     user: user
      // },
      // data: {
      //     name: payload.name,
      //     description: payload.description
      // }
    }).then((res) => {
      dispatch({
        type: ACTION_openCommunity,
        payload: res.data,
        // type: ACTION_createCommunity,
        // payload: res.data
      });
      // }).then(
      //     DISPATCH_openCommunity(res.data._links["Tab_Version"])
      //
      // )
    });
  };
}

//COMMUNITY-PAGE ACTION DISPATCHES
//
// export function DISPATCH_createArticle() {
//
// }

export function DISPATCH_createPost() {}

export function DISPATCH_createReply() {}
