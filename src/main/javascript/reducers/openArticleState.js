import AppTab from "../TabLabels/Page_TabLabels";
import ArticleAppTabContent from "../MainTabs/Article_Page";
import React from "react";

export function openArticleState(state, action) {
  return {
    ...state,
    tabs: {
      ...state.tabs,
      open: state.tabs.open.concat({
        type: "articles",
        name: action.payload.articleName,
        data: action.payload,
        id: action.payload._id,

        likes: action.payload.likes,
        dislikes: action.payload.dislikes,

        tab: (
          <AppTab
            name={action.payload.articleName}
            href={"A" + action.payload._id}
            id={action.payload._id}
          />
        ),
        component: (
          <ArticleAppTabContent
            payload={action.payload}
            href={"A" + action.payload._id}
            id={action.payload._id}
            likes={action.payload.likes}
            dislikes={action.payload.dislikes}
          />
        ),
      }),
    },
  };
}
