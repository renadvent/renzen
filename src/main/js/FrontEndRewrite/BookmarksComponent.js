import React from "react";

import WebsiteTab from "./WebsiteTab";
import * as store from "./Store_Actions";
import { connect } from "react-redux";

/*
 * Show the study guide on the right side of the screen
 *
 * */

const mapStateToProps = (state) => {
  return {
    state: state,
  };
};

const mapDispatchToProps = (dispatch) => {
  return {
    DISPATCH_addBookmark: (userId, articleId) =>
      dispatch(store.DISPATCH_addBookmark),
  };
};

function BookmarksComponent(props) {
  return (
    <div id="addTo">
      <ul className="nav nav-tabs">
        <WebsiteTab name={"Bookmarks"} linkTo={"#AnnoSec"} active={"active"} />
        <WebsiteTab name={"+"} linkTo={""} active={""} />
      </ul>
      <div className="tab-content" id="myTabContent2">
        <div
          className="tab-pane fade show active"
          id="AnnoSec"
          role="tabpanel"
          aria-labelledby="profile-tab"
        >
          {props.state.user.bookmarks.map((x) => {
            return <li>{x.name}</li>;
          })}

          <div id="annoBar">
            {/*<button>Add Note</button>*/}
            {/*...*/}
          </div>
        </div>
      </div>
    </div>
  );
}

export default connect(mapStateToProps, mapDispatchToProps)(BookmarksComponent);
