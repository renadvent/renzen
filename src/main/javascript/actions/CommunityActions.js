import Axios from "axios";
import {
  ACTION_addCommunityToLoggedInUser,
  ACTION_openCommunity,
} from "./StoreDefs";

export function DISPATCH_joinCommunity(payload) {
  return (dispatch) => {
    Axios.post("/joinCommunity", {
      //userId: payload.userId,
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

      getState().reducer.tabs.open.find((x) => {
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

export function DISPATCH_createCommunity(payload) {
  return (dispatch, getState) => {
    Axios.post("/createCommunity", {
      name: payload.name,
    }).then((res) => {
      console.log("create com res");
      console.log(res.data);

      let articles = [];

      try {
        articles =
          res.data.articleInfoComponentCOS._embedded.articleInfoComponentCoes;
        if (articles == null) articles = [];
      } catch {
        articles = [];
      }

      //if ((res.data.articleInfoComponentCOS = {}))
      dispatch({
        type: ACTION_openCommunity,
        payload: res.data,
        articles: articles,
      });

      dispatch({
        type: ACTION_addCommunityToLoggedInUser,
        data: res.data._id,
      });
    });
  };
}
