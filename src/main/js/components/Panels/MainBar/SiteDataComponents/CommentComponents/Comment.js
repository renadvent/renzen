import React from "react";

function Comment(props) {
  return (
    <div>
      <hr></hr>
      <p className="">{props.comment}</p>
      <p className="info">--{props.author}</p>
    </div>
  );
}

export default Comment;
