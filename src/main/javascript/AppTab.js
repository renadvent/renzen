import React from "react";
import * as store from "./Store_Actions";
import { connect } from "react-redux";

const mapStateToProps = (state) => {
  return {};
};

const mapDispatchToProps = (dispatch) => {
  return {
    DISPATCH_removeOpenTabById: (id) =>
      dispatch(store.DISPATCH_removeOpenTabById(id)),
  };
};

function AppTab(props) {
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

export default connect(mapStateToProps, mapDispatchToProps)(AppTab);
