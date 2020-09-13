import React from "react";
import * as store from "./Store_Actions";
import { connect } from "react-redux";

const mapStateToProps = (state) => {
  return {
    user: state.user,
  };
};

const mapDispatchToProps = (dispatch) => {
  return {
    DISPATCH_logOut: () => dispatch(store.DISPATCH_logOut()),
  };
};

function Header_Container(props) {
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

export default connect(mapStateToProps, mapDispatchToProps)(Header_Container);