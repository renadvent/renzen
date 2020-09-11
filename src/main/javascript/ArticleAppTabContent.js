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
      aria-labelledby="article-tab"
    >
      <br />
      {props.user.logged_in ? (
        <button
          className="btn btn-secondary"
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
        <div className="alert alert-secondary" role="alert">
          Log in to bookmark articles!!
        </div>
      )}

      <h1>{props.payload.name}</h1>
      <p>{props.payload.description}</p>
      <p>by: {props.payload.userName}</p>

      <hr />

      {props.payload.articleSectionCOList.map((section) => {
        //style={{ textAlign: "center" }}
        return (
          <div className="d-flex justify-content-center">
            <div className="card" style={{ width: "18rem" }}>
              <div className="card-body">
                <h5 className="card-title">{section.header}</h5>
                {/*<h6 className="card-subtitle mb-2 text-muted">Card subtitle</h6>*/}
                <p className="card-text">{section.body}</p>
              </div>
            </div>
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
