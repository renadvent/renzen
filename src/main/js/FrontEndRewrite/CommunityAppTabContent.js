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

        <p>Members of this Community</p>
        <Stream source={props.payload.user_streamComponentCOList._embedded.profileStreamComponentCoes}/>

        {props.user.communities.find(x=>{
            return (x._id===props.payload._id)
        }) ? <div>You are a member of this community</div>:
        <div>You are not a member of this community</div>}

        <p>Articles in this community</p>
        <Stream source={props.payload.article_Article_streamComponentCOList}/>

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

          <CreateArticleArea community={props.payload._id}/>

        <button className="btn btn-dark">Create Article</button>
      </div>
    </div>
  );
}

//streamCOlist
function Stream(props) {

    console.log(props)
    // source.user_streamComponentCOList._embedded.profileStreamComponentCoes

    // {props.source.user_streamComponentCOList._embedded.profileStreamComponentCoes !== null
    //     ? props.source.user_streamComponentCOList._embedded.profileStreamComponentCoes.map((single) => {

    return (
        <div>
            {props.source.map((single)=> {
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
