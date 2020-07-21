import React from "react";
import DiscussionArea from "../../Discussion/DiscussionArea";
import { annotateSelection } from "../../../old_components/functions";
import Community from "./Community";

function CommunityLayout(props) {
  return (
    <div className="tab-pane fade" id="com" role="tabpanel">
      <h1 style={{ textAlign: "center" }}>Community Homepage</h1>
      <hr></hr>
      <div className="row">
        <div className={"col-5"}>
          <ul className="nav nav-tabs" id="myTab" role="tablist">
            <li className="nav-item">
              <a
                className="nav-link active"
                id="comDiscTag"
                data-toggle="tab"
                href="#comDisc"
                role="tab"
                aria-controls="home"
                aria-selected="true"
              >
                Community Discussion
              </a>
            </li>
            <li className="nav-item">
              <a
                className="nav-link"
                id="artDiscTab"
                data-toggle="tab"
                href="#artDisc"
                role="tab"
                aria-controls="profile"
                aria-selected="false"
              >
                Article Annotations
              </a>
            </li>
          </ul>
          <div className="tab-content" id="myTabContent2">
            <div
              className="tab-pane fade show active"
              id="comDisc"
              role="tabpanel"
              aria-labelledby="home-tab"
            >
              <div>
                <h2>Community Updates</h2>
                <ul>
                  <li>Article Requests</li>
                  <li>New Articles</li>
                  <li>New Members</li>
                  <li>Unanswered Questions</li>
                  <li>Accepted Answers</li>
                  <li>Events</li>
                  <li>Questions about the Community</li>
                </ul>
                <DiscussionArea
                  title={"Community Discussion"}
                  page={"/api/pages/5efd2911d231b04eecfcd282"}
                />
              </div>
              <div
                className="tab-pane fade show active"
                id="artDisc"
                role="tabpanel"
                aria-labelledby="home-tab"
              >
                <div className="btn-group-vertical">
                  <form>
                    <button
                      type="button"
                      className="btn btn-secondary"
                      onClick={annotateSelection}
                    >
                      Add Annotation
                    </button>
                  </form>
                </div>
                None Yet
              </div>
            </div>
          </div>
        </div>
        <div className={"col-7"}>
          <Community comURL={props.comURL} />
        </div>
      </div>
      <div
        className="tab-pane fade"
        id="contact"
        role="tabpanel"
        aria-labelledby="contact-tab"
      >
        ...
      </div>
    </div>
  );
}

export default CommunityLayout;
