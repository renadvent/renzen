import * as at from "../actions/Store_Actions";
import React from "react";
import AppTab from "../Page_Tab";
import Profile_Page from "../Profile_Page";
import CommunityAppTabContent from "../Community_Page";
import ArticleAppTabContent from "../Article_Page";
import { ACTION_openCreateArticleTab } from "../actions/Store_Actions";
import ArticleEditTab from "../Create_Article_Page";

//INITIAL STATE

const initialState = {
  errors: "",

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

//export const firstNamedReducer = (state = 1, action) => state;

const reducer = (state = initialState, action) => {
  switch (action.type) {
    case at.GET_ERRORS:
      //update later
      console.log("ACTION");
      console.log(action);

      //test alert for error
      alert(JSON.stringify(action.payload, null, 5));

      return {
        ...state,
        errors: action.payload,
      };
      break;

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
                articles={action.articles}
              />
            ),
          }),
        },
      };
      break;

    case at.ACTION_openUser:
      console.log("opening");
      console.log(action);

      return {
        ...state,
        tabs: {
          ...state.tabs,
          open: state.tabs.open.concat({
            type: "profile",
            name: action.data.name,
            data: action.data,
            id: action.data._id,
            tab: (
              <AppTab
                name={action.data.name}
                href={"A" + action.data._id}
                id={action.data._id}
              />
            ),
            component: (
              <Profile_Page
                data={action.data}
                articles={action.articles}
                profiles={action.profiles}
                communities={action.communities}
                href={"A" + action.data._id}
                id={action.data._id}
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

      console.log("opening");
      console.log(action);

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
              <Profile_Page
                data={action.payload}
                articles={action.articles}
                profiles={action.profiles}
                communities={action.communities}
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
