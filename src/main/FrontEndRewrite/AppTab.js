import React from "react"


function AppTab(props){
    return(
    <li className="nav-item">
        <a
            className="nav-link"
            //id="profile-tab"
            data-toggle="tab"
            href={"#"+props.href}
            role="tab"
        >
            {props.name}
        </a>
    </li>
    )
}

export default AppTab