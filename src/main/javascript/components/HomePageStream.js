import React from "react";
import { connect } from "react-redux";
import { HomePage_StateToProps as mapStateToProps } from "../maps/StateToProps";
import { HomePage_mapDispatchToProps as mapDispatchToProps } from "../maps/DispatchToProps";

function HomePageStream(props) {
  return (
    <div>
      <ul className="list-group list-group-flush">
        {props.source.map((single) => {
          console.log(single);
          return (
            <div>
              <div className="card">
                <div className="card-header" style={{ textAlign: "left" }}>
                  By: {single.authorName}
                </div>
                <div>
                  Completion Status{" "}
                  <progress id="file" max="100" value="70">
                    {" "}
                    70%{" "}
                  </progress>{" "}
                  70%
                </div>
                {/*<div>completion bar</div>*/}
                {/*<hr />*/}
                {/*<div>vote on tags / polls</div>*/}
                {/*<hr />*/}
                {/*<div>*/}
                {/*  When user uploads a pic from Renzen Ink it takes them to a web*/}
                {/*  page to make polls/text on their post that shows up*/}
                {/*</div>*/}

                <img
                  className={"mx-auto d-block"}
                  src={single.image}
                  // src={
                  //     "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a7/React-icon.svg/1200px-React-icon.svg.png"
                  // }
                  alt={"temp image"}
                  width={500}
                  height={500}
                />
                <li
                  style={{
                    alight: "center",
                    textAlign: "center",
                  }}
                  className="list-group-item shadow-sm"
                  onClick={() =>
                    props.dispatch(single._links["Tab_Version"].href)
                  }
                >
                  {single.name}
                </li>
                <div className={"row"}>
                  <div className={"col col-md-auto"}>
                    <LikeDislikeSection
                      _id={single._id}
                      likes={single.likes}
                      dislikes={single.dislikes}
                    />
                  </div>
                  <div className={"col"}>
                    <div className={"card"} style={{ textAlign: "left" }}>
                      <div className={"row"}>
                        <div className={"col"}>
                          <div className={"card-header"}>Comments</div>
                          <CommentSection />
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
        })}
      </ul>
    </div>
  );

  function CommentSection(commentProps) {
    return (
      <div>
        <input />
        <br />
        Comments here...
      </div>
    );
  }

  function LikeDislikeSection(props2) {
    return (
      <div style={{ textAlign: "left" }}>
        <button
          className="btn btn-secondary"
          onClick={() => {
            console.log(props2._id);
            props.DISPATCH_likeArticle(props2._id);
          }}
        >
          {/*Likes {props.payload.likes}*/}⬆
        </button>

        {props2.likes}

        <br />

        <button
          className="btn btn-secondary"
          onClick={() => {
            props.DISPATCH_dislikeArticle(props2._id);
          }}
        >
          {/*Dislikes {props.payload.dislikes}*/}⬇
        </button>
        {props2.dislikes}
      </div>
    );
  }
}

export default connect(mapStateToProps, mapDispatchToProps)(HomePageStream);
