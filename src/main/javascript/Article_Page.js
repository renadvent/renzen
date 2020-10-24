import React from "react";
import { connect } from "react-redux";
import * as store from "./actions/Store_Actions";

import { ArticlePage_StateToProps as mapStateToProps } from "./maps/StateToProps";

/**
renders the article content as tab content
 */

// const mapStateToProps = (state) => {
//   return {
//     state: state.reducer,
//     user: state.reducer.user,
//   };
// };

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

      {bookmarkLogic()}

      <h1>{props.payload.name}</h1>
      <p>{props.payload.description}</p>
      <p>by: {props.payload.userName}</p>

      <hr />

      {props.payload.articleSectionCOList.map((section) => {
        return (
          <div className="d-flex justify-content-center">
            <div className="card" style={{ width: "60%" }}>
              <div className="card-body">
                <h5 className="card-title">{section.header}</h5>
                <p className="card-text">{section.body}</p>

                {imageRenderLogic(section)}
              </div>
            </div>
          </div>
        );
      })}
    </div>
  );

  /**
   * Logic
   * @returns {JSX.Element}
   */

  function imageRenderLogic(section) {
    return section.imageID !== null ? (
      <img
        height={500}
        src={section.imageID}
        alt={"IMAGE HERE! " + section.imageID}
      />
    ) : null;
  }

  function bookmarkLogic() {
    return props.user.logged_in ? (
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
    );
  }
}

export default connect(mapStateToProps, mapDispatchToProps)(Article_Page);
