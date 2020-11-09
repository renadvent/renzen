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
                <div>completion bar</div>
                <hr />
                <div>vote on tags / polls</div>
                <hr />
                <div>
                  When user uploads a pic from Renzen Ink it takes them to a web
                  page to make polls/text on their post that shows up
                </div>

                <img
                  src={
                    "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a7/React-icon.svg/1200px-React-icon.svg.png"
                  }
                  alt={"temp image"}
                  width={100}
                  height={100}
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
                  <div className={"col-2"}>
                    <LikeDislikeSection
                      id={single._id}
                      likes={single.likes}
                      dislikes={single.dislikes}
                    />
                  </div>
                  <div className={"col"}>
                    <div className={"card"} style={{ textAlign: "left" }}>
                      <div className={"row"}>
                        <div className={"col"}>
                          <div className={"card-header"}>Comments</div>
                          ...
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

  function LikeDislikeSection(props2) {
    return (
      <div>
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
