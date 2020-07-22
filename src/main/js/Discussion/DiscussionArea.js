import React, { useEffect, useState } from "react";
import Axios from "axios";
import SaveToButton from "../Community_Page/SaveToButton";
import DiscussionReplyOptions from "./DiscussionReplyOptions";
import DiscussionReply from "./DiscussionReply";
import DiscussionSection from "./DiscussionSection";
import DiscussionInputArea from "./DiscussionInputArea";

function DiscussionArea(props) {
  const [page_ref, setPageRef] = useState(props.page);

  const [ElementsInSection, setElementsInSection] = useState([]);

  let counter = 10000;

  function getNewId() {
    counter = counter + 1;
    return counter - 1;
  }

  useEffect(() => {
    loadSections(page_ref);
  }, [page_ref]);

  function reload() {
    window.location.reload(false);
    loadSections(page_ref);
  }

  //----------------------------------------------------------------------------

  function askNewQuestion(e) {
    //on "enter", POST text as new CONTENT, add href to content to new SECTION,
    //add href to section to Page

    if (e.key === "Enter") {
      const content = e.target.value;

      //create new content
      Axios.post("/api/contents/", {
        user: "default",
        content: content,
        noteType: "question",
        upVotes: 0,
        downVotes: 0,
        reply_refs: [],
      }).then((postedContent) => {
        //create new section and post ref in
        Axios.post("/api/sections/", {
          question_ref: postedContent.data._links.self.href,
          answer_refs: [],
        }).then((postedSection) => {
          //get sections_refs for page, add new ref, patch
          Axios.get(page_ref)
            .then((page) => {
              //add to beginning
              page.data.section_refs.unshift(
                postedSection.data._links.self.href
              );
              return page;
            })
            .then((page) => {
              Axios.patch(page_ref, {
                section_refs: page.data.section_refs,
              });
            })
            .then(() => {
              reload();
            });
        });
      });
    }
  }

  function AnswerQuestion(e, refer) {
    if (e.key === "Enter") {
      const content = e.target.value;

      Axios.post("/api/contents", {
        user: "default",
        content: content,
        upVotes: 0,
        downVotes: 0,
        reply_refs: [],
      })
        .then((postedContent) => {
          Axios.get(refer).then((question) => {
            //post new content as an answer
            question.data.answer_refs.push(postedContent.data._links.self.href);
            Axios.patch(refer, {
              answer_refs: question.data.answer_refs,
            });
          });
        })
        .then(() => {
          reload();
        });
    }
  }

  //this loads each section for the page
  function loadSections(page) {
    //get sections for page
    Axios.get(page).then((pageObject) => {
      {
        setElementsInSection(
          pageObject.data.section_refs.map((refer) => {
            //console.log("refer: "+refer)
            return (
              <div key={"ia" + getNewId()}>
                <div className={"card"}>
                  <div className={"card-body"}>
                  
                    <DiscussionSection key={"s" + getNewId()} refer={refer}
                    sharable={props.sharable} />

                    <DiscussionInputArea
                      key={"ia" + getNewId()}
                      placeholder={"Answer Question"}
                      section_refs={refer}
                      action={AnswerQuestion}
                    />
                  </div>
                </div>
              </div>
            );
          })
        );
      }
    });
  }

  return (
    <div>
      <h1>{props.title}</h1>

      <div className="input-group">
        <div className="input-group-prepend">
          <span className="input-group-text">
            <select
              className="custom-select"
              // style="width:150px;"
            >
              <option>How Do I...</option>
              <option>What is...</option>
              <option>When Do I...</option>
            </select>
          </span>
        </div>
        <DiscussionInputArea
          key={"ia" + getNewId()}
          placeholder={"Enter A New Question or Note"}
          action={(event) => {
            askNewQuestion(event);
          }}
        />
      </div>

      {ElementsInSection}
    </div>
  );
}

export default DiscussionArea;
