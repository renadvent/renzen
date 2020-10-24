import React from "react";
import { connect } from "react-redux";
import * as store from "./actions/Store_Actions";
import { BookmarksTab_StateToProps as mapStateToProps } from "./maps/StateToProps";

// const mapStateToProps = (state) => {
//   return {};
// };

const mapDispatchToProps = (dispatch) => {
  return {
    DISPATCH_removeOpenTabById: (id) =>
      dispatch(store.DISPATCH_removeOpenTabById(id)),
  };
};

function Bookmarks_Tab(props) {
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

export default connect(mapStateToProps, mapDispatchToProps)(Bookmarks_Tab);
