import Axios from "axios";
import DiscussionReplyOptions from "./DiscussionReplyOptions";
import React, { useEffect, useState } from "react";

function DiscussionReply(props) {
  const [loadedReply, setLoadedReply] = useState("loading reply");

  const [upVotes, setUpVotes] = useState();
  const [downVotes, setDownVotes] = useState();
  const [replyRes, setReplyRes] = useState(props.refer);

  let styleAs = {
    marginLeft: "6rem",
    //width: "100%",
  };

  useEffect(() => {
    Axios.get(props.refer).then((reply) => {
      // console.log("getting replies")

      setDownVotes(reply.data.downVotes);
      setUpVotes(reply.data.upVotes);
      setReplyRes(props.refer);
    });
  }, []);

  useEffect(() => {
    Axios.get(props.refer).then((reply) => {
      // console.log("getting replies")

      setLoadedReply(
        <div>
          <div className={"card"} style={styleAs}>
            <div className={"card-body"}>{reply.data.content}</div>

            <DiscussionReplyOptions
              src={replyRes}
              upVotes={upVotes}
              downVotes={downVotes}
              testUp={(x) => setUpVotes(x)}
              testDown={(x) => setDownVotes(x)}
            />
          </div>
        </div>
      );
    });
  }, [upVotes, downVotes]);

  return <div>{loadedReply}</div>;
}

export default DiscussionReply;
