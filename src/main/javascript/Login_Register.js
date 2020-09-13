import React, { useState } from "react";
import { connect } from "react-redux";
import * as store from "./Store_Actions";

const mapStateToProps = (state) => {
  return {
    isLoggedIn: state.isLoggedIn,
    user: state.user,
  };
};

const mapDispatchToProps = (dispatch) => {
  return {
    DISPATCH_openCommunity: () => dispatch(store.DISPATCH_openCommunity()),
    DISPATCH_openUser: () => dispatch(store.DISPATCH_openUser()),
    DISPATCH_openArticle: () => dispatch(store.DISPATCH_openArticle()),

    DISPATCH_logIn: (username, password) =>
      dispatch(
        store.DISPATCH_logIn({ username: username, password: password })
      ),
    DISPATCH_register: (username, password) =>
      dispatch(
        store.DISPATCH_register({ username: username, password: password })
      ),

    DISPATCH_createCommunity: (user, payload) =>
      dispatch(store.DISPATCH_createCommunity(user, payload)),
    DISPATCH_logOut: () => dispatch(store.DISPATCH_logOut()),
  };
};

function Login_Register(props) {
  const [userName, setUserName] = useState("");
  const [password, setPassword] = useState("");

  function resetFields() {
    setPassword("");
    setUserName("");
  }

  return (
    <div>
      {props.user.logged_in ? (
        <button className="btn btn-dark" onClick={props.DISPATCH_logOut}>
          Logout
        </button>
      ) : (
        <div>
          <ul className="nav nav-tabs" role="tablist">
            <li className="nav-item">
              <a
                className="nav-link active"
                href="#login"
                data-toggle="tab"
                role="tab"
              >
                Login
              </a>
            </li>
            <li className="nav-item">
              <a
                className="nav-link"
                href="#signup"
                data-toggle="tab"
                role="tab"
              >
                Sign Up
              </a>
            </li>
          </ul>

          <div className="tab-content" id="myLoginRegisterContent">
            <div
              className="tab-pane fade show active"
              id="login"
              role="tabpanel"
              aria-labelledby="login-tab"
            >
              <div className="form-group">
                <label htmlFor="username">Username</label>
                <input
                  value={userName}
                  onChange={(event) => setUserName(event.target.value)}
                  type="username"
                  className="form-control"
                  name="username"
                />
              </div>
              <div className="form-group">
                <label htmlFor="password">Password</label>
                <input
                  value={password}
                  onChange={(event) => setPassword(event.target.value)}
                  type="password"
                  className="form-control"
                  name="password"
                />
              </div>

              <button
                className="btn btn-dark"
                onClick={() => {
                  props.DISPATCH_logIn(userName, password);
                  resetFields();
                }}
              >
                Login
              </button>
            </div>

            <div
              className="tab-pane fade"
              id="signup"
              role="tabpanel"
              aria-labelledby="signup-tab"
            >
              <div className="form-group">
                <label htmlFor="username">Username</label>
                <input
                  value={userName}
                  onChange={(event) => setUserName(event.target.value)}
                  type="username"
                  className="form-control"
                  name="username"
                />
              </div>
              <div className="form-group">
                <label htmlFor="password">Password</label>
                <input
                  value={password}
                  onChange={(event) => setPassword(event.target.value)}
                  type="password"
                  className="form-control"
                  name="password"
                />
              </div>
              <button
                className="btn btn-dark"
                onClick={() => {
                  props.DISPATCH_register(userName, password);
                  resetFields();
                }}
              >
                Register
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}

export default connect(mapStateToProps, mapDispatchToProps)(Login_Register);