import Axios from "axios";
import {
  ACTION_dislikeArticle,
  ACTION_likeArticle,
  ACTION_openArticle,
  ACTION_openCreateArticleTab,
  GET_ERRORS,
  ACTION_addComment,
  ACTION_replaceArticle,
} from "./StoreDefs";
import {
  DISPATCH_init,
  LoginCheck,
  reloadHomePage,
  select,
} from "./MiscellaneousActions";

export function DISPATCH_replacePost(originalID, currentID, replacementID) {
  return async (dispatch, getState) => {
    console.log("REPLACMENT" + replacementID);

    let res = await Axios.get("/getArticleStreamComponentCO/" + replacementID);

    console.log("got replacement");

    await dispatch({
      type: ACTION_replaceArticle,
      payload: res,
      uuid: originalID,
      // originalID: originalID,
      currentID: currentID,
      replacementID: replacementID,
    });
  };
}

export function DISPATCH_addComment(id, comment) {
  console.log(comment);
  return async (dispatch, getState) => {
    if (!LoginCheck(getState)) return;

    console.log(comment);
    try {
      let res = await Axios.post("/addComment/" + id, {
        comment: comment,
      });

      await DISPATCH_reloadArticleById(id, dispatch, getState);

      //TODO add dispatch to reload comments
    } catch (error) {
      dispatch({
        type: GET_ERRORS,
        error: error,
      });
    }
  };
}

export async function DISPATCH_reloadArticleById(id, dispatch, getState) {
  try {
    let streamRes = await Axios.get("/getArticleStreamComponentCO/" + id);
    let tabRes = await Axios.get("/getArticleTabComponentCO/" + id);

    await dispatch({
      type: "reload",
      payload: streamRes,
    });

    await dispatch({
      type: "reloadTab",
      payload: tabRes,
    });
  } catch (error) {
    dispatch({
      type: GET_ERRORS,
      error: error,
    });
  }

  //TODO update article tabs

  // try{
  //   let res = await Axios.get("/getArticleTabComponentCO/"+id)
  // }
}

export function DISPATCH_likeArticle(id) {
  return async (dispatch, getState) => {
    if (!LoginCheck(getState)) return;

    try {
      let res = await Axios.post("/likeArticle/" + id);

      await dispatch({
        type: ACTION_likeArticle,
        likes: res.data,
        id: id,
      });

      console.log("about to reaload");

      //TODO Not working for tab component
      await DISPATCH_reloadArticleById(id, dispatch, getState);
    } catch (error) {
      dispatch({
        type: GET_ERRORS,
        error: error,
      });
    }
  };
}

export function DISPATCH_dislikeArticle(id) {
  return async (dispatch, getState) => {
    if (!LoginCheck(getState)) return;

    try {
      let res = await Axios.post("/dislikeArticle/" + id);
      dispatch({
        type: ACTION_dislikeArticle,
        dislikes: res.data,
        id: id,
      });

      await DISPATCH_reloadArticleById(id, dispatch, getState);
    } catch (error) {
      dispatch({
        type: GET_ERRORS,
        error: error,
      });
    }
  };
}

export function DISPATCH_openArticle(url) {
  console.log("open article");
  console.log(url);

  return async (dispatch, getState) => {
    let res = await Axios.get(url); //.then((res) => {
    //check if already open

    if (
      !getState().reducer.tabs.open.find((x) => {
        return x.id === res.data._id;
      })
    ) {
      await dispatch({
        type: ACTION_openArticle,
        payload: res.data,
      });
    }

    await select(dispatch, res.data._id);

    // await dispatch({
    //   type: "selectTab",
    //   id: res.data._id,
    // });
  };
}

export function DISPATCH_createArticle(payload, user, community, sectionData) {
  return async (dispatch, getState) => {
    let res = await Axios.post("/createArticle", {
      articleName: payload.articleName,
      description: payload.articleDescription,
      workName: payload.workName,
      tags: payload.tags,
      pollOptions: payload.pollOptions,

      image: OpenFromInkLink,

      //userID: user,
      // communityID: community,
      communityID: payload.community,
      articleSectionDOList: sectionData,
    });
    dispatch({
      type: ACTION_openArticle,
      payload: res.data,
    });

    await reloadHomePage(dispatch, getState);

    await select(dispatch, res.data._id);
  };
}

export function DISPATCH_openCreateArticleTab(communityId) {
  return async (dispatch) => {
    await dispatch({
      type: ACTION_openCreateArticleTab,
      id: communityId,
    });

    //await select(dispatch, communityId); //???
  };
}
