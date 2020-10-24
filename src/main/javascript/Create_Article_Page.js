import React, { useEffect, useState } from "react";
import * as store from "./actions/Store_Actions";
import { connect } from "react-redux";

const mapStateToProps = (state) => {
  return {
    open: state.reducer.tabs.open,
    user_communities: state.reducer.user.communities,
    user: state.reducer.user,
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

function Editable_Article_Section(props) {
  const [info, setInfo] = useState({ header: "", body: "" });

  //adds info state to parent state array on first render
  useEffect(() => {
    props.update((prevState) => prevState.concat(info));
  }, []);

  //links input forms to react states
  //changes "info" which will trigger another effect
  function handleChange(event) {
    const { value, name } = event.target;
    setInfo((prevState) => {
      return {
        ...prevState,
        [name]: value,
      };
    });
  }

  //uses function from parent to update parent array of sections
  //when info is updated
  useEffect(() => {
    props.update((x) => {
      let dup = x;
      dup[props.index] = info;
      return dup;
    });
  }, [info]);

  return (
    <div>
      <div className="input-group mb-3">
        <div className="input-group-prepend">
          <span className="input-group-text">Heading</span>
        </div>
        <input
          type="text"
          name={"header"}
          value={info.header}
          onChange={handleChange}
          rows={5}
          className="form-control"
          aria-label="Enter question"
        />
      </div>

      <div className="input-group">
        <div className="input-group-prepend">
          <span className="input-group-text">Content</span>
        </div>
        <textarea
          name={"body"}
          value={info.body}
          onChange={handleChange}
          rows={10}
          className="form-control"
          aria-label="With textarea"
        />
      </div>
    </div>
  );
}

function Create_Article_Page(props) {
  const [thisCommunity, setThisCommunity] = useState(props.id);

  //used for saving
  const [sectionData, setSectionData] = useState([]);

  //used for rendering
  const [sectionsCreated, setSectionsCreated] = useState([
    <div>
      <Editable_Article_Section index={0} update={setSectionData} /> <br />
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
                    <Editable_Article_Section
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
          {"     "}
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
