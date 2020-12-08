import React, { useEffect, useState } from "react";
import CommentSection from "./CommentSection";

import { HomePage_mapDispatchToProps as mapDispatchToProps } from "../maps/DispatchToProps";
import { connect } from "react-redux";
import {
  Box_StateToProps,
  Box_StateToProps as mapStateToProps,
} from "../maps/StateToProps";
import likeIcon from "../icons/like.png";
import Axios from "axios";
import { async } from "regenerator-runtime";

function Box(props) {
  let single = props.single;

  // console.log(single);

  // let single = props.content;

  const [otherNames, setOtherNames] = useState(null);

  const [orig, setOrig] = useState(props.single._id);

  // useEffect(()=>{
  //
  // })

  useEffect(() => {
    Promise.all(
      single.otherPostsInWorkHex.map(async (x) => {
        // console.log(x);
        let res = await Axios.get(
          "/getArticleField/" + x + "/" + "articleName"
        );

        let createdAt = await Axios.get(
          "/getArticleField/" + x + "/" + "created_at"
        );

        let date = new Date(single.created_at);
        let renderDate = date.getMonth() + "/" + date.getDay();

        // console.log(res.data);
        if (props.content._id === x) {
          return (
            <div className={x === orig ? "bg-light" : null}>
              <li>
                {/*{x === orig ? <span>~~</span> : null}*/}
                {single.articleName} [Posted on: {renderDate}]
                {/*{x === orig ? <span>~~</span> : null}*/}
              </li>
            </div>
          );
        }

        return (
          <div className={x === orig ? "bg-light" : null}>
            <li>
              {/*{x === orig ? <span>~~</span> : null}*/}
              <a
                href={""}
                onClick={(e) => {
                  e.preventDefault();
                  props.DISPATCH_replacePost(
                    props.uuid,
                    // single._id,
                    props.content._id,
                    x
                  );
                }}
              >
                [{res.data}]
              </a>
              [Posted on: {renderDate}]
            </li>
          </div>
        );
      })
    ).then((x) => {
      setOtherNames(x);
    });
  }, [single]);

  return (
    <div>
      {/*<div>TestField: {testField}</div>*/}
      <div className="card">
        <div className="card-header" style={{ textAlign: "left" }}>
          <div className={"row"}>
            <div className={"col"}>
              <a
                href={""}
                onClick={(e) => {
                  e.preventDefault();
                  console.log(single);
                  props.DISPATCH_openUser(single._links["creator"].href);
                }}
              >
                By: {single.creatorName}
              </a>
            </div>

            <div className={"col"}>
              <a
                href={""}
                onClick={(e) => {
                  e.preventDefault();
                  props.DISPATCH_openCommunity(single._links["community"].href);
                }}
              >
                Community: {single.communityName}
              </a>
            </div>
          </div>
        </div>
        {/*<div>*/}
        {/*  Completion Status{" "}*/}
        {/*  <progress id="file" max="100" value="70">*/}
        {/*    {" "}*/}
        {/*    70%{" "}*/}
        {/*  </progress>{" "}*/}
        {/*  70%*/}
        {/*</div>*/}

        <div className={"row"}>
          <div
            className={"col col-md-auto"}
            // style={{ backgroundColor: "#e3e3e3" }}
          >
            <LikeDislikeSection
              _id={single._id}
              likes={single.likes}
              dislikes={single.dislikes}
            />
          </div>

          <div
          // style={{ height: 500 }}
          >
            <img
              className={"img-fluid"}
              //className={"mx-auto d-block"}
              src={props.content.postImageURL}
              // src={single.image}
              alt={"post image"}
              // width={500}
              //height={500}

              width={"75%"}
            />
          </div>
        </div>

        <a
          href={""}
          style={{
            alight: "center",
            textAlign: "center",
          }}
          className="list-group-item shadow-sm"
          onClick={(e) => {
            e.preventDefault();
            props.dispatchOpen(single._links["Tab_Version"].href);
          }}
        >
          {single.articleName}
        </a>

        <div>
          {/*Work Collection: {single.workName} {"     "}*/}
          <div
            style={{
              alight: "left",
              textAlign: "left",
            }}
          >
            {otherNames !== null ? (
              <div>
                <br />
                {/*{" "}*/}
                <div className="card">
                  <div className="card-header" style={{ textAlign: "left" }}>
                    Collection: {single.workName}
                  </div>
                  {/*Versions/Posts of this Work:*/}
                  <ul>{otherNames}</ul>
                </div>
              </div>
            ) : null}
          </div>
        </div>

        <br />

        <div className={"row"}>
          <div className={"col"}>
            <div className={"card"} style={{ textAlign: "left" }}>
              <div className={"row"}>
                <div className={"col"}>
                  <div className={"card-header"}>Comments</div>
                  <CommentSection
                    _id={props.content._id}
                    comments={props.content.comments}
                    props={props}
                  />
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <br />
      <br />
    </div>
  );

  function LikeDislikeSection(props2) {
    return (
      <div style={{ textAlign: "left" }}>
        {/*<img*/}
        {/*  src={likeIcon}*/}
        {/*  alt={"like"}*/}
        {/*  width={30}*/}
        {/*  height={30}*/}
        {/*  onClick={() => {*/}
        {/*    // console.log(props2._id);*/}
        {/*    // props.DISPATCH_likeArticle(props2._id);*/}
        {/*    props.DISPATCH_likeArticle(props2._id, props.uuid);*/}
        {/*  }}*/}
        {/*/>*/}

        <button
          // src={likeIcon}
          // alt={"like"}
          // width={30}
          // height={30}
          className="btn btn-secondary"
          onClick={() => {
            // console.log(props2._id);
            // props.DISPATCH_likeArticle(props2._id);
            props.DISPATCH_likeArticle(props2._id, props.uuid);
          }}
        >
          ⬆
        </button>

        {/*<img src={"src/main/resources/static/like.png"} />*/}
        {/*<button*/}
        {/*  className="btn btn-secondary"*/}
        {/*  onClick={() => {*/}
        {/*    console.log(props2._id);*/}
        {/*    props.DISPATCH_likeArticle(props2._id);*/}
        {/*  }}*/}
        {/*>*/}
        {/*  ⬆*/}
        {/*</button>*/}

        {props2.likes}

        <br />

        <button
          className="btn btn-secondary"
          onClick={() => {
            props.DISPATCH_dislikeArticle(props2._id, props.uuid);
          }}
        >
          ⬇
        </button>
        {props2.dislikes}
      </div>
    );
  }
}

export default connect(Box_StateToProps, mapDispatchToProps)(Box);
