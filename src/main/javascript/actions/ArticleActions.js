import Axios from "axios";
import setJWTToken from "../securityUtils/setJWTToken";
import jwt_decode from "jwt-decode";
import { ACTION_openArticle, ACTION_openCreateArticleTab } from "./StoreDefs";

export function DISPATCH_openArticle(url) {
  console.log("open article");
  console.log(url);

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
