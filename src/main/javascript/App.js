import React, { useEffect } from "react";
import Header from "./Header";
import Tab_Pane from "./Tab_Pane";
import Footer from "./Footer";
import Bookmarks from "./SideTabs/Bookmarks";
import { connect } from "react-redux";
import { TabPane_StateToProps as mapStateToProps } from "./maps/StateToProps";
import { TabPane_mapDispatchToProps as mapDispatchToProps } from "./maps/DispatchToProps";
import setJWTToken from "./securityUtils/setJWTToken";
import jwt_decode from "jwt-decode";
import { select } from "./actions/MiscellaneousActions";

/*
sets up page and divisions
 */

const jwtToken = localStorage.jwtToken;

function App(props) {
  // //TODO not calling USEEFFECT in tabPane for some reason
  // $(document).on("shown.bs.tab", 'a[data-toggle="tab"]', function (e) {
  //   if ($(e.target).attr("id") === "home-tab") {
  //     alert("MATCH" + props.selectedTab);
  //     props.dispatch(select(props.dispatch, ""));
  //   }
  // });

  //load home screen info
  useEffect(() => {
    //CHECK TO LOGOUT
    if (jwtToken) {
      setJWTToken(jwtToken);
      const decoded_jwtToken = jwt_decode(jwtToken);

      const currentTime = Date.now() / 1000;
      if (decoded_jwtToken.exp < currentTime) {
        alert("Your Login Expired!");
        props.DISPATCH_logOut();
      }
    }

    props.DISPATCH_init();
  }, []);

  //used to open create article from Ink
  useEffect(() => {
    if (OpenFromInkARTICLEID !== null) {
      // if (OpenFromInkSource !== null) {
      props.DISPATCH_openCreateArticleTab("5f92319abce4e159c51a0a11");
    }
  }, []);

  return (
    <div
      id={"app-container"}
      className="container-fluid"
      // style={{ backgroundColor: "black" }}
    >
      <Header key={"app-header"} />
      <div id={"app-body"} className="container-fluid">
        <div id={"app-body-row"} className="row">
          <div
            id={"app-tab-area"}
            className={"col-9"}
            // style={{ backgroundColor: "black" }}
          >
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
