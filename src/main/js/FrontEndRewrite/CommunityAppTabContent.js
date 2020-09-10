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
  const [showCreateArticle, setShowCreateArticle] = useState(false);

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
        {/*<div className={"col-5"}>*/}
        {/*  <ul className="nav nav-tabs" id="myTab" role="tablist">*/}
        {/*    <li className="nav-item">*/}
        {/*      <a*/}
        {/*        className="nav-link active"*/}
        {/*        id="comDiscTag"*/}
        {/*        data-toggle="tab"*/}
        {/*        href="#comDisc"*/}
        {/*        role="tab"*/}
        {/*        aria-controls="home"*/}
        {/*        aria-selected="true"*/}
        {/*      >*/}
        {/*        Community Discussion*/}
        {/*      </a>*/}
        {/*    </li>*/}
        {/*  </ul>*/}

        {/*  <div className="tab-content" id="myTabContent2">*/}
        {/*    <div*/}
        {/*      className="tab-pane fade show active"*/}
        {/*      id="comDisc"*/}
        {/*      role="tabpanel"*/}
        {/*      aria-labelledby="home-tab"*/}
        {/*    >*/}
        {/*      <div>*/}
        {/*        <h2>Community Updates</h2>*/}
        {/*        <ul>*/}
        {/*          <li>Article Requests</li>*/}
        {/*          <li>New Articles</li>*/}
        {/*          <li>New Members</li>*/}
        {/*          <li>Unanswered Questions</li>*/}
        {/*          <li>Accepted Answers</li>*/}
        {/*          <li>Events</li>*/}
        {/*          <li>Questions about the Community</li>*/}
        {/*        </ul>*/}
        {/*      </div>*/}
        {/*    </div>*/}
        {/*  </div>*/}
        {/*</div>*/}
        <div className={"col-5"}>
          <div id="comDisc" role="tabpanel" aria-labelledby="home-tab">
            <div>
              <div className="jumbotron">
                <h1 className="display-4">Welcome to the Community!</h1>
                <p className="lead">
                  Here you can see the articles and content the the community
                  creates and write articles of your own!
                </p>
                <hr className="my-4" />
                <p>
                  More functionality will be coming soon! Such as the ability to
                  comment on articles, and have discussions on the homepage!
                </p>
                {/*<a className="btn btn-primary btn-lg" href="#" role="button">*/}
                {/*  Learn more*/}
                {/*</a>*/}
              </div>

              {/*  <h2>Community Updates</h2>*/}
              {/*  <ul>*/}
              {/*    <li>Welcome to the community!</li>*/}
              {/*    /!*<li>Article Requests</li>*!/*/}
              {/*    /!*<li>New Articles</li>*!/*/}
              {/*    /!*<li>New Members</li>*!/*/}
              {/*    /!*<li>Unanswered Questions</li>*!/*/}
              {/*    /!*<li>Accepted Answers</li>*!/*/}
              {/*    /!*<li>Events</li>*!/*/}
              {/*    /!*<li>Questions about the Community</li>*!/*/}
              {/*  </ul>*/}
              {/*</div>*/}
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
                {showCreateArticle ? "Cancel Article" : "Write New Article"}
              </button>
            ) : null}
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
        {/*<div className={"col-3"}></div>*/}
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
