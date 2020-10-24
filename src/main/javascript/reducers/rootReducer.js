import { combineReducers, createStore } from "redux";

import reducer from "./Store_Reducers";
import communityReducers from "./CommunityReducers";
import userReducers from "./UserReducers";
import articleReducers from "./ArticleReducers";

export default combineReducers({
  reducer,
  communityReducers,
  userReducers,
  articleReducers,
});
