import Axios from "axios";
import {
  ACTION_addBookmark,
  ACTION_init,
  ACTION_logIn,
  ACTION_removeOpenTabById,
} from "./StoreDefs";
import setJWTToken from "../securityUtils/setJWTToken";
import jwt_decode from "jwt-decode";
import { getVarsFromResponse } from "./UserActions";

export function DISPATCH_removeOpenTabById(tabId) {
  return (dispatch) => {
    dispatch({
      type: ACTION_removeOpenTabById,
      id: tabId,
    });
  };
}

export function DISPATCH_addBookmark(userId, articleId, name) {
  return (dispatch) => {
    Axios.post("/addBookmark", {
      //userId: userId,
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

export function DISPATCH_init() {
  console.log("initing");

  return (dispatch, getState) => {
    Axios.get("/getHomeStreams").then(async (res) => {
      let base = res.data._embedded.collectionModels;

      let init = getInitFromEmbedded(base);

      //await
      await dispatch({
        type: ACTION_init,
        payload: {
          articles: init.articles,
          users: init.profiles,
          communities: init.communities,
        },
      });

      let token = localStorage.getItem("jwtToken");

      console.log(token);

      if (token != null && getState().reducer.user.logged_in === false) {
        //let token = localStorage.getItem("jwtToken");
        setJWTToken(token);
        const decoded = jwt_decode(token);

        let res = await Axios.get("/getProfileTabComponentCO/" + decoded.id);
        let base = res.data;
        let vars = getVarsFromResponse(base);

        return dispatch({
          type: ACTION_logIn,
          payload: res.data,
          articles: vars.articles,
          communities: vars.communities,
          bookmarks: vars.bookmarks,
        });
      }
    });
  };
}

function getInitFromEmbedded(base) {
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

  return {
    articles: articles,
    profiles: profiles,
    communities: communities,
  };
}
