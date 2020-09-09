import React from "react";
import { connect } from "react-redux";
import * as store from "./Store_Actions";

const mapStateToProps = (state) => {
  return {
    state: state,
    user: state.user,
  };
};

const mapDispatchToProps = (dispatch) => {
  return {
    DISPATCH_addBookmark: (userId, articleId, name) =>
      dispatch(store.DISPATCH_addBookmark(userId, articleId, name)),
  };
};

function ArticleAppTabContent(props) {
  return (
    <div
      style={{ textAlign: "center" }}
      className="tab-pane fade"
      id={props.href}
      role="tabpanel"
      //aria-labelledby="profile-tab"
    >
      {props.user.logged_in ? (
        <button
          className="btn btn-dark"
          onClick={() =>
            props.DISPATCH_addBookmark(
              props.state.user.id,
              props.payload._id,
              props.payload.name
            )
          }
        >
          Add Bookmark
        </button>
      ) : (
        <button
          onClick={() => {
            $("#home-tab").tab("show");
          }}
          className="btn btn-secondary"
        >
          Log in to bookmark articles!!
        </button>
      )}

      <h1>{props.payload.name}</h1>

      <hr />

      {props.payload.articleSectionCOList.map((section) => {
        return (
          <div>
            <h3>{section.header}</h3>
            <div>{section.body}</div>
          </div>
        );
      })}
    </div>
  );
}

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ArticleAppTabContent);
