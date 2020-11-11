import React from "react";
import * as store from "../actions/Store_Actions";
import { connect } from "react-redux";

import { PageTab_StateToProps as mapStateToProps } from "../maps/StateToProps";
import { PageTab_mapDispatchToProps as mapDispatchToProps } from "../maps/DispatchToProps";

/*
Actual tab parts that a user can click on.
Also allows the user to close the tab by clicking on the "x"
 */

function Page_TabLabels(props) {
  return (
    <li className="nav-item">
      <a
        className="nav-link"
        id={"tab" + props.href}
        data-toggle="tab"
        href={"#" + props.href}
        role="tab"
      >
        {props.name}
        <span
          className="close"
          onClick={() => {
            props.DISPATCH_removeOpenTabById(props.id);
          }}
        >
          ×
        </span>
      </a>
    </li>
  );
}

export default connect(mapStateToProps, mapDispatchToProps)(Page_TabLabels);
