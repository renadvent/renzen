import Axios from "axios";
import {
  ACTION_openArticle,
  ACTION_openCreateArticleTab,
  GET_ERRORS,
} from "./StoreDefs";

export function DISPATCH_likeArticle(id) {
  return async (dispatch) => {
    try {
      await Axios.post("/likeArticle/" + id);
      dispatch({
        type: "do nothing",
      });
    } catch (error) {
      dispatch({
        type: GET_ERRORS,
        error: error,
        payload: error.response.data,
      });
    }
  };
}

export function DISPATCH_dislikeArticle(id) {
  return async (dispatch) => {
    try {
      await Axios.post("/dislikeArticle/" + id);
    } catch (error) {
      dispatch({
        type: GET_ERRORS,
        error: error,
        payload: error.response.data,
      });
    }
  };
}

export function DISPATCH_openArticle(url) {
  console.log("open article");
  console.log(url);

  return (dispatch, getState) => {
    Axios.get(url).then((res) => {
      //check if already open
      getState().reducer.tabs.open.find((x) => {
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

export function DISPATCH_openCreateArticleTab(communityId) {
  return (dispatch) => {
    dispatch({
      type: ACTION_openCreateArticleTab,
      id: communityId,
    });
  };
}
