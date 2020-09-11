import React, { useEffect, useState } from "react";
import { connect } from "react-redux";
import * as store from "./Store_Actions";
import LoginRegister_Container from "./LoginRegister_Container";
import Spotlight from "./Spotlight";

const mapStateToProps = (state) => {
  return {
    loadedCommunities: state.homeTabData.stream_communities,
    loadedArticles: state.homeTabData.stream_articles,
    loadedUsers: state.homeTabData.stream_users,
  };
};

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

function Home_Container(props) {
  return (
    <div>
      <div className="jumbotron">
        <h1 className="display-4">Welcome to Renzen!</h1>
        <p className="lead">
          A site to build communities, and write articles! Create an account and
          get started!
        </p>
        <hr className="my-4" />
        <p>
          Built with Java Spring Boot on the back end, React and Bootstrap for
          site design, MongoDB for the database, Redux for state management, and
          hosted on Azure.
        </p>
        <a className="btn btn-primary btn-lg" href="#" role="button">
          Click here to learn more about how the site was created
        </a>

        <br />
        <br />

        <a
          className="btn btn-secondary"
          href="https://github.com/renadvent/renzen"
          role="button"
          target={"_blank"}
        >
          Click here to see the source code on github
        </a>
      </div>

      <hr />

      <br />

      <div className="container-fluid" style={{ textAlign: "center" }}>
        <div className={"row"}>
          <div className={"col"}>
            <Spotlight name={"Featured Communities"} />
          </div>
          <div className={"col"}>
            <Spotlight name={"Featured Articles"} />
          </div>
        </div>
      </div>

      <hr />

      <br />

      <div className="container-fluid" style={{ textAlign: "center" }}>
        <div className={"row"}>
          <div className={"col"}>
            <div className="card" style={{ width: "18rem" }}>
              <div className="card-header">
                <h4>All Users</h4>
              </div>
              <Stream
                key={"userStream"}
                source={props.loadedUsers}
                dispatch={props.DISPATCH_openUser}
              />
            </div>
          </div>

          <div className={"col"}>
            <div className="card" style={{ width: "18rem" }}>
              <div className="card-header">
                <h4>All Articles</h4>
              </div>
              <Stream
                key={"articleStream"}
                source={props.loadedArticles}
                dispatch={props.DISPATCH_openArticle}
              />
            </div>
          </div>
          <div className={"col"}>
            <div className="card" style={{ width: "18rem" }}>
              <div className="card-header">
                <h4>All Communities</h4>
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
        {props.source !== null
          ? props.source.map((single) => {
              return (
                <li
                  className="list-group-item shadow-sm"
                  onClick={() =>
                    props.dispatch(single._links["Tab_Version"].href)
                  }
                >
                  {single.name}
                </li>
              );
            })
          : null}
      </ul>
    </div>
  );
}

export default connect(mapStateToProps, mapDispatchToProps)(Home_Container);
