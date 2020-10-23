import Axios from "axios";
import {
  ACTION_addBookmark,
  ACTION_init,
  ACTION_removeOpenTabById,
} from "./StoreDefs";

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
