import React from "react";
import * as store from "./Store_Actions";
import { connect } from "react-redux";

/*
Actual tab parts that a user can click on.
Also allows the user to close the tab by clicking on the "x"
 */

const mapStateToProps = (state) => {
  return {};
};

const mapDispatchToProps = (dispatch) => {
  return {
    DISPATCH_removeOpenTabById: (id) =>
      dispatch(store.DISPATCH_removeOpenTabById(id)),
  };
};

function Page_Tab(props) {
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

export default connect(mapStateToProps, mapDispatchToProps)(Page_Tab);
