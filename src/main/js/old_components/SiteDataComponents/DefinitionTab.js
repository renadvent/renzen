import React from "react"

function DefinitionTab(props){
    return(
        <li className="nav-item" role="presentation">
            <a
                className={"nav-link "+props.active}
                id={props.id}
                data-toggle="tab"
                href={props.linkTo}
                role="tab"
                // aria-controls="profile"
                // aria-selected="true"
            >
                {props.name}
            </a>
        </li>
    )
}

export default DefinitionTab