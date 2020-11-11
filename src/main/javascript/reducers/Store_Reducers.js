import * as at from "../actions/Store_Actions";
import React from "react";
import { openArticleState } from "./openArticleState";
import { openCommunityState } from "./openCommunityState";
import { openUserState } from "./openUserState";
import { createArticleState } from "./createArticleState";
import { errorState } from "./errorState";

const initialState = {
  errors: "",
  selectedTab: "",

  user: {
    logged_in: false,
    name: "",
    id: "",
    //url:"",
    communities: [],
    articles: [],
    study_guides: [],
    user_data: {},
    bookmarks: [],
  },

  spotlight: {
    articles: [],
    communities: [],
  },

  tabs: {
    open: [],
  },

  data: [],

  homeTabData: {
    stream_users: [],
    stream_communities: [],
    stream_articles: [],
  },
};

const reducer = (state = initialState, action) => {
  switch (action.type) {
    case at.ACTION_openCreateArticleTab:
      return createArticleState(state, action);

    case at.ACTION_openCommunity:
      return openCommunityState(state, action);

    case at.ACTION_openUser:
      return openUserState(state, action);

    case at.ACTION_openArticle:
      return openArticleState(state, action);

    case at.GET_ERRORS:
      return errorState(state, action);

    //----------------------------------------------------

    case at.ACTION_joinCommunity:
      return state;

    case at.ACTION_createCommunity:
      return state;

    case at.ACTION_createArticle:
      return state;

    //-------------------------

    case "reload":
      console.log("reloading");
      console.log(state.homeTabData.stream_articles);
      return {
        ...state,

        homeTabData: {
          ...state.homeTabData,
          stream_articles: state.homeTabData.stream_articles.map((x) => {
            if (x._id === action.payload.data._id) {
              x = action.payload.data;
            }
            return x;
          }),
        },
      };

    // ----------------------------------------------------
    //TODO these are client-side manual state updates

    case at.ACTION_addCommunityToLoggedInUser:
      return {
        ...state,
        user: {
          ...state.user,
          communities: state.user.communities.concat({
            _id: action.data,
          }),
        },
      };

    case at.ACTION_addBookmark:
      return {
        ...state,
        user: {
          ...state.user,
          bookmarks: state.user.bookmarks.concat({
            name: action.name,
          }),
        },
      };

    //NOT WORKING
    case at.ACTION_removeOpenTabById:
      return {
        ...state,
        tabs: {
          ...state.tabs,
          open: state.tabs.open.filter((tab) => tab.id !== action.id),
        },
      };

    //-----------------------------------------------------

    case at.ACTION_init:
      return {
        ...state,
        homeTabData: {
          stream_articles: action.payload.articles,
          stream_users: action.payload.users,
          stream_communities: action.payload.communities,
        },
      };

    case at.ACTION_register:
      //TODO finish

      return {
        ...state,
        user: {
          logged_in: true,
          name: action.payload.name,
          id: action.payload._id,
          //url:"",
          communities: [],
          articles: [],
          study_guides: [],
          bookmarks: [],
          user_data: {},
        },
      };

    case at.ACTION_logOut:
      return {
        ...state,
        user: {
          logged_in: false,
          name: "",
          id: "",
          //url:"",
          communities: [],
          articles: [],
          study_guides: [],

          bookmarks: [],

          user_data: {},
        },
        tabs: {
          ...state.tabs,
          open: [],
        },
      };

    case at.ACTION_logIn:
      return {
        ...state,
        user: {
          ...state.user,

          bookmarks: action.bookmarks,
          articles: action.articles,
          communities: action.communities,
          logged_in: true,
          name: action.payload.name,
          id: action.payload._id,
          user_data: action.payload,
        },
      };
  }
  return state;
};

export default reducer;
