import React, { useState } from "react";
import { connect } from "react-redux";
import { ArticlePage_StateToProps as mapStateToProps } from "../maps/StateToProps";
import { ArticlePage_mapDispatchToProps as mapDispatchToProps } from "../maps/DispatchToProps";

function CommentSection(commentProps) {
  let [comment, setComment] = useState("");

  const handleChange = (event) => setComment(event.target.value);

  return (
    <div>
      <input value={comment} onChange={handleChange} />
      <button
        onClick={
          () =>
            //commentProps.dispatch(
            commentProps.props.DISPATCH_addComment(commentProps._id, comment)
          //)
        }
      >
        Submit Comment
      </button>
      <br />
      <hr />
      <div>
        {commentProps.comments.map((x) => {
          console.log(x);
          return (
            <div>
              <hr />
              {x.authorName}: {x.comment}
            </div>
          );
        })}
      </div>
    </div>
  );
}

export default connect(mapStateToProps, mapDispatchToProps)(CommentSection);
