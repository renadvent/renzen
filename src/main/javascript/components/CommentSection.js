import React, { useState } from "react";

export function CommentSection(commentProps) {
  let [comment, setComment] = useState("");

  const handleChange = (event) => setComment(event.target.value);

  return (
    <div>
      <input value={comment} onChange={handleChange} />
      <button
        onClick={() =>
          props.dispatch(props.DISPATCH_addComment(commentProps._id, comment))
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
