import React, { useState } from "react";
import * as store from "./Store_Actions";
import Create_Article_Section from "./Create_Article_Section";
import { connect } from "react-redux";

const mapStateToProps = (state) => {
  return {
    open: state.tabs.open,
    user_communities: state.user.communities,
    user: state.user,
  };
};

const mapDispatchToProps = (dispatch) => {
  return {
    DISPATCH_createArticle: (payload, user, community, sectionData) =>
      dispatch(
        store.DISPATCH_createArticle(payload, user, community, sectionData)
      ),
    DISPATCH_removeOpenTabById: (id) =>
      dispatch(store.DISPATCH_removeOpenTabById(id)),
  };
};

function Create_Article_Page(props) {
  const [thisCommunity, setThisCommunity] = useState(props.id);

  //used for saving
  const [sectionData, setSectionData] = useState([]);

  //used for rendering
  const [sectionsCreated, setSectionsCreated] = useState([
    <div>
      <Create_Article_Section index={0} update={setSectionData} /> <br />
      <hr />
    </div>,
  ]);

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

  return (
    <div
      className="tab-pane fade"
      id={props.href}
      role="tabpanel"
      aria-labelledby="article-edit-tab"
    >
      {/*<CreateArticleArea community={props.id} />*/}

      <div>
        <div>
          <div className="jumbotron">
            <h1 className="display-4">Write an Article!</h1>
            <p className="lead">
              Contribute to the Community and share your knowledge, by
              documenting it here!
            </p>
            <hr className="my-4" />
            <p>
              Write as much as you want, splitting it up into sections! Each
              section has a header and content, which helps keep it organized.
            </p>
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

          {sectionsCreated}

          <button
            type="button"
            className="btn btn-secondary"
            onClick={() => {
              setSectionsCreated((x) =>
                x.concat(
                  <div>
                    <Create_Article_Section
                      index={sectionData.length}
                      update={setSectionData}
                    />
                    <br />
                    <hr />
                  </div>
                )
              );
            }}
          >
            Add Section
          </button>
          <button
            type="button"
            onClick={() => {
              props.DISPATCH_createArticle(
                articleData,
                props.user.id,
                thisCommunity,
                sectionData
              );
              props.DISPATCH_removeOpenTabById(props.id + props.id);
            }}
            className="btn btn-secondary"
          >
            Post Article
          </button>
        </div>
      </div>
    </div>
  );
}

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Create_Article_Page);
