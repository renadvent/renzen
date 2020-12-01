import React, { useEffect, useState } from "react";
import { connect } from "react-redux";

import { CreateArticlePage_StateToProps as mapStateToProps } from "../maps/StateToProps";
import { CreateArticlePage_mapDispatchToProps as mapDispatchToProps } from "../maps/DispatchToProps";
import Axios from "axios";

function Editable_Article_Section(props) {
  const [info, setInfo] = useState({
    header: "",
    body: "",
    // workName: "",
    // tags: "",
    // pollOptions: "",
  });

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
  const [image, setImage] = useState("");
  const [id, setID] = useState("");

  async function OpenDraft() {
    //Axios.get()
    //TODO Get Draft Info from Server

    alert(OpenFromInkARTICLEID);

    let res = await Axios.get(
      "/getArticleTabComponentCO/" + OpenFromInkARTICLEID
    );

    alert(res);

    let data = res.data;

    // res.data.articleSectionCOList.map(section=>{
    //   return (
    //       <div>
    //
    //       </div>
    //   )
    // })

    //TODO WORKING HERE
    setArticleData({
      articleName: data.articleName,
      workName: data.workName,
      community: data.communityID,
      communityName: data.communityName,
      //articleDescription: data.ar
    });

    console.log(res);
    setImage(res.data.image);
    setID(res.data._id);

    //TODO Get Image Link and section data
  }

  useEffect(() => {
    if (OpenFromInkARTICLEID !== null) {
      //TODO open Draft
      OpenDraft();
    }
  }, []);

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

    workName: "",

    tags: "",
    pollOptions: "",
    image: "",

    community: "",
    communityName: "",
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
            <h1 className="display-4">
              {/*Write an Article!*/}
              Make A Post!
            </h1>
            <p className="lead">
              Share your art with the community!
              {/*Contribute to the Community and share your knowledge, by*/}
              {/*documenting it here!*/}
            </p>
            <hr className="my-4" />
            <p>
              Write as much as you want, splitting it up into sections! Each
              section has a header and content, which helps keep it organized.
            </p>
          </div>

          <div>
            {/*SHOW IMAGE FROM RENZEN INK HERE. data from url: {OpenFromInkImage}*/}
            {/*<img src={OpenFromInkImage} height={500} />*/}
            <img src={image} height={500} />
          </div>

          <label>Post In Community</label>

          <div className="dropdown">
            <button
              className="btn btn-secondary dropdown-toggle"
              type="button"
              id="dropdownMenuButton"
              data-toggle="dropdown"
              aria-haspopup="true"
              aria-expanded="false"
            >
              Select Community To Post In*
            </button>
            <div className="dropdown-menu" aria-labelledby="dropdownMenuButton">
              {/*<button className="dropdown-item" href="#">*/}
              {/*  New Community*/}
              {/*</button>*/}

              {props.user.communities.map((x) => {
                // console.log(articleData);
                // console.log(x);
                return (
                  <button
                    className="dropdown-item"
                    onClick={() =>
                      setArticleData((prevState) => {
                        return {
                          ...prevState,
                          community: x._id,
                          communityName: x.name,
                        };
                      })
                    }
                  >
                    {x.name}
                  </button>
                );
              })}
            </div>
          </div>

          {articleData.communityName}

          <div className="input-group mb-3">
            <div className="input-group-prepend">
              <span className="input-group-text" id="basic-addon3">
                Article Name*:{" "}
              </span>
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

            <div className="input-group-prepend">
              <span className="input-group-text" id="basic-addon3">
                Work Name*:{" "}
              </span>
              <input
                name={"workName"}
                value={articleData.workName}
                onChange={handleChange}
                type="text"
                className="form-control"
                id="basic-url"
                aria-describedby="basic-addon3"
              />
            </div>
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
              if (!check()) return;

              props.DISPATCH_createArticle(
                articleData,
                props.user.id,
                thisCommunity,
                sectionData,
                id
              );
              //TODO fix here
              props.DISPATCH_removeOpenTabById(props.id + props.id);
            }}
            className="btn btn-secondary"
          >
            Post Article
          </button>

          <button className="btn btn-secondary">
            Post/Update/SaveAsDraft (VOID)
          </button>
        </div>
      </div>
    </div>
  );

  function check() {
    if (
      articleData.workName === "" ||
      articleData.community === "" ||
      articleData.articleName === ""
    ) {
      alert("Required Fields not Filled Out!");
      return false;
    }
    return true;
  }
}

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Create_Article_Page);
