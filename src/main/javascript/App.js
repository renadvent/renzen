import React, { useEffect } from "react";
import Header from "./Header";
import Tab_Pane from "./Tab_Pane";
import Footer from "./Footer";
import Bookmarks from "./SideTabs/Bookmarks";
import { connect } from "react-redux";
import { TabPane_StateToProps as mapStateToProps } from "./maps/StateToProps";
import { TabPane_mapDispatchToProps as mapDispatchToProps } from "./maps/DispatchToProps";
import setJWTToken from "./securityUtils/setJWTToken";

/*
sets up page and divisions
 */

const jwtToken = localStorage.jwtToken;

function App(props) {
  //load home screen info
  useEffect(() => {
    // if (jwtToken) {
    //   setJWTToken(jwtToken);
    //   const decoded_jwtToken = jwt_decode(jwtToken);
    //
    //   const currentTime = Date.now() / 1000;
    //   if (decoded_jwtToken.exp < currentTime) {
    //     props.DISPATCH_logOut();
    //   }
    // }

    props.DISPATCH_init();
  }, []);

  //used to open create article from Ink
  useEffect(() => {
    if (OpenFromInkSource !== null) {
      props.DISPATCH_openCreateArticleTab("5f92319abce4e159c51a0a11");
    }
  }, []);

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

export default connect(mapStateToProps, mapDispatchToProps)(App);
