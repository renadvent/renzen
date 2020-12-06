import * as at from "../actions/Store_Actions";

import React from "react";

import { openArticleState } from "./openArticleState";
import { openCommunityState } from "./openCommunityState";
import { openUserState } from "./openUserState";
import { createArticleState } from "./createArticleState";
import { errorState } from "./errorState";

import { initialState } from "./initialState";
import { ACTION_editArticle } from "../actions/StoreDefs";
import { editArticleState } from "./editArticleState";

const reducer = (state = initialState, action) => {
  //TODO working on
  switch (action.type) {
    case "loadMore":
      console.log(state);

      return {
        ...state,
        streamPage: state.streamPage + 1,
        homeTabData: {
          ...state.homeTabData,

          stream_articles: state.homeTabData.stream_articles.concat(
            action.articles.map((x, index) => {
              x["UUID"] = action.articleUUIDs[index];
              return x;
            })
          ),

          //          stream_articles_UUID: [],
        },
      };

    case "selectTab":
      console.log("ACTIVE TAB ID: " + action.id);

      return {
        ...state,
        selectedTab: action.id,
      };

    //--------------------------------------
    case at.ACTION_replaceArticle:
      return {
        ...state,
        homeTabData: {
          ...state.homeTabData,
          stream_articles: state.homeTabData.stream_articles.map((article) => {
            console.log(article);
            console.log(action.uuid);

            //TODO fix so if same post showed multiple times on homepage, likes etc will update all
            if (
              article.UUID === action.uuid
              //  || (article._id === action.payload.data._id &&

              // article._id === action.currentID &&
              // article._id === action.originalID &&
              // article._id !== action.replacementID
            ) {
              article = action.payload.data;
              article["UUID"] = action.uuid;
            }

            return article;
            //console.log(x);
          }),
        },
      };

    //----------------------------------------------------

    case at.ACTION_openCreateArticleTab:
      return createArticleState(state, action);

    case ACTION_editArticle:
      return editArticleState(state, action);

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
      // console.log("reloading");
      // console.log(state.homeTabData.stream_articles);
      // console.log(action);
      return {
        ...state,

        homeTabData: {
          ...state.homeTabData,
          stream_articles: state.homeTabData.stream_articles.map((x) => {
            if (x._id === action.payload.data._id) {
              let u = x.UUID;
              x = action.payload.data;
              x["UUID"] = u; //action.uuid;
            }
            return x;
          }),
        },
      };

    case "reloadTab":
      // console.log("reloading tab");
      // console.log(state);
      // console.log(action.payload);
      return {
        ...state,

        tabs: {
          open: state.tabs.open.map((x) => {
            if (x.data._id === action.payload.data._id) {
              console.log("FOUND");
              x.data = action.payload.data;
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
        selectedTab: "",
        tabs: {
          ...state.tabs,
          open: state.tabs.open.filter((tab) => tab.id !== action.id),
        },
      };

    //-----------------------------------------------------

    case at.ACTION_init:
      return {
        ...state,

        streamPage: 1,

        homeTabData: {
          stream_articles: action.payload.articles.map((x, index) => {
            x["UUID"] = action.payload.articleUUIDs[index];
            return x;
          }),

          // stream_articles: action.payload.articles,
          // stream_users: action.payload.users,

          stream_users: action.payload.users,

          stream_communities: action.payload.communities,
          // stream_articles_UUID: action.payload.UUIDArray,
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
        //
        // ...initialState,
        // homeTabData: state.homeTabData,
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
