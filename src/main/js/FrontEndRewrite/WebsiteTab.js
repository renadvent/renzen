import React from "react";
import { connect } from "react-redux";
import * as store from "./Store_Actions";

const mapStateToProps = (state) => {
  return {
    // open: state.tabs.open,
  };
};

const mapDispatchToProps = (dispatch) => {
  return {
    DISPATCH_removeOpenTabById: (id) =>
      dispatch(store.DISPATCH_removeOpenTabById(id)),
  };
};

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
        {/*<span className="close"*/}

        {/*onClick={()=>props.DISPATCH_removeOpenTabById(props.)}*/}
        {/*>×</span>*/}
      </a>
    </li>
  );
}

export default connect(mapStateToProps, mapDispatchToProps)(WebsiteTab);
