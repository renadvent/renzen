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
      {/*<div className="tab-pane fade" id="com" role="tabpanel">*/}
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
            <li className="nav-item">
              <a
                className="nav-link"
                id="artDiscTab"
                data-toggle="tab"
                href="#artDisc"
                role="tab"
                aria-controls="profile"
                aria-selected="false"
              >
                +{/*Article Annotations*/}
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
                {/*<DiscussionArea*/}
                {/*    title={"Community Discussion"}*/}
                {/*    page={"/api/pages/5efd2911d231b04eecfcd282"}*/}
                {/*    sharable={false}*/}
                {/*/>*/}
              </div>
              <div
                className="tab-pane fade show active"
                id="artDisc"
                role="tabpanel"
                aria-labelledby="home-tab"
              >
                {/*<div className="btn-group-vertical">*/}
                {/*  <form>*/}
                {/*    <button*/}
                {/*      type="button"*/}
                {/*      className="btn btn-secondary"*/}
                {/*      // onClick={annotateSelection}*/}
                {/*    >*/}
                {/*      Add Annotation*/}
                {/*    </button>*/}
                {/*  </form>*/}
                {/*</div>*/}
                {/*None Yet*/}
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
                    {props.user.logged_in ? "Join Community" : "Hi!"}
                  </button>
                )}
              </button>
            ) : (
              <button
                className="btn btn-dark"
                onClick={() => {
                  $("#home-tab").tab("show");
                }}
              >
                Login
              </button>
            )}

            <h2>Articles in this community:</h2>
            <p>
              Number of Sections in community: {props.payload.numberOfArticles}
            </p>

            {/*<div className={showCreateTopic ? "d-block" : "d-none"}>*/}
            {/*    <CreateTopicArea />*/}
            {/*</div>*/}

            {console.log(props.payload)}
            <ul>
              <Stream
                source={props.payload.article_Article_streamComponentCOList}
                dispatch={props.DISPATCH_openArticle}
              />
            </ul>
            {/*<ul>*/}
            {/*  <li>Community Info</li>*/}

            {/*  <ul>*/}
            {/*    <li>Purpose</li>*/}
            {/*    <li>Affiliated Topics</li>*/}
            {/*    <li>Affiliated Communities</li>*/}
            {/*    <li>Moderators</li>*/}
            {/*  </ul>*/}
            {/*  <li>Getting Started</li>*/}
            {/*  <li>Main Concepts</li>*/}
            {/*  <li>Walkthroughs</li>*/}
            {/*  <li>Study Guides</li>*/}
            {/*  <li>Q&A</li>*/}
            {/*  <li>Reference</li>*/}
            {/*</ul>*/}
            {/*<div className={"card"}>*/}
            {/*  <div className={"card-body"}>*/}
            {/*    <button className="btn btn-dark">add article</button></div>*/}
            {/*</div>*/}

            {/*<div className={"card"}>*/}
            {/*  <div className={"card-body"}>*/}
            {/*    <button type="button" className="btn btn-secondary">*/}
            {/*      Ask Question*/}
            {/*    </button>*/}
            {/*  </div>*/}
            {/*</div>*/}

            {props.user.logged_in ? (
              <button
                type="button"
                onClick={() => setShowCreateArticle(!showCreateArticle)}
                className="btn btn-secondary"
              >
                {showCreateArticle ? "Cancel Article" : "Write New Article"}
                {/*Add Topic to Community+*/}
              </button>
            ) : (
              <button className="btn btn-secondary">
                "Log in to write articles!!"
              </button>
            )}
          </div>
        </div>
      </div>
      <div
        className="tab-pane fade"
        id="contact"
        role="tabpanel"
        aria-labelledby="contact-tab"
      >
        ...
      </div>

      <div>
        {showCreateArticle ? (
          <CreateArticleArea
            show={setShowCreateArticle}
            community={props.payload._id}
          />
        ) : null}
      </div>
    </div>
  );
}

function Stream(props) {


  return (
    <div>
      {console.log("whole")}
      {console.log(props)}
      {props.source._embedded.articleStreamComponentCoes.map((single) => {
        {console.log("ssingle")}
        {console.log(single)}

        //_links.Tab_Version

        //source
        //_embedded.articleStreamComponentCoes[0]._links.Tab_Version
        return (
          <div onClick={()=>props.dispatch(single._links["Tab_Version"].href)}>

            {/*//source._embedded.articleStreamComponentCoes[0]._links.Tab_Version*/}
          <li>
            <a>{single.name}</a>
          </li>
          </div>
        );
      })}
    </div>
  );
}

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(CommunityAppTabContent);
