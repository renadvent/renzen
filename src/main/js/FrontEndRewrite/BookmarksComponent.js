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
    DISPATCH_openArticle: (url) => dispatch(store.DISPATCH_openArticle(url)),
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
          {console.log("BOOKMARKS-=------------------------------------------")}
          {console.log(props.state.user.bookmarks)}

          {props.state.user.bookmarks.map((x) => {
            return (
              <li
                onClick={() =>
                  props.DISPATCH_openArticle(x._links.Tab_Version.href)
                }
              >
                {x.name}
              </li>
            );
          })}

          {/*{!jQuery.isEmptyObject(props.state.user.bookmarks._embedded) ||*/}
          {/*props.state.user.bookmarks !== []*/}
          {/*  ? props.state.user.bookmarks.map(*/}
          {/*      (x) => {*/}
          {/*        return <li>{x.name}</li>;*/}
          {/*      }*/}
          {/*    )*/}
          {/*  : null}*/}

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
