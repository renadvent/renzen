import React from "react";

import WebsiteTab from "./WebsiteTab";

/*
 * Show the study guide on the right side of the screen
 *
 * */

function BookmarksComponent() {
    return (
        <div id="addTo">
            <ul className="nav nav-tabs">
                <WebsiteTab
                    name={"Bookmarks"}
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
                        {/*<button>Add Note</button>*/}
                        {/*...*/}
                    </div>
                </div>
            </div>
        </div>
    );
}

export default BookmarksComponent;
