import React from "react"
import Profile_Container from "./Profile_Container";

function ProfileAppTabContent(props){

    console.log("HREF"+props.href)

    return(
        <div
            className="tab-pane fade"
            id={props.href}
            role="tabpanel"
            //aria-labelledby="profile-tab"
        >

            <Profile_Container data={props.payload}/>

        </div>
    )
}

export default ProfileAppTabContent