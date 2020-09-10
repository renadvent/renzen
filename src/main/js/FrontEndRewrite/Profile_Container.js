import React, { useState } from "react";
import { connect } from "react-redux";
import * as store from "./Store_Actions";

const mapStateToProps = (state) => {
  return {
    user: state.user,
  };
};

const mapDispatchToProps = (dispatch) => {
  return {
    DISPATCH_logOut: () => dispatch(store.DISPATCH_logOut()),
    DISPATCH_openArticle: (url) => dispatch(store.DISPATCH_openArticle(url)),
    DISPATCH_createCommunity: (creatorID, name) =>
      dispatch(
        store.DISPATCH_createCommunity({ creatorID: creatorID, name: name })
      ),
    DISPATCH_openCommunity: (url) =>
      dispatch(store.DISPATCH_openCommunity(url)),
  };
};

function Profile_Container(props) {
  const [communityName, setCommunityName] = useState("");

  return (
    <div>
      <br />

      <div className="jumbotron">
        <h1 className="display-4">Hello!</h1>
        <p className="lead">Welcome to {props.data.name}'s Profile Page!</p>
        <hr className="my-4" />
        <p>
          Here you can see the articles this user has written, and what
          communities they belong to
        </p>
        <p>
          If you are logged in, you can create new Communities at the bottom of
          the page!
        </p>
      </div>

      <br />

      <div className="container-fluid">
        <div className={"row"}>
          {/*<h4>username: </h4>*/}

          <br />

          {/*{props.user.logged_in ? (*/}
          {/*  <div className={"row"}>*/}
          {/*    <button className="btn btn-dark" onClick={props.DISPATCH_logOut}>*/}
          {/*      Log Out*/}
          {/*    </button>*/}
          {/*  </div>*/}
          {/*) : null}*/}

          <hr />
        </div>

        <div className={"row"}>
          <div className={"col"}>
            <h4>Communities: {props.data.numberOfCommunities} </h4>
            <Stream
              source={
                !jQuery.isEmptyObject(
                  props.data.communityStreamComponentCOList._embedded
                )
                  ? props.data.communityStreamComponentCOList._embedded
                      .communityStreamComponentCoes
                  : []
              }
              dispatch={props.DISPATCH_openCommunity}
            />

            <hr />

            <div>
              <input
                value={communityName}
                onChange={(event) => setCommunityName(event.target.value)}
                type="communityName"
                className="form-control"
                name="communityName"
                placeholder="Enter the name of a new Community!"
              />
              <br />
            </div>

            <button
              className="btn btn-dark"
              onClick={() => {
                props.DISPATCH_createCommunity(props.data._id, communityName);
                setCommunityName("");
              }}
            >
              Create Community+
            </button>
          </div>

          <div className={"col"}>
            {/*<p>number of articles: {props.data.numberOfArticles}</p>*/}
            <h4>Articles: {props.data.numberOfArticles}</h4>
            <Stream
              source={
                !jQuery.isEmptyObject(
                  props.data.articleHomePageCOList._embedded
                )
                  ? props.data.articleHomePageCOList._embedded
                      .articleStreamComponentCoes
                  : null
              }
              dispatch={props.DISPATCH_openArticle}
            />
            <hr />
          </div>
        </div>
      </div>
    </div>
  );
}

function Stream(props) {
  return (
    <ul className="list-group">
      {!jQuery.isEmptyObject(props.source)
        ? props.source.map((single) => {
            return (
              <div
                onClick={() =>
                  props.dispatch(single._links["Tab_Version"].href)
                }
              >
                <li className={"list-group-item"}>
                  <a>+{single.name}</a>
                </li>
              </div>
            );
          })
        : null}
    </ul>
  );
}

export default connect(mapStateToProps, mapDispatchToProps)(Profile_Container);
