import Axios from "axios";
import { Provider } from "react-redux";
import { applyMiddleware, createStore, compose } from "redux";

export const FAKE_LOG_IN = "FAKE_LOGIN";

export const OPEN_STUDY_GUIDE = "OPEN_STUDY_GUIDE";
export const OPEN_COMMUNITY = "OPEN_COMMUNITY";
export const OPEN_ARTICLE = "OPEN_ARTICLE";
export const OPEN_USER = "OPEN_USER";
export const OPEN_MESSAGES = "OPEN_MESSAGES";

export const SEND_FRIEND_REQUEST = "SEND_FRIEND_REQUEST";

export const ADD_CONTENT_TO_STUDYGUIDE = "ADD_CONTENT_TO_STUDYGUIDE";
export const CREATE_ANNOTATION = "CREATE_ANNOTATION";

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

export const open_article_helper = (articleObject) => {
  return {
    type: OPEN_ARTICLE,
    activeArticle: articleObject,
  };
};

export const open_article = (articleURL) => {
  return (dispatch) => {
    Axios.get(articleURL).then((loadedArticle) => {
      dispatch(open_article_helper(loadedArticle));
    });
  };
};

//---------------------------------------------------------------

//synchronous
export const fake_login_synchronous = (userObject) => {
  return {
    type: FAKE_LOG_IN,
    userNameObject: userObject,
  };
};

//asynchronous //thunk
export const fake_login = (userURL) => {
  return (dispatch) => {
    Axios.get(userURL).then((loadedUser) => {
      dispatch(fake_login_synchronous(loadedUser));
    });
  };
};

//finish adding these here
