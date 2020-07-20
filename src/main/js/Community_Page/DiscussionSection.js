import React, { useEffect, useState } from "react";
import Axios from "axios";
import SaveToButton from "./SaveToButton";
import DiscussionReplyOptions from "./DiscussionReplyOptions";
import DiscussionInputArea from "./DiscussionInputArea";
import { replyToQuestionOrAnswer } from "./DiscussionAnswer";
import { DiscussionAnswer } from "./DiscussionAnswer";
import DiscussionReply from "./DiscussionReply";

function DiscussionSection(props) {
  let counter = 52222;

  function getNewId() {
    counter = counter + 1;
    return counter - 1;
  }

  const [loadedAnswers, setLoadedAnswers] = useState([]);
  const [loadedReplies, setLoadedReplies] = useState([]);
  const [loadedQuestion, setLoadedQuestion] = useState("loading question");

  const [replyRes, setReplyRse] = useState();

  const [upVotes, setUpVotes] = useState();
  const [downVotes, setDownVotes] = useState();

  useEffect(() => {
    //console.log("REFER:" + props.refer)

    Axios.get(props.refer).then((section) => {
      //what the input section attaches itself to when posting
      // console.log(section)

      setReplyRse(section.data.question_ref);

      //load Question from reference
      Axios.get(section.data.question_ref).then((question) => {
        setLoadedQuestion(question.data.content);

        setDownVotes(question.data.downVotes);
        setUpVotes(question.data.upVotes);

        //create react objects from comment references
        setLoadedReplies(
          question.data.reply_refs.map((reply_ref) => {
            // console.log("question"+reply_ref)
            return (
              <div>
                <DiscussionReply key={"ia" + getNewId()} refer={reply_ref} />
              </div>
            );
          })
        );
      }); //.then(()=>{console.log(loadedQuestion);console.log(loadedReplies)})

      setLoadedAnswers(
        section.data.answer_refs.map((answer_ref) => {
          //console.log("answer ref: "+answer_ref)
          return (
            <div className={"card"}>
              <div className={"card-body"}>
                <DiscussionAnswer key={"ia" + getNewId()} refer={answer_ref} />
              </div>
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
          <h2>
            {loadedQuestion}
            <SaveToButton />
          </h2>

          <DiscussionReplyOptions
            src={replyRes}
            upVotes={upVotes}
            downVotes={downVotes}
            testUp={(x) => setUpVotes(x)}
            testDown={(x) => setDownVotes(x)}
          />

          {loadedReplies}

          {/*not really section_refs, but reply_ref*/}
          <DiscussionInputArea
            key={"ia" + getNewId()}
            placeholder={"Enter A New Reply"}
            section_refs={replyRes}
            action={replyToQuestionOrAnswer}
          />
          {loadedAnswers}
        </div>
      </div>
    </div>
  );
}

export default DiscussionSection;
