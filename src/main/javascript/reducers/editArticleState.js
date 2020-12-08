import ArticleEditTab from "../MainTabs/Edit_Article_Components/Update_Article_Page";
import AppTab from "../TabLabels/Page_TabLabels";
import React from "react";

export function editArticleState(state, action) {
  //TODO pass article sections to
  //as a property of to ArticleEditTab and then load into state

  return {
    ...state,
    // selectedTab: action.id + action.id,
    tabs: {
      ...state.tabs,
      open: state.tabs.open.concat({
        // data: {
        //   _id: action.id + action.id,
        // },
        type: "writing article",
        id: action.id,

        name: action.articleName,

        component: <ArticleEditTab id={action.id} href={"A" + action.id} />,
        tab: (
          <AppTab
            name={"Editing Article"}
            href={"A" + action.id}
            id={action.id}
          />
        ),
      }),
    },
  };
}
