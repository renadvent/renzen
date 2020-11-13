import React, { useState } from "react";
import { connect } from "react-redux";
import { HomePage_StateToProps as mapStateToProps } from "../maps/StateToProps";
import { HomePage_mapDispatchToProps as mapDispatchToProps } from "../maps/DispatchToProps";
import CommentSection from "./CommentSection";

function HomePageStream(props) {
  function NextLogic(single) {
    console.log(single.otherPostsInWork);

    //TODO
    //replace this component with next work
  }

  return (
    <div>
      <ul className="list-group list-group-flush">
        {props.source.map((single) => {
          //console.log(single);
          return (
            <div>
              <div className="card">
                <div className="card-header" style={{ textAlign: "left" }}>
                  By: {single.authorName}
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

                  <img
                    className={"mx-auto d-block"}
                    src={single.image}
                    alt={"post image"}
                    // width={500}
                    height={500}
                  />
                </div>
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

                <div>
                  {/*<button className="btn btn-secondary">Previous</button>*/}
                  {/*<button*/}
                  {/*  className="btn btn-secondary"*/}
                  {/*  onClick={() => NextLogic(single)}*/}
                  {/*>*/}
                  {/*  Next*/}
                  {/*</button>*/}
                  Post Collection: {single.workName} {"     "}
                  {single.otherPostsInWork.map((x) => {
                    return <span>+</span>;
                  })}
                </div>
                {/*<div>*/}
                {/*  (pluses are number of posts in work. on click will let you go*/}
                {/*  through them)*/}
                {/*</div>*/}

                <div className={"row"}>
                  <div className={"col"}>
                    <div className={"card"} style={{ textAlign: "left" }}>
                      <div className={"row"}>
                        <div className={"col"}>
                          <div className={"card-header"}>Comments</div>
                          <CommentSection
                            _id={single._id}
                            comments={single.comments}
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
        })}
      </ul>
    </div>
  );

  // function CommentSection(commentProps) {
  //   let [comment, setComment] = useState("");
  //
  //   const handleChange = (event) => setComment(event.target.value);
  //
  //   return (
  //     <div>
  //       <input value={comment} onChange={handleChange} />
  //       <button
  //         onClick={() =>
  //           props.dispatch(props.DISPATCH_addComment(commentProps._id, comment))
  //         }
  //       >
  //         Submit Comment
  //       </button>
  //       <br />
  //       <hr />
  //       <div>
  //         {commentProps.comments.map((x) => {
  //           console.log(x);
  //           return (
  //             <div>
  //               <hr />
  //               {x.authorName}: {x.comment}
  //             </div>
  //           );
  //         })}
  //       </div>
  //     </div>
  //   );
  // }

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
          ⬆
        </button>

        {props2.likes}

        <br />

        <button
          className="btn btn-secondary"
          onClick={() => {
            props.DISPATCH_dislikeArticle(props2._id);
          }}
        >
          ⬇
        </button>
        {props2.dislikes}
      </div>
    );
  }
}

export default connect(mapStateToProps, mapDispatchToProps)(HomePageStream);
