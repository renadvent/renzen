import React from "react"
import Profile_Container from "./Profile_Container";

function CommunityAppTabContent(props){

    console.log("HREF"+props.href)

    return(
        <div
            className="tab-pane fade"
            id={props.href}
            role="tabpanel"
            //aria-labelledby="profile-tab"
        >
            Hi

        </div>
    )
}

export default CommunityAppTabContent