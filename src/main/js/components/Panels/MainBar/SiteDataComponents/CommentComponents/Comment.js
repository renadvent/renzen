import React from "react";

function Comment(props) {
  return (
    <div>
      <hr></hr>
      <p className="">{props.comment}</p>
      <p className="info">--{props.author}</p>
        <span>Upvotes+</span>
        <span className="badge badge-primary badge-pill">14</span>
    </div>
  );
}

export default Comment;
