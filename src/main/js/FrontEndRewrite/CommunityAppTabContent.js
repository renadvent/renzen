import React, { useState, useEffect } from "react";
import { connect } from "react-redux";
import * as store from "./Store_Actions";
import CreateArticleArea from "./CreateArticleArea";

const mapStateToProps = (state) => {
  return {
    user: state.user,
  };
};

const mapDispatchToProps = (dispatch) => {
  return {
    DISPATCH_joinCommunity: (userId, communityId) =>
      dispatch(
        store.DISPATCH_joinCommunity({
          userId: userId,
          communityId: communityId,
        })
      ),
    DISPATCH_createArticle: () => dispatch(store.DISPATCH_createArticle()),
    DISPATCH_openArticle: (url) => dispatch(store.DISPATCH_openArticle(url)),
    DISPATCH_openCreateArticleTab: (comid) =>
      dispatch(store.DISPATCH_openCreateArticleTab(comid)),
  };
};

function CommunityAppTabContent(props) {
  console.log("app content payload");
  console.log(props.payload);

  const [showCreateArticle, setShowCreateArticle] = useState(false);
  const [childPosted, setChildPosted] = useState(false);

  return (
    <div
      className="tab-pane fade"
      id={props.href}
      role="tabpanel"
      //aria-labelledby="profile-tab"
    >
      <h1 style={{ textAlign: "center" }}>{props.payload.name} Homepage</h1>
      <hr></hr>
      <div className="row">
        <div className={"col-5"}>
          <ul className="nav nav-tabs" id="myTab" role="tablist">
            <li className="nav-item">
              <a
                className="nav-link active"
                id="comDiscTag"
                data-toggle="tab"
                href="#comDisc"
                role="tab"
                aria-controls="home"
                aria-selected="true"
              >
                Community Discussion
              </a>
            </li>
          </ul>

          <div className="tab-content" id="myTabContent2">
            <div
              className="tab-pane fade show active"
              id="comDisc"
              role="tabpanel"
              aria-labelledby="home-tab"
            >
              <div>
                <h2>Community Updates</h2>
                <ul>
                  <li>Article Requests</li>
                  <li>New Articles</li>
                  <li>New Members</li>
                  <li>Unanswered Questions</li>
                  <li>Accepted Answers</li>
                  <li>Events</li>
                  <li>Questions about the Community</li>
                </ul>
              </div>
            </div>
          </div>
        </div>
        <div className={"col-7"}>
          <div>
            {/*works... but is a mess of logic*/}
            {props.user.logged_in ? (
              <button className="btn btn-dark" style={{ textAlign: "center" }}>
                {props.user.communities.find((x) => {
                  return x._id === props.payload._id;
                }) ? (
                  <div>Welcome Back!</div>
                ) : (
                  <button
                    className="btn btn-dark"
                    onClick={() => {
                      props.DISPATCH_joinCommunity(
                        props.user.id,
                        props.payload._id
                      );
                    }}
                  >
                    {"Join Community!"}
                  </button>
                )}
              </button>
            ) : (
              <div className="alert alert-secondary" role="alert">
                Login to write articles and participate in the community!
              </div>
            )}

            <h2>Articles in this community:</h2>
            <p>
              Number of Sections in community: {props.payload.numberOfArticles}
            </p>

            <ul className="list-group">
              <Stream
                source={props.payload.article_Article_streamComponentCOList}
                dispatch={props.DISPATCH_openArticle}
              />
            </ul>

            {/*//TODO fix this. move logic to a function and call function here */}
            {props.user.logged_in ? (
              <button
                type="button"
                onClick={() =>
                  props.DISPATCH_openCreateArticleTab(props.payload._id)
                }
                className="btn btn-secondary"
              >
                {showCreateArticle ? "Cancel Article" : "Write New Article"}
              </button>
            ) : null}
          </div>
        </div>
      </div>
    </div>
  );
}

function Stream(props) {
  return (
    <div>
      {!jQuery.isEmptyObject(props.source._embedded)
        ? props.source._embedded.articleStreamComponentCoes.map((single) => {
            return (
              <div
                onClick={() =>
                  props.dispatch(single._links["Tab_Version"].href)
                }
              >
                <li className={"list-group-item"}>{single.name}</li>
              </div>
            );
          })
        : null}
    </div>
  );
}

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(CommunityAppTabContent);
