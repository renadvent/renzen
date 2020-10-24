import * as at from "../actions/Store_Actions";
import React from "react";
import AppTab from "../Page_Tab";
import Profile_Page from "../Profile_Page";
import CommunityAppTabContent from "../Community_Page";
import ArticleAppTabContent from "../Article_Page";
import { ACTION_openCreateArticleTab } from "../actions/Store_Actions";
import ArticleEditTab from "../Create_Article_Page";

const initialState = {
  openTabs: [],
};

const reducer = (state = initialState, action) => {
  switch (action.type) {
  }
  return state;
};

export default reducer;
