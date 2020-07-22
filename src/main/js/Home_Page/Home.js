import React, { useState, useEffect } from "react";
import Axios from "axios";
import Articles from "../Article/Articles";
import * as actionTypes from "../Store/actions";
import {fake_login, fake_login_synchronous, log_in, post_new_community} from "../Store/actions";

import {connect, useDispatch} from "react-redux";
import ListSection from "./ListSection";
import LoginRegisterSection from "./LoginRegistrationSection";
import CreateCommunitySection from "./CreateCommunitySection";

const mapDispatchToProps = (dispatch) => {
  return {
    // onChangeName: ()=> dispatch({type:'CHANGE_NAME'}),
    //onFakeLogin: ()=> dispatch({type:'FAKE_LOGIN', userURL:"http://localhost:8001/api/users/5f0aba93ba913107ab69627c"}),
    onFakeLogin: () =>
      dispatch(
        fake_login("http://localhost:8001/api/users/5f0aba93ba913107ab69627c")
      ),
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

function Home(props) {

    const dispatch = useDispatch();

  const [createCom, setCreateCom] = useState(false);

  function handleNewCommunity(props) {
      props.onPostNewCommunity({creatorName:props.userName,comName});
  }

  function handleCreateComClick() {
    setCreateCom((prevState) => !prevState);
  }

  return (
    <div>
      <p>Redux Value:{props.userName}</p>
      <button
        onClick={() => {
          // props.onChangeName();
          props.onFakeLogin();
        }}
      >
        Fake Login by Redux
      </button>

      <button
        onClick={handleCreateComClick}
        onClick={handleNewCommunity}
        type="submit"
        className="btn btn-dark"
      >
        Create New Community +
      </button>
      <div className={createCom ? "d-block" : "d-none"}>
        <CreateCommunitySection />
      </div>
      <LoginRegisterSection />
      <ListSection />
    </div>
  );
}

export default connect(mapStateToProps, mapDispatchToProps)(Home);
