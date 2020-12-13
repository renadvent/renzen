import React, { useEffect, useState } from "react";
import { connect } from "react-redux";

import { CreateArticlePage_StateToProps as mapStateToProps } from "../../maps/StateToProps";
import { CreateArticlePage_mapDispatchToProps as mapDispatchToProps } from "../../maps/DispatchToProps";
import Axios from "axios";
import { Editable_Article_Section } from "./Editable_Article_Section";
import {
  ArticleTabComponentCO,
  UpdateArticlePayload,
} from "../../classes/classes";

import * as store from "../../actions/Store_Actions";

//TODO more like "EDIT ARTICLE PAGE"

function Update_Article_Page(props) {
  const [articleResource, setArticleResource] = useState({});
  const [componentData, setComponentData] = useState([]);
  const [components, setComponents] = useState([]);
  const [updateArticlePayload, setUpdateArticlePayload] = useState(
    new UpdateArticlePayload()
  );
  const [communityName, setCommunityName] = useState("");

  async function OpenDraft() {
    let res = await Axios.get("/getArticleTabComponentCO/" + props.id);
    let article = new ArticleTabComponentCO(res.data);

    console.log(article);

    setCommunityName(article.communityName);

    setArticleResource(article);

    setUpdateArticlePayload({
      ...updateArticlePayload,
      articleName: article.articleName,
      workName: article.workName,
      // community: article.communityID,
      communityID: article.communityID,
      tags: article.tags,
    });

    try {
      if (res.data.articleSectionCOList.length > 0) {
        setComponents(
          res.data.articleSectionCOList.map((section, i) => {
            return (
              <div>
                <Editable_Article_Section
                  index={i}
                  update={setComponentData}
                  text={section}
                />{" "}
                <br />
                <hr />
              </div>
            );
          })
        );
      } else {
        setComponents([
          <div>
            <Editable_Article_Section index={0} update={setComponentData} />{" "}
            <br />
            <hr />
          </div>,
        ]);
      }
    } catch {}
  }

  useEffect(() => {
    OpenDraft().then();
  }, []);

  function handleChange(event) {
    const { value, name } = event.target;
    setUpdateArticlePayload((prevState) => {
      return {
        ...prevState,
        [name]: value,
      };
    });
  }

  function PostArticleLogic() {
    return (
      <button
        type="button"
        onClick={() => {
          if (!check()) return;

          props.dispatch(
            store.DISPATCH_createArticle(
              updateArticlePayload,
              componentData,
              articleResource._id,
              true
            )
          );

          //TODO fix here
          props.DISPATCH_removeOpenTabById(props.id + props.id);
        }}
        className="btn btn-secondary"
      >
        Post Article
      </button>
    );
  }

  function AddSectionLogic() {
    return (
      <button
        type="button"
        className="btn btn-secondary"
        onClick={() => {
          setComponents((x) =>
            x.concat(
              <div>
                <Editable_Article_Section
                  index={componentData.length}
                  update={setComponentData}
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
    );
  }

  function SaveDraftLogic() {
    return (
      <button
        type="button"
        onClick={() => {
          if (!check()) return;

          props.dispatch(
            store.DISPATCH_createArticle(
              updateArticlePayload,
              componentData,
              articleResource._id,
              false
            )
          );

          //TODO fix here
          props.DISPATCH_removeOpenTabById(props.id + props.id);
        }}
        className="btn btn-secondary"
      >
        Save As Draft
      </button>
    );
  }

  function changeCommunityLogic() {
    return (
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
          {props.user.communities.map((x) => {
            return (
              <button
                className="dropdown-item"
                onClick={() => {
                  setUpdateArticlePayload((prevState) => {
                    prevState.communityID = x.objectId;

                    return {
                      ...prevState,
                    };
                  });
                  setCommunityName(x.name);
                }}
              >
                {x.name}
              </button>
            );
          })}
        </div>
      </div>
    );
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
            <h1 className="display-4">Make A Post!</h1>
            <p className="lead">Share your art with the community!</p>
            <hr className="my-4" />
            <p>
              Write as much as you want, splitting it up into sections! Each
              section has a header and content, which helps keep it organized.
            </p>
          </div>

          <div>
            <img src={articleResource.postImageURL} height={500} />
          </div>

          {changeCommunityLogic()}

          {communityName}

          <div className="input-group mb-3">
            <div className="input-group-prepend">
              <span className="input-group-text" id="basic-addon3-a">
                Article Name*:{" "}
              </span>
              <input
                name={"articleName"}
                value={updateArticlePayload.articleName}
                onChange={handleChange}
                type="text"
                className="form-control"
                id="input-articleName"
                aria-describedby="basic-addon3"
              />
            </div>

            <div className="input-group-prepend">
              <span className="input-group-text" id="basic-addon3-b">
                Work Name*:{" "}
              </span>
              <input
                name={"workName"}
                value={updateArticlePayload.workName}
                onChange={handleChange}
                type="text"
                className="form-control"
                id="input-workName"
                aria-describedby="basic-addon3"
              />
            </div>
          </div>

          {components}

          {AddSectionLogic()}
          {"     "}
          {SaveDraftLogic()}
          {"     "}
          {PostArticleLogic()}
        </div>
      </div>
    </div>
  );

  function check() {
    if (
      updateArticlePayload.workName === "" ||
      updateArticlePayload.community === "" ||
      updateArticlePayload.articleName === ""
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
)(Update_Article_Page);
