import React from "react"


function WebsiteTab(props){

    // if (props.initial==="active"){
    //
    // }


    return (
        <li className="nav-item" role="presentation">
            <a
                className="nav-link active"
                id={props.name+"Tab"}
                data-toggle="tab"
                href={props.linkTo}
                role="tab"
                aria-controls="profile"
                aria-selected="true"
            >
                {props.name}
            </a>
        </li>
    )
}

export default WebsiteTab