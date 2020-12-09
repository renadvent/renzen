import React, { useState } from "react";
import { connect } from "react-redux";
import { ArticlePage_StateToProps as mapStateToProps } from "../maps/StateToProps";
import { ArticlePage_mapDispatchToProps as mapDispatchToProps } from "../maps/DispatchToProps";

function CommentSection(commentProps) {
  let [comment, setComment] = useState("");

  const handleChange = (event) => setComment(event.target.value);

  function resetFields() {
    setComment("");
  }

  return (
    <div style={{ align: "center" }}>
      {/*<br />*/}
      {/*<hr />*/}

      {/*<div className="card" style="width: 18rem;">*/}
      {/*    <ul className="list-group list-group-flush">*/}
      {/*        <li className="list-group-item">Cras justo odio</li>*/}
      {/*        <li className="list-group-item">Dapibus ac facilisis in</li>*/}
      {/*        <li className="list-group-item">Vestibulum at eros</li>*/}
      {/*    </ul>*/}
      {/*</div>*/}

      {/*style="width: 18rem;"*/}
      <div className="card">
        <ul className="list-group list-group-flush">
          <li className="list-group-item">
            <form
              onSubmit={(event) => {
                event.preventDefault();
                //commentProps.dispatch(
                commentProps.props.DISPATCH_addComment(
                  commentProps._id,
                  comment,
                  commentProps.props.uuid
                );

                $("#comments" + commentProps.uuid).collapse("show");

                resetFields();

                //)
              }}
            >
              <div className="form-group">
                <div className="input-group">
                  <input type="text" value={comment} onChange={handleChange} />
                  {/*<span>*/}
                  <div className="input-group-append">
                    <button type="submit" className="btn btn-secondary">
                      Post
                    </button>
                  </div>
                </div>
                {/*</span>*/}
              </div>
            </form>
          </li>

          <a
            // className="btn btn-primary"
            data-toggle="collapse"
            href={"#comments" + commentProps.uuid}
            role="button"
            aria-expanded="false"
            aria-controls="collapseExample"
          >
            Show Comments ⬇ ({commentProps.comments.length})
          </a>

          <div id={"comments" + commentProps.uuid} className={"collapse"}>
            {commentProps.comments.map((x, i) => {
              // console.log(x);
              console.log(x);
              return (
                <li
                  key={i}
                  className="list-group-item"
                  //className="d-flex justify-content-center"
                  //style={{ width: "60%" }}
                >
                  {/*//commentsDTO[0].*/}
                  <a
                    href={""}
                    onClick={(e) => {
                      e.preventDefault();
                      commentProps.DISPATCH_openUser(x._links.author.href);
                    }}
                  >
                    {x.authorName}
                  </a>
                  : {x.comment}
                </li>
              );
            })}
          </div>
        </ul>
      </div>
    </div>
  );
}

export default connect(mapStateToProps, mapDispatchToProps)(CommentSection);
