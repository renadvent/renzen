import React from "react";
{
  /*"nav-link active"*/
}

function WebsiteTab(props) {
  return (
    <li className="nav-item" role="presentation">
      <a
        className={"nav-link " + props.active}
        id={props.name + "Tab"}
        data-toggle="tab"
        href={props.linkTo}
        role="tab"
        aria-controls="profile"
        aria-selected="true"
      >
        {props.name}
      </a>
    </li>
  );
}

export default WebsiteTab;
