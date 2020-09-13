import * as at from "./Store_Actions";
import React from "react";
import AppTab from "./AppTab";
import ProfileAppTabContent from "./ProfileAppTabContent";
import CommunityAppTabContent from "./CommunityAppTabContent";
import ArticleAppTabContent from "./ArticleAppTabContent";
import { ACTION_openCreateArticleTab } from "./Store_Actions";
import ArticleEditTab from "./ArticleEditTab";

//INITIAL STATE

const initialState = {
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

  homeTabData: {
    stream_users: [],
    stream_communities: [],
    stream_articles: [],
  },
};

const reducer = (state = initialState, action) => {
  switch (action.type) {
    //TODO change redux state on loading spotlight content
    case at.ACTION_getSpotlightContent:
      return {
        ...state,
      };

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

    //NOT WORKING
    case at.ACTION_removeOpenTabById:
      return {
        ...state,
        tabs: {
          ...state.tabs,
          open: state.tabs.open.filter((tab) => tab.id !== action.id),
        },
      };

    case at.ACTION_openCreateArticleTab:
      return {
        ...state,
        tabs: {
          ...state.tabs,
          open: state.tabs.open.concat({
            data: {
              _id: action.id + action.id,
            },
            type: "writing article",
            id: action.id + action.id,
            component: (
              <ArticleEditTab
                id={action.id}
                href={"A" + action.id + action.id}
              />
            ),
            tab: (
              <AppTab
                name={"Editing Article"}
                href={"A" + action.id + action.id}
              />
            ),
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

    case at.ACTION_createArticle:
      return state;

      break;

    case at.ACTION_joinCommunity:
      return {
        ...state,
      };

      break;

    case at.ACTION_init:
      return {
        ...state,
        homeTabData: {
          stream_articles: action.payload.articles,
          stream_users: action.payload.users,
          stream_communities: action.payload.communities,
        },
      };
      break;

    case at.ACTION_openCommunity:
      console.log("OPEN COMMUNITY REDUCER REC");
      console.log(action);
      return {
        ...state,
        tabs: {
          ...state.tabs,
          open: state.tabs.open.concat({
            type: "community",
            name: action.payload.name,
            // data: action.payload.data,
            data: action.payload,
            id: action.payload._id,
            tab: (
              <AppTab
                name={action.payload.name}
                href={"A" + action.payload._id}
                id={action.payload._id}
              />
            ),
            component: (
              <CommunityAppTabContent
                payload={action.payload}
                href={"A" + action.payload._id}
                id={action.payload._id}
              />
            ),
          }),
        },
      };
      break;

    case at.ACTION_openUser:
      return {
        ...state,
        tabs: {
          ...state.tabs,
          open: state.tabs.open.concat({
            type: "profile",
            name: action.payload.name,
            data: action.payload,
            id: action.payload._id,
            tab: (
              <AppTab
                name={action.payload.name}
                href={"A" + action.payload._id}
                id={action.payload._id}
              />
            ),
            component: (
              <ProfileAppTabContent
                payload={action.payload}
                href={"A" + action.payload._id}
                id={action.payload._id}
              />
            ),
          }),
        },
      };
      break;

    case at.ACTION_openArticle:
      return {
        ...state,
        tabs: {
          ...state.tabs,
          open: state.tabs.open.concat({
            type: "articles",
            name: action.payload.name,
            data: action.payload,
            id: action.payload._id,

            tab: (
              <AppTab
                name={action.payload.name}
                href={"A" + action.payload._id}
                id={action.payload._id}
              />
            ),
            component: (
              <ArticleAppTabContent
                payload={action.payload}
                href={"A" + action.payload._id}
                id={action.payload._id}
              />
            ),
          }),
        },
      };
      break;

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

      break;

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
      //TODO redo

      return {
        ...state,
        user: {
          ...state.user,

          //TODO fix error when login user has no bookmarks, communities etc like in init
          // bookmarks:
          //   action.payload.articleBookmarksCM._embedded
          //     .articleStreamComponentCoes,

          bookmarks: action.bookmarks,
          articles: action.articles,
          communities: action.communities,
          logged_in: true,
          name: action.payload.name,
          id: action.payload._id,
          user_data: action.payload,
        },
        tabs: {
          ...state.tabs,
          open: state.tabs.open.concat({
            type: "CURRENT USER",
            name: action.payload.name,
            data: action.payload,
            id: action.payload._id,
            component: (
              <ProfileAppTabContent
                payload={action.payload}
                href={"A" + action.payload._id}
                id={action.payload._id}
              />
            ),
            tab: (
              <AppTab
                name={action.payload.name + " (Your Profile)"}
                href={"A" + action.payload._id}
                id={action.payload._id}
              />
            ),
          }),
        },
      };

    case at.ACTION_createCommunity:
      break;
  }
  return state;
};

export default reducer;
