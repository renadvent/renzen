import React, { useState } from "react";
import { connect } from "react-redux";
import * as store from "../actions/Store_Actions";

import { LoginRegister_StateToProps as mapStateToProps } from "../maps/StateToProps";
import { LoginRegister_mapDispatchToProps as mapDispatchToProps } from "../maps/DispatchToProps";

function Login_Register(props) {
  const [userName, setUserName] = useState("");
  const [password, setPassword] = useState("");

  const [email, setEmail] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");

  function resetFields() {
    setPassword("");
    setUserName("");
    setConfirmPassword("");
    setEmail("");
  }

  return (
    <div>
      {props.user.logged_in ? (
        LoginButton()
      ) : (
        <div>
          <ul className="nav nav-tabs" role="tablist">
            {LoginTabLabel()}
            {SignupTabLabel()}
          </ul>

          <div className="tab-content" id="myLoginRegisterContent">
            {LoginTabContent()}
            {SignupTabContent()}
          </div>
        </div>
      )}
    </div>
  );

  function LoginButton() {
    return (
      <button className="btn btn-dark" onClick={props.DISPATCH_logOut}>
        Logout
      </button>
    );
  }

  function LoginTabLabel() {
    return (
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
    );
  }

  function SignupTabLabel() {
    return (
      <li className="nav-item">
        <a className="nav-link" href="#signup" data-toggle="tab" role="tab">
          Sign Up
        </a>
      </li>
    );
  }

  function LoginTabContent() {
    return (
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

          {/*{props.errors.username && (*/}
          {/*  <div className="invalid-feedback">{props.errors.username}</div>*/}
          {/*)}*/}
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

        {/*{props.errors.username && (*/}
        {/*  <div className="invalid-feedback">{props.errors.username}</div>*/}
        {/*)}*/}

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
    );
  }

  function SignupTabContent() {
    return (
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
          <label htmlFor="email">Email</label>
          <input
            value={email}
            onChange={(event) => setEmail(event.target.value)}
            type="email"
            className="form-control"
            name="email"
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

        <div className="form-group">
          <label htmlFor="password">Confirm Password</label>
          <input
            value={confirmPassword}
            onChange={(event) => setConfirmPassword(event.target.value)}
            type="password"
            className="form-control"
            name="confirmPassword"
          />
        </div>

        <button
          className="btn btn-dark"
          onClick={() => {
            props.DISPATCH_register(userName, password, confirmPassword, email);
            //props.DISPATCH_register(userName, email, password, confirmPassword);
            resetFields();
          }}
        >
          Register
        </button>
      </div>
    );
  }
}

export default connect(mapStateToProps, mapDispatchToProps)(Login_Register);
