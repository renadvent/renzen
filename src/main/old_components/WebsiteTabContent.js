import React from "react"

function WebsiteTabContent(props) {


    return (

        <div
            className="tab-pane fade"
            id={props.id}
            role="tabpanel"
            aria-labelledby="profile-tab"
        >
            {" "}
            <iframe
                // title="search"
                src={props.site}
                width="100%"
                height="600"
            />
        </div>
    )
}

export default WebsiteTabContent