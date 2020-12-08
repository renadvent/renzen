import React from "react";
import { connect } from "react-redux";
import Axios from "axios";

import { HomePage_StateToProps as mapStateToProps } from "../maps/StateToProps";
import { HomePage_mapDispatchToProps as mapDispatchToProps } from "../maps/DispatchToProps";
import HomePageStream from "../components/HomePageStream";
import { CodeProgress } from "../components/CodeProgress";

function Home_Page(props) {
  return (
    <div>
      <div className="jumbotron">
        <h1 className="display-4">Welcome to Renzen (Dev Version)!</h1>
        <div className="lead">
          <div className={"row"}>
            <div className={"col"}>
              A social media community to upload art from Renzen Ink! Create an
              account and get started!
            </div>
          </div>
        </div>
        <hr className="my-4" />

        <div className={"row"}>
          <CodeProgress />
          <div className={"col"}>
            <p>
              Built with Java Spring Boot on the back end, React and Bootstrap
              for site design, MongoDB for the database, Redux for state
              management, and hosted on Azure.
            </p>

            <hr />

            {/*<br />*/}

            <p>
              With just a single click from the desktop application, upload
              whatever you are working on and create a post to share!
            </p>
            <p>Then, on the website, give and receive comments and likes!</p>

            <hr />

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

            <hr />
          </div>

          <div className={"col"}>
            <p>
              Quick Demonstration of how Renzen Ink and Renzen.io work together!
            </p>

            <div className={"embed-responsive embed-responsive-16by9"}>
              <iframe
                className={"embed-responsive-item"}
                width="560"
                height="315"
                src="https://www.youtube.com/embed/6AvbwnFJj-o?rel=0"
                frameBorder="0"
                allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
                allowFullScreen
              ></iframe>
            </div>
          </div>
        </div>
      </div>

      <div>
        <div
          className="container-fluid"
          style={{ textAlign: "center", width: "75%" }}
        >
          <div className={"col"}>
            <HomePageStream
              key={"articleStream"}
              source={props.loadedArticles}
              dispatchOpen={props.DISPATCH_openArticle}
            />
          </div>
          {/*<div className={"col"}>*/}
          {/*  <div className="card">*/}
          {/*    <div className="card-header">*/}
          {/*      <h4>New Communities</h4>*/}
          {/*    </div>*/}
          {/*    <Stream*/}
          {/*      key={"communityStream"}*/}
          {/*      source={props.loadedCommunities}*/}
          {/*      dispatch={props.DISPATCH_openCommunity}*/}
          {/*    />*/}
          {/*  </div>*/}
          {/*</div>*/}
        </div>
      </div>
    </div>
  );
}

function Stream(props) {
  return (
    <div>
      <ul className="list-group list-group-flush">
        {props.source.map((single, i) => {
          return (
            <a
              href={""}
              className="list-group-item shadow-sm"
              onClick={(event) => {
                event.preventDefault();
                props.dispatch(single._links["Tab_Version"].href);
              }}
            >
              {single.name} [^] {single.likes}
            </a>
          );
        })}
      </ul>
    </div>
  );
}

export default connect(mapStateToProps, mapDispatchToProps)(Home_Page);
