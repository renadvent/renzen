import React, {useState,useEffect} from 'react'
import ArticleArea from "./ArticleArea";
import {connect} from "react-redux";

function Community(props) {

    const [joinStatus, setJoinStatus] = useState(false)

    //determine if user is a member of the community
    useEffect(()=>{
        setJoinStatus(
            props.userNameCom.includes(props.comURL)
        )
    },[props.userNameCom])

    return (
        <div>

            <button style={{textAlign: "center"}}>
                {joinStatus ? "You Are a Member" : "Join Community!"}
            </button>

            <p>{props.userNameObject ? props.userName : "not logged in"}</p>
            {/*<p>{props.userNameCom}</p>*/}
            {/*<p>{props.userURL}</p>*/}
            <h2>Articles in this community:</h2>
            <ul>
                <li>
                    Community Info
                </li>

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

                    <ArticleArea page={props.comURL}
                                 title={"Show Add Article"}/>
                </div>
            </div>

            <div className={"card"}>
                <div className={"card-body"}>
                    <button type="button" className="btn btn-secondary">Ask Question</button>
                </div>
            </div>
        </div>
    )
}

const mapStateToProps = state => {
    return {
        userName: state.userName,
        userURL: state.userURL,
        userNameObject: state.userNameObject,
        userNameCom: state.userNameCom
    }
}

const mapDispatchToProps = dispatch => {
    return {
        // onChangeName: () => dispatch({type: 'CHANGE_NAME'}),
        // onFakeLogin: () => dispatch({
        //     type: 'FAKE_LOGIN',
        //     userURL: "http://localhost:8001/api/users/5f0aba93ba913107ab69627c"
        // })
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(Community)