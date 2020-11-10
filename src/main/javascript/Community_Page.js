import React, { useState, useEffect } from "react";
import { connect } from "react-redux";
import * as store from "./actions/Store_Actions";

import { CommunityPage_StateToProps as mapStateToProps } from "./maps/StateToProps";
import { CommunityPage_mapDispatchToProps as mapDispatchToProps } from "./maps/DispatchToProps";
import MessageComponent from "./components/MessageComponent";

function Community_Page(props) {
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
            {joinCommunityLogic()}

            <h2>Articles in this community:</h2>

            {NewArticleButtonLogic()}

            <br />
            <br />
            <p>
              Number of Articles in community: {props.payload.numberOfArticles}
            </p>

            <ul className="list-group">
              <Stream
                source={props.articles}
                dispatch={props.DISPATCH_openArticle}
              />
            </ul>

            <h3>Comments</h3>
            {/*<MessageComponent />*/}
          </div>
        </div>
      </div>
    </div>
  );

  /**
   * loic
   * @returns {JSX.Element}
   */

  function NewArticleButtonLogic() {
    return props.user.logged_in ? (
      <button
        type="button"
        onClick={() => props.DISPATCH_openCreateArticleTab(props.payload._id)}
        className="btn btn-secondary"
      >
        {"Write New Article"}
      </button>
    ) : null;
  }

  function joinCommunityLogic() {
    return props.user.logged_in ? (
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
            props.DISPATCH_joinCommunity(props.user.id, props.payload._id);
          }}
        >
          {"Join Community!"}
        </button>
      )
    ) : (
      <div className="alert alert-secondary" role="alert">
        Login to write articles and participate in the community!
      </div>
    );
  }
}

function Stream(props) {
  console.log("STREAM COMMUNITY NEW PAGE STUFF");
  console.log(props.source);

  return (
    <div>
      {props.source.map((single) => {
        return (
          <div
            onClick={() => props.dispatch(single._links["Tab_Version"].href)}
          >
            <li className={"list-group-item"}>{single.name}</li>
          </div>
        );
      })}
    </div>
  );
}

export default connect(mapStateToProps, mapDispatchToProps)(Community_Page);
