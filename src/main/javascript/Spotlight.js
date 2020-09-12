import React, { useEffect, useState } from "react";
import { connect } from "react-redux";
import * as store from "./Store_Actions";

const mapStateToProps = (state) => {
  return {
    loadedCommunities: state.homeTabData.stream_communities,
    loadedArticles: state.homeTabData.stream_articles,
    loadedUsers: state.homeTabData.stream_users,

    user: state.user,
    state: state,
  };
};

const mapDispatchToProps = (dispatch) => {
  return {
    DISPATCH_openCommunity: (url) =>
      dispatch(store.DISPATCH_openCommunity(url)),
    DISPATCH_openUser: (url) => dispatch(store.DISPATCH_openUser(url)),
    DISPATCH_openArticle: (url) => dispatch(store.DISPATCH_openArticle(url)),
  };
};

function Spotlight(props) {
  return (
    <div>
      <div className="jumbotron jumbotron-fluid">
        <div className="container">
          <h1 className="display-4">{props.name}</h1>
          <p className="lead">{props.title}</p>
          <div
            id="carouselExampleIndicators"
            className="carousel slide"
            data-ride="carousel"
          >
            <ol className="carousel-indicators">
              {/*<li*/}
              {/*  data-target="#carouselExampleIndicators"*/}
              {/*  data-slide-to="0"*/}
              {/*  className="active"*/}
              {/*></li>*/}
              {/*<li*/}
              {/*  data-target="#carouselExampleIndicators"*/}
              {/*  data-slide-to="1"*/}
              {/*></li>*/}
              {/*<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>*/}
            </ol>

            <div className="carousel-inner">
              <div className="carousel-item active">
                <h1 className="d-block w-100">{props.one}</h1>
              </div>
              <div className="carousel-item">
                <h1 className="d-block w-100">{props.two}</h1>
              </div>
            </div>

            <a
              className="carousel-control-prev"
              href="#carouselExampleIndicators"
              role="button"
              data-slide="prev"
            >
              <span
                className="carousel-control-prev-icon"
                aria-hidden="true"
              ></span>
              <span className="sr-only">Previous</span>
            </a>
            <a
              className="carousel-control-next"
              href="#carouselExampleIndicators"
              role="button"
              data-slide="next"
            >
              <span
                className="carousel-control-next-icon"
                aria-hidden="true"
              ></span>
              <span className="sr-only">Next</span>
            </a>
          </div>
        </div>
      </div>

      {/*<div className="jumbotron jumbotron-fluid">*/}
      {/*  <div className="container">*/}
      {/*    <h1 className="display-4">{props.name}</h1>*/}
      {/*    <p className="lead">Say Hello!</p>*/}
      {/*    <div className="bd-example">*/}
      {/*      <div*/}
      {/*        id="carouselExampleCaptions"*/}
      {/*        className="carousel slide"*/}
      {/*        data-ride="carousel"*/}
      {/*      >*/}
      {/*        <ol className="carousel-indicators">*/}
      {/*          <li*/}
      {/*            data-target="#carouselExampleCaptions"*/}
      {/*            data-slide-to="0"*/}
      {/*            className="active"*/}
      {/*          >*/}
      {/*            Caption*/}
      {/*          </li>*/}

      {/*          <li data-target="#carouselExampleCaptions" data-slide-to="1">*/}
      {/*            Caption*/}
      {/*          </li>*/}

      {/*          <li data-target="#carouselExampleCaptions" data-slide-to="2">*/}
      {/*            Caption*/}
      {/*          </li>*/}
      {/*        </ol>*/}
      {/*        <div className="carousel-inner">*/}
      {/*          <div className="carousel-item active">*/}
      {/*            /!*<img src="..." className="d-block w-100" alt="..." />*!/*/}
      {/*            <div className="d-block w-100" alt="...">*/}
      {/*              Spotlight 1!*/}
      {/*            </div>*/}

      {/*            <div className="carousel-caption d-none d-md-block">*/}
      {/*              <h5>First slide label</h5>*/}
      {/*              <p>*/}
      {/*                Nulla vitae elit libero, a pharetra augue mollis interdum.*/}
      {/*              </p>*/}
      {/*            </div>*/}
      {/*          </div>*/}
      {/*          <div className="carousel-item">*/}
      {/*            <div className="d-block w-100" alt="...">*/}
      {/*              Spotlight!*/}
      {/*            </div>*/}
      {/*            <div className="carousel-caption d-none d-md-block">*/}
      {/*              <h5>Second slide label</h5>*/}
      {/*              <p>*/}
      {/*                Lorem ipsum dolor sit amet, consectetur adipiscing elit.*/}
      {/*              </p>*/}
      {/*            </div>*/}
      {/*          </div>*/}
      {/*          <div className="carousel-item">*/}
      {/*            <div className="d-block w-100" alt="...">*/}
      {/*              Spotlight!*/}
      {/*            </div>*/}
      {/*            <div className="carousel-caption d-none d-md-block">*/}
      {/*              <h5>Third slide label</h5>*/}
      {/*              <p>*/}
      {/*                Praesent commodo cursus magna, vel scelerisque nisl*/}
      {/*                consectetur.*/}
      {/*              </p>*/}
      {/*            </div>*/}
      {/*          </div>*/}
      {/*        </div>*/}
      {/*        <a*/}
      {/*          className="carousel-control-prev"*/}
      {/*          href="#carouselExampleCaptions"*/}
      {/*          role="button"*/}
      {/*          data-slide="prev"*/}
      {/*        >*/}
      {/*          <span*/}
      {/*            className="carousel-control-prev-icon"*/}
      {/*            aria-hidden="true"*/}
      {/*          ></span>*/}
      {/*          <span className="sr-only">Previous</span>*/}
      {/*        </a>*/}
      {/*        <a*/}
      {/*          className="carousel-control-next"*/}
      {/*          href="#carouselExampleCaptions"*/}
      {/*          role="button"*/}
      {/*          data-slide="next"*/}
      {/*        >*/}
      {/*          <span*/}
      {/*            className="carousel-control-next-icon"*/}
      {/*            aria-hidden="true"*/}
      {/*          ></span>*/}
      {/*          <span className="sr-only">Next</span>*/}
      {/*        </a>*/}
      {/*      </div>*/}
      {/*    </div>*/}
      {/*  </div>*/}
      {/*</div>*/}
    </div>
  );
}

export default connect(mapStateToProps, mapDispatchToProps)(Spotlight);
