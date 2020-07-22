import Axios from "axios";
import React, { useEffect, useState } from "react";

function DiscussionReplyOptions(props) {
  let counter = 49448948;

  function getNewId() {
    counter = counter + 1;
    return counter - 1;
  }

  useEffect(() => {
    Axios.patch(props.src, {
      upVotes: props.upVotes,
    }).catch(() => {});
  }, [props.upVotes, props.src]);

  useEffect(() => {
    Axios.patch(props.src, {
      downVotes: props.downVotes,
    }).catch(() => {});
  }, [props.downVotes, props.src]);

  return (
    <div>
      <button
        type={"button"}
        className={"btn btn-outline-secondary"}
        id={getNewId()}
        onClick={() => props.testUp((x) => x + 1)}
      >
        UpVote
      </button>

      {props.upVotes - props.downVotes}

      <button
        id={getNewId()}
        type={"button"}
        className={"btn btn-outline-secondary"}
        onClick={() => props.testDown((x) => x + 1)}
      >
        {" "}
        DownVote
      </button>
      {/*<button id={getNewId()}>Share with another community</button>*/}

      <div className={props.sharable ? "d-block" : "d-none"}>

      <div id={getNewId()} className="dropdown d-inline-block">
        <button
          className="x btn btn-secondary dropdown-toggle"
          type="button"
          id="dropdownMenuButton"
          data-toggle="dropdown"
        >
          Share with another community
        </button>
        <div className="dropdown-menu">
          <a className="dropdown-item" href="#">
            A Community you're in
          </a>
          <a className="dropdown-item" href="#">
            Another Community you're in
          </a>
        </div>
      </div>
    </div>

    </div>



  );
}

export default DiscussionReplyOptions;
