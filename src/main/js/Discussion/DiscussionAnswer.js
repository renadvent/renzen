import Axios from "axios";
import DiscussionReply from "./DiscussionReply";
import SaveToButton from "../Community_Page/SaveToButton";
import DiscussionReplyOptions from "./DiscussionReplyOptions";
import React, { useState, useEffect } from "react";
import DiscussionInputArea from "./DiscussionInputArea";

function DiscussionAnswer(props) {
  const [loadedAnswer, setLoadedAnswer] = useState("loading answer");
  const [loadedReplies, setLoadedReplies] = useState([]);

  const [upVotes, setUpVotes] = useState();
  const [downVotes, setDownVotes] = useState();
  const [replyRes, setReplyRes] = useState(props.refer);

  let counter = 10202020;

  function getNewId() {
    counter = counter + 1;
    return counter - 1;
  }

  ///annotated wrong, copy and paste
  useEffect(() => {
    Axios.get(props.refer).then((reply) => {
      setDownVotes(reply.data.downVotes);
      setUpVotes(reply.data.upVotes);
      setReplyRes(props.refer);
    });
  }, []);

  useEffect(() => {
    //load answer from reference
    Axios.get(props.refer).then((answer) => {
      setLoadedAnswer(answer.data.content);

      // create react objects from comment references
      setLoadedReplies(
        answer.data.reply_refs.map((reply_ref) => {
          return (
            <div>
              <DiscussionReply key={"rep" + getNewId()} refer={reply_ref} />
            </div>
          );
        })
      );
    });
  }, []);

  return (
    <div>
      <div className={"card"}>
        <div className={"card-body"}>
          <h4>
            {loadedAnswer}
            <SaveToButton />
          </h4>
          <DiscussionReplyOptions
            src={replyRes}
            upVotes={upVotes}
            downVotes={downVotes}
            testUp={(x) => setUpVotes(x)}
            testDown={(x) => setDownVotes(x)}
          />
        </div>
      </div>

      {loadedReplies}
      <DiscussionInputArea
        placeholder={"Enter A New Reply"}
        section_refs={props.refer}
        action={replyToQuestionOrAnswer}
      />
    </div>
  );
}

function replyToQuestionOrAnswer(e, refer) {
  if (e.key === "Enter") {
    const content = e.target.value;

    Axios.post("/api/contents/", {
      user: "default",
      content: content,
      upVotes: 0,
      downVotes: 0,
      reply_refs: null,
    })
      .then((postedContent) => {
        Axios.get(refer).then((replyingTo) => {
          //post new content as an answer
          replyingTo.data.reply_refs.push(postedContent.data._links.self.href);
          Axios.patch(refer, {
            reply_refs: replyingTo.data.reply_refs,
          });
        });
      })
      .then(() => {
        //reload()
      });
  }
}

export { DiscussionAnswer, replyToQuestionOrAnswer };
