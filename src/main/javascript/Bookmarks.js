import React from "react";

import WebsiteTab from "./Bookmarks_Tab";
import * as store from "./actions/Store_Actions";
import { connect } from "react-redux";
import LoginRegister_Container from "./Login_Register";

import { Bookmarks_StateToProps as mapStateToProps } from "./maps/StateToProps";
/*
 * Show the bookmark on the right side of the screen
 * */

// const mapStateToProps = (state) => {
//   console.log("STATE");
//   console.log(state);
//
//   return {
//     state: state.reducer,
//     user: state.reducer.user,
//   };
// };

const mapDispatchToProps = (dispatch) => {
  return {
    DISPATCH_openArticle: (url) => dispatch(store.DISPATCH_openArticle(url)),
  };
};

function Bookmarks(props) {
  return (
    <div>
      <div className={props.user.logged_in ? "d-block" : "d-none"}>
        <div className="jumbotron">
          <h5>Keep Track of what matters to you!</h5>
          <p className="lead">
            Add bookmarks below to articles you want to come back to!
          </p>
        </div>
      </div>

      <div id="addTo">
        <div className={!props.user.logged_in ? "d-block" : "d-none"}>
          <LoginRegister_Container />
        </div>

        <div className={props.user.logged_in ? "d-block" : "d-none"}>
          <ul className="nav nav-tabs">
            <WebsiteTab
              name={"Bookmarks"}
              linkTo={"#AnnoSec"}
              active={"active"}
            />
          </ul>
        </div>

        <div className="tab-content" id="myTabContent2">
          <div
            className="tab-pane fade show active"
            id="AnnoSec"
            role="tabpanel"
            aria-labelledby="profile-tab"
          >
            <ul className="list-group">{renderBookmarkLogic()}</ul>
          </div>
        </div>
      </div>
    </div>
  );

  /**
   * logic
   * @param bookmarks
   * @returns {unknown[]}
   */

  function renderBookmarkLogic(bookmarks) {
    return props.state.user.bookmarks.map((x) => {
      return (
        <li
          className={"list-group-item"}
          onClick={() => props.DISPATCH_openArticle(x._links.Tab_Version.href)}
        >
          {x.name}
        </li>
      );
    });
  }
}

export default connect(mapStateToProps, mapDispatchToProps)(Bookmarks);
