import AppTab from "../TabLabels/Page_Tab";
import CommunityAppTabContent from "../MainTabs/Community_Page";
import React from "react";

export function openCommunityState(state, action) {
  return {
    ...state,
    selectedTab: action.payload._id,
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
}
