import React, { useEffect, useState } from "react";
import { connect } from "react-redux";
import * as store from "./actions/Store_Actions";

const mapStateToProps = (state) => {
  return {
    loadedCommunities: state.reducer.homeTabData.stream_communities,
    loadedArticles: state.reducer.homeTabData.stream_articles,
    loadedUsers: state.reducer.homeTabData.stream_users,

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
            <ol className="carousel-indicators"></ol>

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
    </div>
  );
}

export default connect(mapStateToProps, mapDispatchToProps)(Spotlight);
