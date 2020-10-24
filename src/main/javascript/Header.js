import React from "react";
import * as store from "./actions/Store_Actions";
import { connect } from "react-redux";

import { Header_StateToProps as mapStateToProps } from "./maps/StateToProps";
import { Header_mapDispatchToProps as mapDispatchToProps } from "./maps/DispatchToProps";

function Header(props) {
  return (
    <nav
      id={"header-component"}
      className="navbar navbar-expand-lg navbar-light bg-light"
    >
      <a className="navbar-brand" href="#">
        Renzen
      </a>

      {props.user.logged_in ? (
        <button className="btn btn-dark" onClick={props.DISPATCH_logOut}>
          Log Out
        </button>
      ) : null}
    </nav>
  );
}

export default connect(mapStateToProps, mapDispatchToProps)(Header);
