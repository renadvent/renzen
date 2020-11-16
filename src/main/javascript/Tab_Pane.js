import React, { useEffect, useState } from "react";
import { connect } from "react-redux";
import Home_Container from "./MainTabs/Home_Page";

import { TabPane_StateToProps as mapStateToProps } from "./maps/StateToProps";
import { TabPane_mapDispatchToProps as mapDispatchToProps } from "./maps/DispatchToProps";
import { select } from "./actions/MiscellaneousActions";

function Tab_Pane(props) {
  useEffect(() => {
    console.log("selected tab changed" + props.selectedTab);

    //TODO think it works
    //resets tab in "state" if home-tab is clicked
    $(document).on("shown.bs.tab", 'a[data-toggle="tab"]', (e) => {
      if ($(e.target).attr("id") === "home-tab") {
        // alert("Clicked!");
        props.dispatch(() => select(props.dispatch, ""));
      }
    });

    if (props.selectedTab !== "") {
      $("#tabA" + props.selectedTab).tab("show");
    } else {
      $("#home-tab").tab("show");
    }
  }, [props.selectedTab]);

  return (
    <div id={"tabsAndContents"}>
      <div id={"tab-list"}>
        <ul className="nav nav-tabs" id="app-tabs" role="tablist">
          <li className="nav-item">
            <a
              className="nav-link active"
              id="home-tab"
              data-toggle="tab"
              href="#home-contents"
              role="tab"
            >
              <div>Home</div>
            </a>
          </li>
          {console.log(props)}
          {props.open.map((open) => {
            return open.tab;
          })}
        </ul>
      </div>

      <div id={"tabContents"} className={"tab-content"}>
        <div
          className="tab-pane fade show active"
          id="home-contents"
          role="tabpanel"
          aria-labelledby="home-tab"
        >
          <Home_Container />
        </div>

        {props.open.map((open) => {
          return open.component;
        })}
      </div>
    </div>
  );
}

//has to be "null" to get default dispatch?
export default connect(mapStateToProps, null)(Tab_Pane);
