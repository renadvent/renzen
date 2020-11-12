import React, { useEffect, useState } from "react";
import { connect } from "react-redux";
import Axios from "axios";

import { HomePage_StateToProps as mapStateToProps } from "../maps/StateToProps";
import { HomePage_mapDispatchToProps as mapDispatchToProps } from "../maps/DispatchToProps";
import HomePageStream from "../components/HomePageStream";

function Home_Page(props) {
  return (
    <div>
      <div className="jumbotron">
        <h1 className="display-4">Welcome to Renzen!</h1>
        <p className="lead">
          A social media site to build communities, write articles, and upload
          art from Renzen Ink! Create an account and get started! (beta)
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

      <hr />

      <br />

      <div
        className="container-fluid"
        style={{ textAlign: "center", width: "75%" }}
      >
        <div className={"col"}>
          <HomePageStream
            key={"articleStream"}
            source={props.loadedArticles}
            dispatch={props.DISPATCH_openArticle}
          />
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
              {single.name} [^] {single.likes}
            </li>
          );
        })}
      </ul>
    </div>
  );
}

export default connect(mapStateToProps, mapDispatchToProps)(Home_Page);