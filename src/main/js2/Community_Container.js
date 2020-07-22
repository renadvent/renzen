import React from "react"
import Community from "../js/Community_Page/Community/Community";

function Community_Container(props){

    return(
        <div id={"community-container"} className="tab-pane fade" role="tabpanel">
            <h1 style={{ textAlign: "center" }}>Community Homepage</h1>
            <hr></hr>
            <div className="row">
                <div id={"left-side-bar"} className={"col-5"}>
                    <CommunitySidebar/>
                </div>
                <div id={"main-content"} className={"col-7"}>
                    <CommunityContent/>
                </div>
            </div>
        </div>
    )
}