import React from "react";
import { connect } from "react-redux";
import Axios from "axios";
import Header_Container from "./Header_Container";
import TabPane_Container from "./TabPane_Container";
import Footer_Container from "./Footer_Container";
import BookmarksComponent from "./BookmarksComponent";

/*
sets up page and divisions
 */

function App2(props) {
  return (
    <div id={"app-container"} className="container-fluid">
      <Header_Container key={"app-header"} />
      <div id={"app-body"} className="container-fluid">
        <div id={"app-body-row"} className="row">
          <div id={"app-tab-area"} className={"col-9"}>
            <TabPane_Container />
          </div>
          <div id={"study-guide-area"} className={"col-3"}>
            <BookmarksComponent />
          </div>
        </div>
      </div>
      <Footer_Container key={"app-footer"} />
    </div>
  );
}

export default App2;
