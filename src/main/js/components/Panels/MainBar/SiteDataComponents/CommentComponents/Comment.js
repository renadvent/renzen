import React from "react";
import CommentOptions from "./CommentOptions";

function Comment(props) {

    let styleAs

    switch (props.content.noteType) {
        case "reply":
            styleAs={
                // width: "100%",
                marginLeft: "6rem"
            }
            break;
        case "def":
            styleAs={
                width: "100%",
            }
            break;
    }

    console.log("ID")
    console.log(props.content)

  return (
    <div>
      <hr></hr>

        <div className={"card"} style={styleAs}>
            <div className={"card-body"} >
                {props.content.content}

            </div>
            <CommentOptions id={props.content.id}
                upVotes={props.content.upVotes}
                downVotes={props.content.downVotes}/>
        </div>


      {/*<p className="">{props.content.comment}</p>*/}
      {/*<p className="info">--{props.content.user}</p>*/}
      {/*  <span>Upvotes+</span>*/}
      {/*  <span className="badge badge-primary badge-pill">14</span>*/}
    </div>
  );
}

export default Comment;
