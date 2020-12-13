import React from "react";
import { connect } from "react-redux";

import { ArticlePage_StateToProps as mapStateToProps } from "../maps/StateToProps";
import { ArticlePage_mapDispatchToProps as mapDispatchToProps } from "../maps/DispatchToProps";

import CommentSection from "../components/CommentSection";

/**
renders the article content as tab content
 */

function Article_Page(props) {
  // console.log(props.payload);

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

      <br />

      <LikeDislikeSection
        _id={props.payload._id}
        likes={props.article.data.likes}
        dislikes={props.article.data.dislikes}
      />

      {/*{LikeDislikeSection()}*/}

      <h1>{props.payload.articleName}</h1>

      <p>by: {props.payload.creatorName}</p>

      <img
        height={500}
        src={props.payload.postImageURL}
        alt={"IMAGE HERE! " + props.payload.postImageURL}
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

      <div className="d-flex justify-content-center">
        <div
          className="card"
          style={{ textAlign: "left", width: "60%" }}
          //className="d-block center"
          // style={{ textAlign: "left", width: "60%" }}
        >
          <CommentSection
            _id={props.payload._id}
            // comments={props.payload.comments}
            comments={props.article.data.comments}
            props={props}
          />
        </div>
      </div>
    </div>
  );

  // function LikeDislikeSection() {
  //   console.log(props);
  //
  //   return (
  //     <LikeDislikeSection
  //       _id={props.payload._id}
  //       likes={props.article.data.likes}
  //       dislikes={props.article.data.dislikes}
  //     />
  //
  //     // <div>
  //     //   <button
  //     //     className="btn btn-secondary"
  //     //     onClick={() => {
  //     //       console.log(props.payload._id);
  //     //       props.DISPATCH_likeArticle(props.payload._id);
  //     //     }}
  //     //   >
  //     //     Likes
  //     //   </button>
  //     //   {props.article.data.likes}
  //     //   <button
  //     //     className="btn btn-secondary"
  //     //     onClick={() => {
  //     //       props.DISPATCH_dislikeArticle(props.payload._id);
  //     //     }}
  //     //   >
  //     //     Dislikes
  //     //   </button>
  //     //   {props.article.data.dislikes}
  //     // </div>
  //   );
  // }

  function LikeDislikeSection(props2) {
    return (
      <div>
        {/*<img*/}
        {/*  src={likeIcon}*/}
        {/*  alt={"like"}*/}
        {/*  width={30}*/}
        {/*  height={30}*/}
        {/*  onClick={() => {*/}
        {/*    // console.log(props2._id);*/}
        {/*    // props.DISPATCH_likeArticle(props2._id);*/}
        {/*    props.DISPATCH_likeArticle(props2._id, props.uuid);*/}
        {/*  }}*/}
        {/*/>*/}

        <button
          // src={likeIcon}
          // alt={"like"}
          // width={30}
          // height={30}
          className="btn btn-secondary"
          onClick={() => {
            // console.log(props2._id);
            // props.DISPATCH_likeArticle(props2._id);
            props.DISPATCH_likeArticle(props2._id, props.uuid);
          }}
        >
          ⬆
        </button>

        {/*<img src={"src/main/resources/static/like.png"} />*/}
        {/*<button*/}
        {/*  className="btn btn-secondary"*/}
        {/*  onClick={() => {*/}
        {/*    console.log(props2._id);*/}
        {/*    props.DISPATCH_likeArticle(props2._id);*/}
        {/*  }}*/}
        {/*>*/}
        {/*  ⬆*/}
        {/*</button>*/}

        {props2.likes}

        <br />

        <button
          className="btn btn-secondary"
          onClick={() => {
            props.DISPATCH_dislikeArticle(props2._id, props.uuid);
          }}
        >
          ⬇
        </button>
        {props2.dislikes}
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
        <div>
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
          <br />
        </div>
      )
    ) : (
      <div className="alert alert-secondary" role="alert">
        Log in to bookmark articles!!
      </div>
    );
  }
}

export default connect(mapStateToProps, mapDispatchToProps)(Article_Page);
