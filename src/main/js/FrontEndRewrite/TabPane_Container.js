import React, { useEffect } from "react";
import { connect } from "react-redux";
import Home_Container from "./Home_Container";
import * as store from "./Store_Actions";

/*
sets up main tab (HOME) and content
hosts other opened tabs when they are opened
 */

const mapStateToProps = (state) => {
  return {
    open: state.tabs.open,
  };
};

const mapDispatchToProps = (dispatch) => {
  return {
    DISPATCH_init: () => dispatch(store.DISPATCH_init()),
  };
};

function TabPane_Container(props) {
  //initial load of site

  //loads initially, and updates article names etc when a new tab is opened
  useEffect(() => {
    props.DISPATCH_init();
  }, [props.open]);

  //updates active tab when new ones are opened
  useEffect(() => {
    if (props.open.length > 0) {
      $("#tabA" + props.open[props.open.length - 1].data._id).tab("show");
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

export default connect(mapStateToProps, mapDispatchToProps)(TabPane_Container);
