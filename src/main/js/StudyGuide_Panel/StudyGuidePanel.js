import React from "react";
import defaultText from "./DefaultText";
import WebsiteTab from "../../old_components/WebsiteTab";

/*
 * Show the study guide on the right side of the screen
 *
 * */

function StudyGuidePanel() {
  return (
    <div id="addTo">
      <ul className="nav nav-tabs">
        <WebsiteTab
          name={"Default Study Guide"}
          linkTo={"#AnnoSec"}
          active={"active"}
        />
        <WebsiteTab name={"+"} linkTo={""} active={""} />
      </ul>
      <div className="tab-content" id="myTabContent2">
        <div
          className="tab-pane fade show active"
          id="AnnoSec"
          role="tabpanel"
          aria-labelledby="profile-tab"
        >
          <div id="annoBar">
            <button>Add Note</button>
            {defaultText}
          </div>
        </div>
      </div>
    </div>
  );
}

export default StudyGuidePanel;
