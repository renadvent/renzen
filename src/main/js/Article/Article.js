import React, { useEffect, useState } from "react";
import DiscussionArea from "../Discussion/DiscussionArea";
import Axios from "axios";
import SaveToButton from "../Community_Page/SaveToButton";
import ArticleReplySection from "./ArticleReplySection";

function Article(props) {
  const [loadedContent, setLoadedContent] = useState([]);

  //creates new article
  //also needs to create new PAGE for reply section
  useEffect(() => {
    let art = props.source;
    let contentURLS = art.data.contentArray.map((content) => {
      return Axios.get(content);
    });
    Promise.all(contentURLS).then((contentObjects) => {
      setLoadedContent(() => {
        return (
          <div className={"card"}>
            <div className={"card-body"}>
              <h3>
                {art.data.articleName}
                <span>
                  {/*<div className={"text-right"}>*/}
                  <div>
                    <button
                      type="button"
                      className="btn btn-secondary text-right"
                    >
                      Open in Another Tab
                    </button>
                    <button
                      type="button"
                      className="btn btn-secondary text-right"
                    >
                      Edit Article
                    </button>

                    <button
                        type="button"
                        className="btn btn-secondary text-right"
                    >
                      Show Annotations
                    </button>

                  </div>
                </span>
              </h3>
              <SaveToButton />
              <hr />
              {contentObjects.map((contentObject) => {
                return (
                  <div>
                    <h4>{contentObject.data.header}</h4>
                    <p>{contentObject.data.content}</p>
                  </div>
                );
              })}
              <ArticleReplySection />
            </div>
          </div>
        );
      });
    });
  }, []);

  return <div>{loadedContent}</div>;
}

export default Article;
