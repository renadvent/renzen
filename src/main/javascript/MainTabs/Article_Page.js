import React from "react";
import { connect } from "react-redux";

import { ArticlePage_StateToProps as mapStateToProps } from "../maps/StateToProps";
import { ArticlePage_mapDispatchToProps as mapDispatchToProps } from "../maps/DispatchToProps";

import CommentSection from "../components/CommentSection";

/**
renders the article content as tab content
 */

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

      {LikeDislikeSection()}

      <h1>{props.payload.name}</h1>
      <p>{props.payload.description}</p>
      <p>by: {props.payload.userName}</p>

      <img
        height={500}
        src={props.payload.image}
        alt={"IMAGE HERE! " + props.payload.image}
      />

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

      <h3>Comments</h3>

      <div
        //className="d-block center"
        style={{ align: "center", textAlign: "left", width: "60%" }}
      >
        <CommentSection
          _id={props.payload._id}
          // comments={props.payload.comments}
          comments={props.article.data.comments}
          props={props}
        />
      </div>
    </div>
  );

  function LikeDislikeSection() {
    return (
      <div>
        <button
          className="btn btn-secondary"
          onClick={() => {
            console.log(props.payload._id);
            props.DISPATCH_likeArticle(props.payload._id);
          }}
        >
          Likes {props.article.data.likes}
        </button>
        <button
          className="btn btn-secondary"
          onClick={() => {
            props.DISPATCH_dislikeArticle(props.payload._id);
          }}
        >
          Dislikes {props.article.data.dislikes}
        </button>
      </div>
    );
  }

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
