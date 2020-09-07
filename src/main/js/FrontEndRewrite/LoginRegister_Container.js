import React, { useState } from "react";
import { connect } from "react-redux";
import * as store from "./Store_Actions";

const mapStateToProps = (state) => {
  return {
    open_communities: state.open_communities,
    isLoggedIn: state.isLoggedIn,

    loadedCommunities: state.tabs.open_communities,
    loadedArticles: state.tabs.open_articles,
    loadedUsers: state.tabs.open_profiles,

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
  };
};

function LoginRegister_Container(props) {
  const [userName, setUserName] = useState("");
  const [password, setPassword] = useState("");

  function handleSubmit() {}

  return (
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
          <a className="nav-link" href="#signup" data-toggle="tab" role="tab">
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
            onClick={() => props.DISPATCH_logIn(userName, password)}
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
            onClick={() => props.DISPATCH_register(userName, password)}
          >
            Register
          </button>
        </div>
      </div>
    </div>
  );
}

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(LoginRegister_Container);
