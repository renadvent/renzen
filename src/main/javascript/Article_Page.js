import React from "react";
import { connect } from "react-redux";
import * as store from "./Store_Actions";

/*
renders the article content as tab content
 */

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

function Article_Page(props) {
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
        props.user.bookmarks.find((x) => {
          return x._id === props.payload._id;
        }) ? (
          <div className="alert alert-secondary" role="alert">
            Bookmarked!
          </div>
        ) : (
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
        )
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
        console.log(section);

        return (
          <div className="d-flex justify-content-center">
            <div className="card" style={{ width: "18rem" }}>
              <div className="card-body">
                <h5 className="card-title">{section.header}</h5>
                <p className="card-text">{section.body}</p>

                {section.ImageID !== "" || section.ImageID !== null ? (
                  <img
                    height={250}
                    src={section.imageID}
                    alt={"IMAGE HERE! " + section.imageID}
                  />
                ) : null}
              </div>
            </div>
          </div>
        );
      })}
    </div>
  );
}

export default connect(mapStateToProps, mapDispatchToProps)(Article_Page);
