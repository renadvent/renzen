import React, { useEffect, useState } from "react";
import { connect } from "react-redux";
import Home_Container from "./MainTabs/Home_Page";

import { TabPane_StateToProps as mapStateToProps } from "./maps/StateToProps";
import { TabPane_mapDispatchToProps as mapDispatchToProps } from "./maps/DispatchToProps";

function Tab_Pane(props) {
  useEffect(() => {
    console.log("selected tab changed" + props.selectedTab);

    if (props.selectedTab !== "") {
      // $("#tabA" + props.selectedTab + props.selectedTab).tab("show");
      $("#tabA" + props.selectedTab).tab("show");
    } else {
      $("#home-tab").tab("show");
    }
  }, [props.selectedTab]);

  return (
    <div id={"tabsAndContents"}>
      <div
        id={"tab-list"}
        // style={{ backgroundColor: "white" }}
      >
        <ul className="nav nav-tabs" id="app-tabs" role="tablist">
          <li className="nav-item">
            <a
              className="nav-link active"
              id="home-tab"
              data-toggle="tab"
              href="#home-contents"
              role="tab"
            >
              Home
            </a>
          </li>
          {console.log(props)}
          {props.open.map((open) => {
            return open.tab;
          })}
        </ul>
      </div>

      <div
        id={"tabContents"}
        className={"tab-content"}
        style={{ backgroundColor: "black" }}
      >
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

export default connect(mapStateToProps, mapDispatchToProps)(Tab_Pane);
