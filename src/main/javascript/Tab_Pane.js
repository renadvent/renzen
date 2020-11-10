import React, { useEffect, useState } from "react";
import { connect } from "react-redux";
import Home_Container from "./MainTabs/Home_Page";

import { TabPane_StateToProps as mapStateToProps } from "./maps/StateToProps";
import { TabPane_mapDispatchToProps as mapDispatchToProps } from "./maps/DispatchToProps";

function Tab_Pane(props) {
  //loads initially, and updates article names etc when a new tab is opened
  useEffect(() => {
    props.DISPATCH_init();
  }, []);

  useEffect(() => {
    if (OpenFromInkSource !== null) {
      props.DISPATCH_openCreateArticleTab("5f92319abce4e159c51a0a11");
    }
  }, []);

  useEffect(() => {
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
              Home
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

export default connect(mapStateToProps, mapDispatchToProps)(Tab_Pane);
