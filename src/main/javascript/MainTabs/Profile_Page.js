import React, { useState } from "react";
import * as store from "../actions/Store_Actions";
import { connect } from "react-redux";

import { ProfilePage_StateToProps as mapStateToProps } from "../maps/StateToProps";
import { ProfilePage_mapDispatchToProps as mapDispatchToProps } from "../maps/DispatchToProps";
import {
  DISPATCH_deleteImageFromProfile,
  DISPATCH_deletePost,
} from "../actions/Store_Actions";
import { DISPATCH_openEditArticleTab } from "../actions/ArticleActions";

function Profile_Page(props) {
  const [communityName, setCommunityName] = useState("");

  return (
    <div
      className="tab-pane fade"
      id={props.href}
      role="tabpanel"
      aria-labelledby="profile-tab"
    >
      <div>
        <br />

        {ProfileHeader()}

        <br />

        {props.user.logged_in && props.user.id === props.data._id
          ? CreateCommunityButton()
          : null}

        <div className="container-fluid">{PublicProfileContents()}</div>
      </div>
    </div>
  );

  function ProfileHeader() {
    return (
      <div>
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
      </div>
    );
  }

  function PublicProfileContents() {
    return (
      <div>
        <div className={"row"}>
          <br />
          <hr />
        </div>

        <div className={"row"}>
          <div className={"col"}>
            <h4>Communities: {props.data.numberOfCommunities} </h4>
            <StreamCommunity
              source={props.communities}
              dispatch={props.DISPATCH_openCommunity}
            />

            <hr />
          </div>

          <div className={"col"}>
            <h4>Articles: {props.data.numberOfArticles}</h4>
            <StreamArticleConnect
              source={props.articles}
              dispatchSent={props.DISPATCH_openArticle}
              props={props}
            />
            <hr />

            {props.user.logged_in && props.user.id === props.data._id ? (
              <div>
                <h4>Drafts:</h4>
                {/*{console.log("props.data")}*/}
                {/*{console.log(props.data)}*/}
                <StreamArticleConnect
                  source={props.drafts}
                  dispatchSent={props.DISPATCH_openArticle}
                  props={props}
                />
              </div>
            ) : null}
            <hr />
          </div>
        </div>
      </div>
    );
  }

  function CreateCommunityButton() {
    return (
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
    );
  }
}

//receives dispatch
const StreamArticleConnect = connect(null, null)(StreamArticle);

function StreamArticle(props) {
  // console.log(props);

  return (
    <ul className="list-group">
      {props.source.map((single) => {
        return (
          <li className={"list-group-item"}>
            <a
              href={""}
              onClick={(event) => {
                event.preventDefault();
                props.dispatchSent(single._links["Tab_Version"].href);
              }}
            >
              +{single.articleName}
            </a>

            {props.props.user.logged_in &&
            props.props.user.id === props.props.data._id ? (
              <span>
                <button
                  className="btn btn-secondary"
                  style={{ marginRight: "10px", marginLeft: "10px" }}
                  onClick={() => {
                    // alert("DELETING CLICKD");
                    props.dispatch(DISPATCH_deletePost(single._id));
                  }}
                >
                  Delete Article
                </button>
                <button
                  onClick={() => {
                    props.dispatch(DISPATCH_openEditArticleTab(single._id));
                  }}
                  className="btn btn-secondary"
                  style={{ marginRight: "10px", marginLeft: "10px" }}
                >
                  Edit Article
                </button>
              </span>
            ) : null}
          </li>
        );
      })}
    </ul>
  );
}

function StreamCommunity(props) {
  // console.log(props);

  return (
    <ul className="list-group">
      {props.source.map((single) => {
        return (
          <li className={"list-group-item"}>
            <a
              href={""}
              onClick={(event) => {
                event.preventDefault();
                props.dispatch(single._links["Tab_Version"].href);
              }}
            >
              +{single.name}
            </a>
            {/*<button>Delete Article</button>*/}
          </li>
        );
      })}
    </ul>
  );
}

export default connect(mapStateToProps, mapDispatchToProps)(Profile_Page);
