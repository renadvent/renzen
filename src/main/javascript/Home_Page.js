import React, { useEffect, useState } from "react";
import { connect } from "react-redux";
import * as store from "./actions/Store_Actions";
import Spotlight from "./Spotlight";
import Axios from "axios";

import { HomePage_StateToProps as mapStateToProps } from "./maps/StateToProps";

// const mapStateToProps = (state) => {
//   return {
//     loadedCommunities: state.reducer.homeTabData.stream_communities,
//     loadedArticles: state.reducer.homeTabData.stream_articles,
//     loadedUsers: state.reducer.homeTabData.stream_users,
//   };
// };

const mapDispatchToProps = (dispatch) => {
  return {
    DISPATCH_openCommunity: (url) =>
      dispatch(store.DISPATCH_openCommunity(url)),
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

function Home_Page(props) {
  return (
    <div>
      <div className="jumbotron">
        <h1 className="display-4">Welcome to Renzen!</h1>
        <p className="lead">
          A site to build communities, write articles, and upload art from
          Renzen Ink! Create an account and get started! (beta)
        </p>
        <hr className="my-4" />
        <p>
          Built with Java Spring Boot on the back end, React and Bootstrap for
          site design, MongoDB for the database, Redux for state management, and
          hosted on Azure.
        </p>
        <button
          className="btn btn-primary btn-lg"
          // href="#"
          role="button"
          onClick={() => {
            Axios.get(
              "/getCommunityStreamComponentCO/5f75f709b95e14569c4332a8"
            ).then((res) => {
              props.DISPATCH_openCommunity(res.data._links["Tab_Version"].href);
            });
          }}
        >
          Click here to learn more about how the site was created
        </button>

        <br />
        <br />

        <a
          className="btn btn-secondary"
          href="https://github.com/renadvent/renzen"
          role="button"
          target={"_blank"}
        >
          Click here to see the source code for Renzen.io on github
        </a>

        <br />
        <br />

        <a
          className="btn btn-secondary"
          href="https://github.com/renadvent/Renzen-Ink"
          role="button"
          target={"_blank"}
        >
          Click here to see the source code for Renzen Ink on github
        </a>
      </div>

      <hr />

      <br />

      <div className="container-fluid" style={{ textAlign: "center" }}>
        <div className={"row"}>
          <div className={"col"}>
            <Spotlight
              name={"Featured Communities"}
              one={"How It's Made"}
              two={"Eri's House"}
              title={"Say Hello!"}
            />
          </div>
          <div className={"col"}>
            <Spotlight
              name={"Featured Articles"}
              one={"Renzen"}
              two={"Learning Curve Post #1"}
              title={"Check It Out!"}
            />
          </div>
        </div>
      </div>

      <hr />

      <br />

      <div>
        <div className="card">
          <div className="card-header">
            <h4>Renzen Ink Demos</h4>
          </div>
          <div>Placeholder for gifs</div>
        </div>
      </div>

      <hr />

      <br />

      <div className="container-fluid" style={{ textAlign: "center" }}>
        <div className={"row"}>
          <div className={"col"}>
            <div className="card">
              <div className="card-header">
                <h4>New Users</h4>
              </div>
              <Stream
                key={"userStream"}
                source={props.loadedUsers}
                dispatch={props.DISPATCH_openUser}
              />
            </div>
          </div>

          <div className={"col"}>
            <div className="card">
              <div className="card-header">
                <h4>New Articles</h4>
              </div>
              <Stream
                key={"articleStream"}
                source={props.loadedArticles}
                dispatch={props.DISPATCH_openArticle}
              />
            </div>
          </div>
          <div className={"col"}>
            <div className="card">
              <div className="card-header">
                <h4>New Communities</h4>
              </div>
              <Stream
                key={"communityStream"}
                source={props.loadedCommunities}
                dispatch={props.DISPATCH_openCommunity}
              />
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

function Stream(props) {
  return (
    <div>
      <ul className="list-group list-group-flush">
        {props.source.map((single) => {
          return (
            <li
              className="list-group-item shadow-sm"
              onClick={() => props.dispatch(single._links["Tab_Version"].href)}
            >
              {single.name}
            </li>
          );
        })}
      </ul>
    </div>
  );
}

export default connect(mapStateToProps, mapDispatchToProps)(Home_Page);
