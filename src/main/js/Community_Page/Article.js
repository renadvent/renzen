import React, { useEffect, useState } from "react";
import DiscussionArea from "./DiscussionArea";
import Axios from "axios";
import SaveToButton from "./SaveToButton";

function Article(props) {
  const [loadedContent, setLoadedContent] = useState([]);

  function ArticleReplySection(props) {
    const [showRep, setShowRep] = useState(false);
    return (
      <div>
        <button
          onClick={(e) => {
            setShowRep((prevState) => !prevState);
          }}
          type="submit"
          className="btn btn-dark"
        >
          Show Discussion
        </button>
        <div className={showRep ? "d-block" : "d-none"}>
          {/* <DiscussionArea title={"Section Discussion"}
                                    page={"/api/pages/5efd2911d231b04eecfcd282"}/> : null} */}
          <DiscussionArea
            title={"Section Discussion"}
            page={"/api/pages/5efd2911d231b04eecfcd282"}
          />
        </div>
      </div>
    );
  }

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
                  <div className={"text-right"}>
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
