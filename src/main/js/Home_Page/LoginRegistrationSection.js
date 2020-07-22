import React, { useState, useEffect } from "react";
import Axios from "axios";
import { connect } from "react-redux";

const mapDispatchToProps = (dispatch) => {
  return {
      onPostNewCommunity: (payload) =>
          dispatch(post_new_community(payload)),
  };
};

const mapStateToProps = (state) => {
  return {
    userName: state.userName,
    userURL: state.userURL,
  };
};



function LoginRegisterSection(props) {
  const [userName, setUserName] = useState();
  const [password, setPassword] = useState();

  function handleRegister(props) {
    props.preventDefault();

    Axios.post("/api/users", {
      userName: userName,
      password: password,
      communities: ["default"],
      contentList: [],
    });
    setPassword("");
    setUserName("");
  }

  return (
    <div>
      <form action="/login" method="POST">
        <div className="form-group">
          <label htmlFor="email">Email</label>
          <input type="email" className="form-control" name="username" />
        </div>
        <div className="form-group">
          <label htmlFor="password">Password</label>
          <input type="password" className="form-control" name="password" />
        </div>
        <button type="submit" className="btn btn-dark">
          Login
        </button>
      </form>

      <hr />

      <form onSubmit={(event) => handleRegister(event)}>
        <div className="form-group">
          <label htmlFor="email">Email</label>
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
        <button type="submit" className="btn btn-dark">
          Register
        </button>
      </form>
    </div>
  );
}

export default connect(mapStateToProps, mapDispatchToProps) (LoginRegisterSection);
