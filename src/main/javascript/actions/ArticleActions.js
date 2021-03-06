import Axios from "axios";
import {
  ACTION_dislikeArticle,
  ACTION_likeArticle,
  ACTION_openArticle,
  ACTION_openCreateArticleTab,
  GET_ERRORS,
  ACTION_addComment,
  ACTION_replaceArticle,
  ACTION_editArticle,
} from "./StoreDefs";
import {
  DISPATCH_init,
  DISPATCH_removeOpenTabById,
  LoginCheck,
  reloadHomePage,
  select,
} from "./MiscellaneousActions";

import {
  ArticleTabComponentCO,
  UpdateArticlePayload,
} from "../classes/classes";
import { reloadLoggedInUser } from "./UserActions";

//TODO wont work until changes for CO
export function DISPATCH_deleteImageFromProfile(link) {
  return async (dispatch, getState) => {
    alert("trying to delete" + link);
    let res = await Axios.post("/deleteImageFromProfile/" + link);

    await dispatch({
      type: "deleteFromProfile",
      payload: res,
    });
  };
}

export function DISPATCH_deletePost(id) {
  return async (dispatch, getState) => {
    // alert("DELETING DISPATCHED");

    let res = await Axios.post("/deleteArticle/" + id);

    await dispatch({
      type: "deletePost",
      payload: res,
    });

    await reloadLoggedInUser(dispatch);
    await reloadHomePage(dispatch, getState);

    alert("Deleted");
  };
}

export function DISPATCH_replacePost(originalID, currentID, replacementID) {
  return async (dispatch, getState) => {
    // console.log("REPLACMENT" + replacementID);

    let res = await Axios.get("/getArticleStreamComponentCO/" + replacementID);

    // console.log("got replacement");

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

export function DISPATCH_addComment(id, comment, uuid) {
  console.log(comment);
  return async (dispatch, getState) => {
    if (!LoginCheck(getState)) return;

    console.log(comment);
    try {
      let res = await Axios.post("/addComment/" + id, {
        comment: comment,
      });

      console.log("UUID " + uuid);

      await DISPATCH_reloadArticleById(id, dispatch, getState, uuid);

      //TODO add dispatch to reload comments
    } catch (error) {
      dispatch({
        type: GET_ERRORS,
        error: error,
      });
    }
  };
}

export async function DISPATCH_reloadArticleById(id, dispatch, getState, uuid) {
  try {
    let streamRes = await Axios.get("/getArticleStreamComponentCO/" + id);
    let tabRes = await Axios.get("/getArticleTabComponentCO/" + id);

    // console.log("dipatch " + uuid);

    //stream reloads
    if (uuid !== undefined) {
      await dispatch({
        type: ACTION_replaceArticle,
        payload: streamRes,
        uuid: uuid,
      });
    } else {
      await dispatch({
        type: "reload",
        payload: streamRes,
      });
    }

    //tab reloads
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
}

export function DISPATCH_likeArticle(id, uuid) {
  return async (dispatch, getState) => {
    if (!LoginCheck(getState)) return;

    try {
      let res = await Axios.post("/likeArticle/" + id);

      console.log("about to reaload");

      //TODO Not working for tab component
      await DISPATCH_reloadArticleById(id, dispatch, getState, uuid);
    } catch (error) {
      dispatch({
        type: GET_ERRORS,
        error: error,
      });
    }
  };
}

export function DISPATCH_dislikeArticle(id, uuid) {
  return async (dispatch, getState) => {
    if (!LoginCheck(getState)) return;

    try {
      let res = await Axios.post("/dislikeArticle/" + id);
      // dispatch({
      //   type: ACTION_dislikeArticle,
      //   dislikes: res.data,
      //   id: id,
      // });

      await DISPATCH_reloadArticleById(id, dispatch, getState, uuid);
    } catch (error) {
      dispatch({
        type: GET_ERRORS,
        error: error,
      });
    }
  };
}

export function DISPATCH_openArticle(url) {
  // console.log("open article");
  // console.log(url);

  return async (dispatch, getState) => {
    let res = await Axios.get(url);
    let article = new ArticleTabComponentCO(res.data);

    await dispatch(DISPATCH_removeOpenTabById(article._id));

    if (
      !getState().reducer.tabs.open.find((x) => {
        return x.id === article._id;
      })
    ) {
      await dispatch({
        type: ACTION_openArticle,
        payload: article,
      });
    }

    await select(dispatch, article._id);

    document.documentElement.scrollTop = 0;
  };
}

export function DISPATCH_createArticle(payload, sectionData, id, post) {
  return async (dispatch, getState) => {
    let res = await Axios.post("/UPDATE_ARTICLE/" + id, {
      //let res = await Axios.post("/createArticle", {

      // articleName: payload.articleName,
      // workName: payload.workName,
      // tags: payload.tags,
      // pollOptions: payload.pollOptions,
      //
      // // image: OpenFromInkLink,
      //
      // //userID: user,
      // // communityID: community,
      // communityID: payload.communityID,
      // //payload.community,

      ...payload,

      articleSectionDOList: sectionData,
    });

    if (post) {
      await Axios.post("/publishArticle/" + id);
    } else {
      await Axios.post("/unpublishArticle/" + id);
    }

    // console.log("_------------------------------------------");
    // console.log(res.data);

    dispatch({
      type: ACTION_openArticle,
      payload: res.data,
    });

    await reloadLoggedInUser(dispatch, getState);
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

export function DISPATCH_openEditArticleTab(articleId) {
  return async (dispatch) => {
    await dispatch({
      type: ACTION_editArticle,
      id: articleId,
    });

    await select(dispatch, articleId + articleId);

    document.documentElement.scrollTop = 0;

    //await select(dispatch, communityId); //???
  };
}
