import React, { useEffect, useState } from "react";
import Axios from "axios";
import DiscussionArea from "./DiscussionArea";
import SaveToButton from "./SaveToButton";
import ArticleSection from "./ArticleSection";
import Article from "./Article";

function ArticleArea(props) {
  //should this be here?
  const [loadedArticles, setLoadedArticles] = useState([]);
  let [comRef, setComRef] = useState(props.page);

  const [numOfArticles, setNumOfArticles] = useState(0);
  // const [communitySections, setCommunitySections] = useState([])
  // const [numOfSections,setNumOfSections] = useState(0)

  //load articles from community
  function loadArticles(communityURL) {
    Axios.get(communityURL)
      .then((community) => {
        // setCommunitySections(community.data.articles)
        // setCommunitySections(community.data.articles.length)

        let articleURLS = [];
        articleURLS = community.data.articles.map((article) => {
          return Axios.get(article);
        });

        return Promise.all(articleURLS);
      })
      .then((articleObjects) => {
        //render Article

        setNumOfArticles(articleObjects.length);

        //create articles from loaded
        setLoadedArticles(() => {
          return articleObjects.map((articleObject) => {
            return (
              <Article source={articleObject} />
              //prevState.concat(<Article source={}/>)
            );
          });
        });
      });
  }

  useEffect(() => {
    loadArticles(comRef);
  }, [comRef]);

  let counter = 1000000;

  function getNewId() {
    counter = counter + 1;
    return counter - 1;
  }

  const [createState, setCreateState] = useState(false);

  //used for saving
  const [sectionData, setSectionData] = useState([]);

  //used for rendering
  const [sectionsCreated, setSectionsCreated] = useState([
    <ArticleSection index={0} update={setSectionData} />,
  ]);

  //gets flipped when button clicked. doesn't matter value
  const [post, setPost] = useState(false);

  //used to save get article information
  const [articleData, setArticleData] = useState({
    articleName: "",
    articleDescription: "",
    articleTags: "",
    articleAddToSection: "",
  });

  function handleChange(event) {
    const { value, name } = event.target;
    setArticleData((prevState) => {
      return {
        ...prevState,
        [name]: value,
      };
    });
  }

  //when post is clicked
  //to save
  useEffect(() => {
    if (post) {
      //create content in database for each section

      //post article
      Axios.post("/api/articles", {
        articleName: articleData.articleName,
        articleDescription: articleData.articleDescription,
        articleTags: articleData.articleTags,
        articleAddToSection: articleData.articleAddToSection,
      })
        //post contents and put links in array
        .then((postedArticle) => {
          let contentArray = sectionData.map((section) => {
            return Axios.post("/api/contents", {
              header: section.header,
              content: section.body,
            });
          });
          return (
            Promise.all(contentArray)
              //add link array to posted article
              .then((postedContentArray) => {
                let linkArray = postedContentArray.map((content) => {
                  // console.log(content)
                  return content.data._links.self.href;
                });

                Axios.patch(postedArticle.data._links.self.href, {
                  contentArray: linkArray,
                });

                //------------------
                //patches community with article
                Axios.get(comRef).then((com) => {
                  let newArticles = [];
                  newArticles = com.data.articles.concat(
                    postedArticle.data._links.self.href
                  );
                  // console.log("before patch")
                  // console.log(newArticles)
                  Axios.patch(comRef, { articles: newArticles });
                });
              })
          );
        });

      setPost(false);
    }
  }, [sectionData, post, articleData]);

  return (
    <div>
      <div className={createState ? "d-block" : "d-none"}>
        <div>
          <label htmlFor="basic-url">{props.title}</label>

          <div className="input-group mb-3">
            <div className="input-group-prepend">
              <label className="input-group-text" htmlFor="inputGroupSelect01">
                Add To Section
              </label>
            </div>
            <select className="custom-select" id="inputGroupSelect01">
              <option selected>Choose...</option>
              <option value="1">One</option>
              <option value="2">Two</option>
              <option value="3">Three</option>
            </select>
          </div>

          <div className="input-group mb-3">
            <div className="input-group-prepend">
              <span className="input-group-text" id="basic-addon3">
                Tags
              </span>
            </div>
            <input
              name={"articleTags"}
              value={articleData.articleTags}
              onChange={handleChange}
              type="text"
              className="form-control"
              id="basic-url"
              aria-describedby="basic-addon3"
            />
          </div>

          <div className="input-group mb-3">
            <div className="input-group-prepend">
              <span className="input-group-text" id="basic-addon3">
                Description
              </span>
            </div>
            <input
              name={"articleDescription"}
              value={articleData.articleDescription}
              onChange={handleChange}
              type="text"
              className="form-control"
              id="basic-url"
              aria-describedby="basic-addon3"
            />
          </div>

          <div className="input-group mb-3">
            <div className="input-group-prepend">
              <span className="input-group-text" id="basic-addon3">
                Article Name:{" "}
              </span>
            </div>
            <input
              name={"articleName"}
              value={articleData.articleName}
              onChange={handleChange}
              type="text"
              className="form-control"
              id="basic-url"
              aria-describedby="basic-addon3"
            />
          </div>

          {sectionsCreated}

          <button
            type="button"
            className="btn btn-secondary"
            onClick={() => {
              setSectionsCreated((x) =>
                x.concat(
                  <ArticleSection
                    index={sectionData.length}
                    update={setSectionData}
                  />
                )
              );
            }}
          >
            Add Section
          </button>

          <button type="button" className="btn btn-secondary">
            Add Image
          </button>

          <button
            type="button"
            className="btn btn-secondary"
            onClick={() => {
              setCreateState(false);
              setSectionsCreated(
                <ArticleSection
                  index={sectionData.length}
                  update={setSectionData}
                />
              );
            }}
          >
            Cancel Article
          </button>

          <button
            type="button"
            onClick={() =>
              setPost((x) => {
                return !x;
              })
            }
            className="btn btn-secondary"
          >
            Post Article
          </button>
        </div>
      </div>

      <p>Number of Articles {numOfArticles}</p>
      <button
        type="button"
        className="btn btn-secondary"
        onClick={() => setCreateState((prevState) => !prevState)}
      >
        {props.title}
      </button>

      {loadedArticles}
    </div>
  );
}

export default ArticleArea;
