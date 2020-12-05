import AppTab from "../TabLabels/Page_TabLabels";
import Profile_Page from "../MainTabs/Profile_Page";
import React from "react";

export function openUserState(state, action) {
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

            drafts={action.drafts}

            communities={action.communities}
            href={"A" + action.data._id}
            id={action.data._id}
          />
        ),
      }),
    },
  };
}
