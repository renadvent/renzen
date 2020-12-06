import Axios from "axios";
import {
  ACTION_addCommunityToLoggedInUser,
  ACTION_openCommunity,
} from "./StoreDefs";
import { reloadHomePage, select } from "./MiscellaneousActions";
import { reloadLoggedInUser } from "./UserActions";

export function DISPATCH_joinCommunity(payload) {
  return async (dispatch) => {
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
    await reloadLoggedInUser();
  };
}

export function DISPATCH_openCommunity(com_url) {
  return async (dispatch, getState) => {
    let res = await Axios.get(com_url);
    console.log(res);

    let articles = [];

    try {
      articles =
        res.data.articleInfoComponentCOS._embedded.articleInfoComponentCoes;
      if (articles == null) articles = [];
    } catch {
      articles = [];
    }

    if (
      !getState().reducer.tabs.open.find((x) => {
        return x.id === res.data._id;
      })
    ) {
      await dispatch({
        type: ACTION_openCommunity,
        payload: res.data,
        articles: articles,
      });
    }

    await select(dispatch, res.data._id);

    document.documentElement.scrollTop = 0;

    // await dispatch({
    //   type: "selectTab",
    //   id: res.data._id,
    // });
  };
}

export function DISPATCH_createCommunity(payload) {
  return async (dispatch, getState) => {
    let res = await Axios.post("/createCommunity", {
      name: payload.name,
    });
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

    dispatch({
      type: ACTION_openCommunity,
      payload: res.data,
      articles: articles,
    });

    dispatch({
      type: ACTION_addCommunityToLoggedInUser,
      data: res.data._id,
    });

    await reloadLoggedInUser();
    await reloadHomePage(dispatch);
  };
}
