import React from "react";

import WebsiteTab from "./WebsiteTab";
import * as store from "./Store_Actions";
import { connect } from "react-redux";
import LoginRegister_Container from "./LoginRegister_Container";

/*
 * Show the study guide on the right side of the screen
 *
 * */

const mapStateToProps = (state) => {
  return {
    state: state,
    user: state.user,
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
          {/*<RegisterContainer action={props.onRegister}/>*/}
        </div>

        <div className={props.user.logged_in ? "d-block" : "d-none"}>
          <ul className="nav nav-tabs">
            <WebsiteTab
              name={"Bookmarks"}
              linkTo={"#AnnoSec"}
              active={"active"}
            />
            {/*<WebsiteTab name={"+"} linkTo={""} active={""} />*/}
          </ul>
        </div>

        <div className="tab-content" id="myTabContent2">
          <div
            className="tab-pane fade show active"
            id="AnnoSec"
            role="tabpanel"
            aria-labelledby="profile-tab"
          >
            <ul className="list-group">
              {props.state.user.bookmarks.map((x) => {
                return (
                  <li
                    className={"list-group-item"}
                    onClick={() =>
                      props.DISPATCH_openArticle(x._links.Tab_Version.href)
                    }
                  >
                    {x.name}
                  </li>
                );
              })}
            </ul>
          </div>
        </div>
      </div>
    </div>
  );
}

export default connect(mapStateToProps, mapDispatchToProps)(BookmarksComponent);
