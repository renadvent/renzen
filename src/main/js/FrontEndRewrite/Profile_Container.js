import React, { useState } from "react";
import { connect } from "react-redux";
import * as store from "./Store_Actions";

//can be opened as a tab as well as in home page
function Profile_Container(props) {
  const [communityName, setCommunityName] = useState("");

  return (
    <div>
      <div className="container-fluid">
        <div className={"row"}>
          <br />
          <h4>username: {props.data.name}</h4>

          <br />
        </div>
        <div className={"row"}>
          <button className="btn btn-dark" onClick={props.DISPATCH_logOut}>
            Log Out
          </button>
        </div>

        <hr />

        <div className={"row"}>
          <div className={"col"}>
            {/*<p>number of communities: </p>*/}
            <h4>
              Communities you are a part of: {props.data.numberOfCommunities}{" "}
            </h4>
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

            <div>
              <input
                value={communityName}
                onChange={(event) => setCommunityName(event.target.value)}
                type="communityName"
                className="form-control"
                name="communityName"
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
            <h4>Articles you've written: {props.data.numberOfArticles}</h4>
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
            {/*<p>Articles you've written</p>*/}
            {/*<Stream source={props.data.articleStreamComponentCOList} />*/}
            {/*<p>{props.data.url}</p>*/}

            {/*//TODO create community*/}
          </div>
        </div>
      </div>
    </div>
  );
}
//<Stream source={props.loadedCommunities} dispatch={props.DISPATCH_openCommunity}/>
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

function Stream(props) {
  console.log("OF INTEREEST-----------------------------------");
  {
    console.log(props);
  }

  return (
    <ul>
      {!jQuery.isEmptyObject(props.source)
        ? props.source.map((single) => {
            return (
              <div
                onClick={() =>
                  props.dispatch(single._links["Tab_Version"].href)
                }
              >
                <li>
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
