import Axios from "axios";
//import reducer from "./reducer";
import {Provider, useDispatch} from "react-redux";
import { applyMiddleware, createStore, compose } from "redux";

export const FAKE_LOG_IN = "FAKE_LOG_IN";

export const LOG_IN = "LOG_IN"

export const OPEN_STUDY_GUIDE = "OPEN_STUDY_GUIDE";
export const OPEN_COMMUNITY = "OPEN_COMMUNITY";
export const OPEN_ARTICLE = "OPEN_ARTICLE";
export const OPEN_USER = "OPEN_USER";
export const OPEN_MESSAGES = "OPEN_MESSAGES";

export const SEND_COMMUNITY_INVITATION = "SEND_COMMUNITY_INVITATION"
export const SEND_FRIEND_REQUEST = "SEND_FRIEND_REQUEST";

export const ADD_CONTENT_TO_STUDY_GUIDE = "ADD_CONTENT_TO_STUDY_GUIDE";

export const POST_NEW_USER = "POST_NEW_USER"
export const POST_NEW_COMMUNITY = "POST_NEW_COMMUNITY"
export const POST_NEW_ARTICLE = "POST_NEW_ARTICLE"
export const POST_NEW_ANNOTATION = "POST_NEW_ANNOTATION";


//export const POST_NEW_


//--------------------------------------------------

//requires: name, description, to post
//pulls username from logged_in_user_name

export const add_community_to_this_user = (userURL, comURL) => {
  Axios.post(userURL,comURL)
}

export const post_new_community = (info) => {

  return (dispatch,getState) => {

    //patch username coms
    //useDispatch(add_community_to_this_user(getState().logged_in_user_URL))

    //Axios.post(/api/post_new_community


    Axios.post("/api/communities", {
      name:info.name,
      creator:getState().logged_in_user_name,
      users:[],
      moderators:[],
      description: info.description,
      articles:[],
      topics:[]
    }).then((res)=>{
      dispatch({
        type:POST_NEW_COMMUNITY,
        res:res
      })
    });
  }
}

/*
action creators for redux
 */

export const open_community = (comURL) => {
  return {
    type: OPEN_COMMUNITY,
    comURL: comURL,
  };
};

//--------------------------------------------------------------

export const open_article = (articleURL) => {
  return (dispatch) => {
    Axios.get(articleURL).then((loadedArticle) => {
      dispatch({
        type: OPEN_ARTICLE,
        activeArticle: loadedArticle,
      });
    });
  };
};

//---------------------------------------------------------------

//asynchronous //thunk
export const fake_login = (userURL) => {
  return (dispatch) => {
    Axios.get(userURL).then((loadedUser) => {
      dispatch({
        type: FAKE_LOG_IN,
        userNameObject: loadedUser,
      });
    });
  };
};

//finish adding these here
