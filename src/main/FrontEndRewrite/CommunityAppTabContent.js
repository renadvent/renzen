import React from "react";
import { connect } from "react-redux";
import * as store from "./Store_Actions";
import Profile_Container from "./Profile_Container";
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
  console.log("HREF" + props.href);

  return (
    <div
      className="tab-pane fade"
      id={props.href}
      role="tabpanel"
      //aria-labelledby="profile-tab"
    >

        <p>Members of this Community</p>
        <Stream source={props.payload}/>


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

          <CreateArticleArea/>

        <button className="btn btn-dark">Create Article</button>
      </div>
    </div>
  );
}

//streamCOlist
function Stream(props) {

    console.log(props)

    return (
        <div>
            {props.source.user_streamComponentCOList._embedded.profileStreamComponentCoes !== null
                ? props.source.user_streamComponentCOList._embedded.profileStreamComponentCoes.map((single) => {
                    return (
                        // <div onClick={()=>props.dispatch(single._links["Tab_Version"].href)}>
                        <div>
                            <a>+{single.name}</a>
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
