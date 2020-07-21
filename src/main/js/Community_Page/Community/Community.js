import React, { useState, useEffect } from "react";
import Articles from "../../Article/Articles";
import { connect } from "react-redux";
import Axios from "axios";
import CreateArticle from "../../Article/CreateArticle";
import CreateTopicArea from "../CreateTopicArea";

function Community(props) {
  const [joinStatus, setJoinStatus] = useState(false);
  const [showCreateTopic, setShowCreateTopic] = useState(false);

  const [communitySections, setCommunitySections] = useState([]);
  const [numOfSections, setNumOfSections] = useState(0);

  //determine if user is a member of the community
  useEffect(() => {
    setJoinStatus(props.userNameCom.includes(props.comURL));

    Axios.get(props.comURL).then((community) => {
      setCommunitySections(community.data.topics);
      setNumOfSections(community.data.topics.length);
    });
  }, [props.userNameCom]);

  return (
    <div>
      <button style={{ textAlign: "center" }}>
        {joinStatus ? "You Are a Member" : "Join Community!"}
      </button>

      <p>{props.userNameObject ? props.userName : "not logged in"}</p>
      <h2>Articles in this community:</h2>
      <p>Number of Sections in community: {numOfSections}</p>

      <button
        type="button"
        className="btn btn-secondary"
        onClick={() => setShowCreateTopic((prevState) => !prevState)}
      >
        Add Topic to Community+
      </button>

      <div className={showCreateTopic ? "d-block" : "d-none"}>
        <CreateTopicArea />
      </div>

      <ul>
        <li>Community Info</li>

        <ul>
          <li>Purpose</li>
          <li>Affiliated Topics</li>
          <li>Affiliated Communities</li>
          <li>Moderators</li>
        </ul>
        <li>Getting Started</li>
        <li>Main Concepts</li>
        <li>Walkthroughs</li>
        <li>Study Guides</li>
        <li>Q&A</li>
        <li>Reference</li>
      </ul>
      <div className={"card"}>
        <div className={"card-body"}>
          <Articles
            page={props.comURL}
            title={"Add Article to Community+"}
          />
        </div>
      </div>

      <div className={"card"}>
        <div className={"card-body"}>
          <button type="button" className="btn btn-secondary">
            Ask Question
          </button>
        </div>
      </div>
    </div>
  );
}

const mapStateToProps = (state) => {
  return {
    userName: state.userName,
    userURL: state.userURL,
    userNameObject: state.userNameObject,
    userNameCom: state.userNameCom,
  };
};

const mapDispatchToProps = (dispatch) => {
  return {
    // onChangeName: () => dispatch({type: 'CHANGE_NAME'}),
    // onFakeLogin: () => dispatch({
    //     type: 'FAKE_LOGIN',
    //     userURL: "http://localhost:8001/api/users/5f0aba93ba913107ab69627c"
    // })
  };
};

export default connect(mapStateToProps, mapDispatchToProps)(Community);
