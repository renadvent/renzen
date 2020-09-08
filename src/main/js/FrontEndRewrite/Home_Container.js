import React, { useEffect, useState } from "react";
import { connect } from "react-redux";
import * as store from "./Store_Actions";
import LoginRegister_Container from "./LoginRegister_Container";

const mapStateToProps = (state) => {
  return {
    // open_communities: state.open_communities,
    isLoggedIn: state.isLoggedIn,

    // loadedCommunities:state.tabs.open_communities,
    // loadedArticles:state.tabs.open_articles,
    // loadedUsers:state.tabs.open_profiles,

    loadedCommunities: state.homeTabData.stream_communities,
    loadedArticles: state.homeTabData.stream_articles,
    loadedUsers: state.homeTabData.stream_users,

    user: state.user,
  };
};

const mapDispatchToProps = (dispatch) => {
  return {
    DISPATCH_openCommunity: (url) => dispatch(store.DISPATCH_openCommunity(url)),
    DISPATCH_openUser: (url) => dispatch(store.DISPATCH_openUser(url)),
    DISPATCH_openArticle: (url) => dispatch(store.DISPATCH_openArticle(url)),

    DISPATCH_logIn: (username, password) =>
      dispatch(
        store.DISPATCH_logIn({ username: username, password: password })
      ),
    DISPATCH_register: (username, password) =>
      dispatch(
        store.DISPATCH_register({ username: username, password: password })
      ),

    DISPATCH_createCommunity: (user, payload) =>
      dispatch(store.DISPATCH_createCommunity(user, payload)),
  };
};

/*
The home page/tab
includes:
login/register actions
open articles/users/communities
shows own profile
create communities
 */

function Home_Container(props) {
  return (
    <div>
      <div className={props.isLoggedIn ? "d-block" : "d-none"}>
        <InLine_NewCommunity />
        {/*<Profile_Container/>*/}
      </div>

      <div className={!props.isLoggedIn ? "d-block" : "d-none"}>
        <LoginRegister_Container />
        {/*<RegisterContainer action={props.onRegister}/>*/}
      </div>

      <div className="container-fluid">
        <div className={"row"}>
          <div className={"col"}>
            <h4>All Users</h4>
            <Stream
              key={"userStream"}
              source={props.loadedUsers}
              dispatch={props.DISPATCH_openUser}
            />
          </div>
          <div className={"col"}>
            <h4>All Articles</h4>
            <Stream
              key={"articleStream"}
              source={props.loadedArticles}
              dispatch={props.DISPATCH_openArticle}
            />
          </div>
          <div className={"col"}>
            <h4>All Communities</h4>
            <Stream
              key={"communityStream"}
              source={props.loadedCommunities}
              dispatch={props.DISPATCH_openCommunity}
            />
          </div>
        </div>
      </div>
    </div>
  );
}

function Stream(props) {
  return (
    <div>
      {/*{console.log("props")}*/}
      {/*{console.log(props)}*/}

      {props.source !== null
        ? props.source.map((single) => {
            // {console.log(single._links)}
            // {console.log(single.name)}
            // {console.log(single._links["Tab_Version"].href)}
            return (
              <div
                onClick={() =>
                  props.dispatch(single._links["Tab_Version"].href)
                }
              >
                <a>{single.name}</a>
              </div>
            );
          })
        : null}
    </div>
  );
}

function InLine_NewCommunity(props) {
  return <div></div>;
}

export default connect(mapStateToProps, mapDispatchToProps)(Home_Container);
