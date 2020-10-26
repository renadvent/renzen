// import * as at from "../actions/Store_Actions";
// import React from "react";
// import AppTab from "../Page_Tab";
// import Profile_Page from "../Profile_Page";
// import CommunityAppTabContent from "../Community_Page";
// import ArticleAppTabContent from "../Article_Page";
// import { ACTION_openCreateArticleTab } from "../actions/Store_Actions";
// import ArticleEditTab from "../Create_Article_Page";
//
// const initialState = {
//
//   user : {
//     logged_in: false,
//     username: "",
//     id: "",
//     communities: [],
//     articles: [],
//     bookmarks: [],
//   }
//
// };
//
// const reducer = (state = initialState, action) => {
//   switch (action.type) {
//     case at.ACTION_openUser:
//       console.log("opening");
//       console.log(action);
//
//       return {
//         ...state,
//         tabs: {
//           ...state.tabs,
//           open: state.tabs.open.concat({
//             type: "profile",
//             name: action.data.name,
//             data: action.data,
//             id: action.data._id,
//             tab: (
//               <AppTab
//                 name={action.data.name}
//                 href={"A" + action.data._id}
//                 id={action.data._id}
//               />
//             ),
//             component: (
//               <Profile_Page
//                 data={action.data}
//                 articles={action.articles}
//                 profiles={action.profiles}
//                 communities={action.communities}
//                 href={"A" + action.data._id}
//                 id={action.data._id}
//               />
//             ),
//           }),
//         },
//       };
//       break;
//     case at.ACTION_register:
//       //TODO finish
//
//       return {
//         ...state,
//         user: {
//           logged_in: true,
//           name: action.payload.name,
//           id: action.payload._id,
//           //url:"",
//           communities: [],
//           articles: [],
//           study_guides: [],
//           bookmarks: [],
//
//           user_data: {},
//         },
//       };
//
//       break;
//
//     case at.ACTION_logOut:
//       return {
//         ...state,
//         user: {
//           logged_in: false,
//           name: "",
//           id: "",
//           //url:"",
//           communities: [],
//           articles: [],
//           study_guides: [],
//
//           bookmarks: [],
//
//           user_data: {},
//         },
//         tabs: {
//           ...state.tabs,
//           open: [],
//         },
//       };
//
//     case at.ACTION_logIn:
//       //TODO redo
//
//       console.log("opening");
//       console.log(action);
//
//       return {
//         ...state,
//         user: {
//           ...state.user,
//
//           //TODO fix error when login user has no bookmarks, communities etc like in init
//           // bookmarks:
//           //   action.payload.articleBookmarksCM._embedded
//           //     .articleStreamComponentCoes,
//
//           bookmarks: action.bookmarks,
//           articles: action.articles,
//           communities: action.communities,
//           logged_in: true,
//           name: action.payload.name,
//           id: action.payload._id,
//           user_data: action.payload,
//         },
//         tabs: {
//           ...state.tabs,
//           open: state.tabs.open.concat({
//             type: "CURRENT USER",
//             name: action.payload.name,
//             data: action.payload,
//             id: action.payload._id,
//             component: (
//               <Profile_Page
//                 data={action.payload}
//                 articles={action.articles}
//                 profiles={action.profiles}
//                 communities={action.communities}
//                 href={"A" + action.payload._id}
//                 id={action.payload._id}
//               />
//             ),
//             tab: (
//               <AppTab
//                 name={action.payload.name + " (Your Profile)"}
//                 href={"A" + action.payload._id}
//                 id={action.payload._id}
//               />
//             ),
//           }),
//         },
//       };
//   }
//   return state;
// };
//
// export default reducer;
