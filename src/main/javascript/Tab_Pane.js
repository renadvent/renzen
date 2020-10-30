import React, { useEffect } from "react";
import { connect } from "react-redux";
import Home_Container from "./Home_Page";

import { TabPane_StateToProps as mapStateToProps } from "./maps/StateToProps";
import { TabPane_mapDispatchToProps as mapDispatchToProps } from "./maps/DispatchToProps";

function Tab_Pane(props) {
  //initial load of site

  //loads initially, and updates article names etc when a new tab is opened
  useEffect(() => {
    props.DISPATCH_init();
  }, []);

  //updates active tab when new ones are opened
  useEffect(() => {
    if (props.open.length > 0) {
      $("#tabA" + props.open[props.open.length - 1].data._id).tab("show");
    } else {
      $("#home-tab").tab("show");
    }
  }, [props.open]);

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
