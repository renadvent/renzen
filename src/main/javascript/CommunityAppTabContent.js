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
  return (
    <div
      className="tab-pane fade"
      id={props.href}
      role="tabpanel"
      aria-labelledby="community-tab"
    >
      <h1 style={{ textAlign: "center" }}>{props.payload.name} Homepage</h1>
      <hr></hr>
      <div className="row">
        <div className={"col-5"}>
          <div id="comDisc" role="tabpanel" aria-labelledby="home-tab">
            <div>
              <div className="jumbotron">
                <h1 className="display-4">Welcome to the Community!</h1>
                <p className="lead">
                  Here you can see the articles and content the community
                  creates and write articles of your own!
                </p>
                <hr className="my-4" />
                <p>
                  More functionality will be coming soon! Such as the ability to
                  comment on articles, and have discussions on the homepage!
                </p>
              </div>
            </div>
          </div>
        </div>
        <div className={"col-5"}>
          <div>
            {/*works... but is a mess of logic*/}
            {props.user.logged_in ? (
              props.user.communities.find((x) => {
                return x._id === props.payload._id;
              }) ? (
                <div className="alert alert-secondary" role="alert">
                  Welcome Back!
                </div>
              ) : (
                <button
                  className="btn btn-secondary"
                  onClick={() => {
                    props.DISPATCH_joinCommunity(
                      props.user.id,
                      props.payload._id
                    );
                  }}
                >
                  {"Join Community!"}
                </button>
              )
            ) : (
              <div className="alert alert-secondary" role="alert">
                Login to write articles and participate in the community!
              </div>
            )}

            <h2>Articles in this community:</h2>
            {/*//TODO fix this. move logic to a function and call function here */}
            {props.user.logged_in ? (
              <button
                type="button"
                onClick={() =>
                  props.DISPATCH_openCreateArticleTab(props.payload._id)
                }
                className="btn btn-secondary"
              >
                {"Write New Article"}
              </button>
            ) : null}
            <br />
            <br />
            <p>
              Number of Articles in community: {props.payload.numberOfArticles}
            </p>

            <ul className="list-group">
              <Stream
                source={props.payload.article_Article_streamComponentCOList}
                dispatch={props.DISPATCH_openArticle}
              />
            </ul>
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
