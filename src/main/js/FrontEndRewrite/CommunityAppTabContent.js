import React from "react";
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
  };
};

function CommunityAppTabContent(props) {
  console.log("app content payload");
  console.log(props.payload);

  return (
    <div
      className="tab-pane fade"
      id={props.href}
      role="tabpanel"
      //aria-labelledby="profile-tab"
    >
      {/*<div className="tab-pane fade" id="com" role="tabpanel">*/}
        <h1 style={{ textAlign: "center" }}>Community Homepage</h1>
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
                  Article Annotations
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
                  <div className="btn-group-vertical">
                    <form>
                      <button
                        type="button"
                        className="btn btn-secondary"
                        // onClick={annotateSelection}
                      >
                        Add Annotation
                      </button>
                    </form>
                  </div>
                  None Yet
                </div>
              </div>
            </div>
          </div>
          <div className={"col-7"}>
            <div>
              <button style={{ textAlign: "center" }}>
                {props.user.communities.find((x) => {
                  return x._id === props.payload._id;
                }) ? (
                  <div>You are a member of this community</div>
                ) : (
                  <div>You are not a member of this community</div>
                )}
              </button>

              <p>
                {props.user.name === "" ? "not logged in" : props.user.name}
              </p>
              <h2>Articles in this community:</h2>
              <p>Number of Sections in community: {0}</p>

              <button type="button" className="btn btn-secondary">
                Add Topic to Community+
              </button>

              {/*<div className={showCreateTopic ? "d-block" : "d-none"}>*/}
              {/*    <CreateTopicArea />*/}
              {/*</div>*/}

              <ul>
                <li>Community Info</li>

                <ul>
                  <li>Purpose</li>
                  <li>Affiliated Topics</li>
                  <li>Affiliated Communities</li>
                  <li>Moderators</li>
                </ul>
                <li>Getting Started</li>
                <li>Main Concepts</li>
                <li>Walkthroughs</li>
                <li>Study Guides</li>
                <li>Q&A</li>
                <li>Reference</li>
              </ul>
              <div className={"card"}>
                <div className={"card-body"}>add article</div>
              </div>

              <div className={"card"}>
                <div className={"card-body"}>
                  <button type="button" className="btn btn-secondary">
                    Ask Question
                  </button>
                </div>
              </div>
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
      {/*</div>*/}

      <p>Members of this Community</p>
      <Stream
        source={
          props.payload.user_streamComponentCOList._embedded
            .profileStreamComponentCoes
        }
      />

      {props.user.communities.find((x) => {
        return x._id === props.payload._id;
      }) ? (
        <div>You are a member of this community</div>
      ) : (
        <div>You are not a member of this community</div>
      )}

      <p>Articles in this community</p>
      <Stream source={props.payload.article_Article_streamComponentCOList} />

      <div>
        <button
          className="btn btn-dark"
          onClick={() => {
            console.log("CLICKED");
            console.log(props.user.id);
            console.log(props.payload._id);
            props.DISPATCH_joinCommunity(props.user.id, props.payload._id);
          }}
        >
          Join Community
        </button>

        <CreateArticleArea community={props.payload._id} />

        <button className="btn btn-dark">Create Article</button>
      </div>
    </div>
  );
}

//streamCOlist
function Stream(props) {
  console.log(props);
  // source.user_streamComponentCOList._embedded.profileStreamComponentCoes

  // {props.source.user_streamComponentCOList._embedded.profileStreamComponentCoes !== null
  //     ? props.source.user_streamComponentCOList._embedded.profileStreamComponentCoes.map((single) => {

  return (
    <div>
      {props.source.map((single) => {
        return (
          // <div onClick={()=>props.dispatch(single._links["Tab_Version"].href)}>
          <div>
            <a>+{single.name}</a>
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
