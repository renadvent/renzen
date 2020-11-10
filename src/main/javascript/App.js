import React from "react";
import Header from "./Header";
import Tab_Pane from "./Tab_Pane";
import Footer from "./Footer";
import Bookmarks from "./SideTabs/Bookmarks";

/*
sets up page and divisions
 */

function App(props) {
  return (
    <div id={"app-container"} className="container-fluid">
      <Header key={"app-header"} />
      <div id={"app-body"} className="container-fluid">
        <div id={"app-body-row"} className="row">
          <div id={"app-tab-area"} className={"col-9"}>
            <Tab_Pane />
          </div>
          <div id={"study-guide-area"} className={"col-3"}>
            <Bookmarks />
          </div>
        </div>
      </div>
      <Footer key={"app-footer"} />
    </div>
  );
}

export default App;
