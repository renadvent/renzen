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
        <div>
          <button className="btn btn-dark" onClick={props.DISPATCH_logOut}>
            Log Out
          </button>

          <button
            className="btn btn-dark"
            //onClick={() => console.log(props.user.user_data)}

            onClick={() =>
              props.DISPATCH_openUser(
                props.user.user_data._links.Tab_Version.href
              )
            }
          >
            Open Your Profile
          </button>
        </div>
      ) : null}
    </nav>
  );
}

export default connect(mapStateToProps, mapDispatchToProps)(Header);
