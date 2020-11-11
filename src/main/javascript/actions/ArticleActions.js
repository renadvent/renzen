import Axios from "axios";
import {
  ACTION_dislikeArticle,
  ACTION_likeArticle,
  ACTION_openArticle,
  ACTION_openCreateArticleTab,
  GET_ERRORS,
  ACTION_addComment,
} from "./StoreDefs";
import { DISPATCH_init } from "./MiscellaneousActions";

export function DISPATCH_addComment(id, comment) {
  console.log(comment);
  return async (dispatch) => {
    console.log(comment);
    try {
      let res = await Axios.post("/addComment/" + id, {
        comment: comment,
      });

      await DISPATCH_reloadArticleById(id, dispatch);

      //TODO add dispatch to reload comments
    } catch (error) {
      dispatch({
        type: GET_ERRORS,
        error: error,
        //payload: error.response.data,
      });
    }
  };
}

export async function DISPATCH_reloadArticleById(id, dispatch) {
  // return async (dispatch) => {
  try {
    let res = await Axios.get("/getArticleStreamComponentCO/" + id);

    // console.log("res" + res);

    dispatch({
      type: "reload",
      payload: res,
    });
  } catch (error) {
    dispatch({
      type: GET_ERRORS,
      error: error,
      //payload: error.response.data,
    });
  }
  // };
}

export function DISPATCH_likeArticle(id) {
  return async (dispatch) => {
    try {
      let res = await Axios.post("/likeArticle/" + id);
      // console.log(res.data);

      await dispatch({
        type: ACTION_likeArticle,
        likes: res.data,
        id: id,
      });

      console.log("about to reaload");
      await DISPATCH_reloadArticleById(id, dispatch);

      // try {
      //   let res = await Axios.get("/getArticleStreamComponentCO/" + id);
      //
      //   console.log("res" + res);
      //
      //   dispatch({
      //     type: "reload",
      //     payload: res,
      //   });
      // } catch (error) {
      //   dispatch({
      //     type: GET_ERRORS,
      //     error: error,
      //     //payload: error.response.data,
      //   });
      // }

      console.log("done");
    } catch (error) {
      dispatch({
        type: GET_ERRORS,
        error: error,
        //payload: error.response.data,
      });
    }
  };
}

export function DISPATCH_dislikeArticle(id) {
  return async (dispatch) => {
    try {
      let res = await Axios.post("/dislikeArticle/" + id);
      dispatch({
        type: ACTION_dislikeArticle,
        dislikes: res.data,
        id: id,
      });

      await DISPATCH_reloadArticleById(id, dispatch);
    } catch (error) {
      dispatch({
        type: GET_ERRORS,
        error: error,
        //payload: error.response.data,
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
      workName: payload.workName,
      tags: payload.tags,
      pollOptions: payload.pollOptions,

      image: OpenFromInkLink,

      //userID: user,
      communityID: community,
      articleSectionDOList: sectionData,
    }).then((res) => {
      dispatch({
        type: ACTION_openArticle,
        payload: res.data,
      });

      // DISPATCH_init();

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
