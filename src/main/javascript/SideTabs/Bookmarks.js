import React from "react";

import WebsiteTab from "../TabLabels/Bookmarks_TabLabels";
import * as store from "../actions/Store_Actions";
import { connect } from "react-redux";
import LoginRegister_Container from "./Login_Register";

import { Bookmarks_StateToProps as mapStateToProps } from "../maps/StateToProps";
import { Bookmarks_mapDispatchToProps as mapDispatchToProps } from "../maps/DispatchToProps";
/*
 * Show the bookmark on the right side of the screen
 * */

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

  function renderBookmarkLogic() {
    return props.state.user.bookmarks.map((x) => {
      return (
        <a
          href={""}
          className={"list-group-item"}
          onClick={(e) => {
            e.preventDefault();
            props.DISPATCH_openArticle(x._links.Tab_Version.href);
          }}
        >
          {x.articleName}
        </a>
      );
    });
  }
}

export default connect(mapStateToProps, mapDispatchToProps)(Bookmarks);
