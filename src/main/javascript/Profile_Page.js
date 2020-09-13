import React, { useState } from "react";
import Profile_Container from "./Profile_Container";
import * as store from "./Store_Actions";
import { connect } from "react-redux";

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

function Profile_Page(props) {
  const [communityName, setCommunityName] = useState("");

  return (
    <div
      className="tab-pane fade"
      id={props.href}
      role="tabpanel"
      aria-labelledby="profile-tab"
    >
      {/*<Profile_Container data={props.payload} />*/}
      <div>
        <br />

        <div className="jumbotron">
          <h1 className="display-4">Hello!</h1>
          <p className="lead">Welcome to {props.data.name}'s Profile Page!</p>
          <hr className="my-4" />
          <p>
            Here you can see the articles this user has written, and what
            communities they belong to!
          </p>
          <p>
            If you are logged in as this user, you can create communities here!
          </p>
        </div>

        <br />

        {props.user.logged_in && props.user.id === props.data._id ? (
          <div className="input-group mb-3">
            <div className="input-group-prepend">
              <button
                className="btn btn-secondary"
                onClick={() => {
                  props.DISPATCH_createCommunity(props.data._id, communityName);
                  setCommunityName("");
                }}
              >
                Create Community+
              </button>{" "}
            </div>
            <input
              value={communityName}
              onChange={(event) => setCommunityName(event.target.value)}
              type="communityName"
              className="form-control"
              name="communityName"
              placeholder="Enter the name of a new Community!"
            />
          </div>
        ) : null}

        <div className="container-fluid">
          <div className={"row"}>
            <br />
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
            </div>

            <div className={"col"}>
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

export default connect(mapStateToProps, mapDispatchToProps)(Profile_Page);
