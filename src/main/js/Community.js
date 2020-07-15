import React, {useState} from 'react'
import CreateArticleArea from "./Article";
import {connect, useSelector} from "react-redux";

function CreateArticle(props) {

}

// <button type="button" className="btn btn-secondary">+</div>

function Community(props) {


    const [joinStatus, setJoinStatus] = useState(false)

    const member = useSelector(state => state.userNameObject)

    function checkIfMember(event, props2) {

        console.log("CHECKING COMMUNITY LOGIN STATUS COMMM: " + JSON.stringify(props2.userNameCom))

            console.log("Included? " + props2.userNameCom.includes("http://localhost:8001/api/communities/5f0c01b2d552840570235567"))

            setJoinStatus(
                props2.userNameCom.includes("http://localhost:8001/api/communities/5f0c01b2d552840570235567")
            )
    }

    return (
        <div>

            {/*<button style={{textAlign: "center"}}*/}

            {/*        onClick={event => checkIfMember(event, props.userNameCom)}*/}

            {/*>{joinStatus ? "You Are a Member" : "Join Community!"}</button>*/}

            <p>{props.userNameObject ? props.userNameObject.userName : "nothing"}</p>
            <p>{props.userNameCom}</p>
            <p>{props.userURL}</p>

            <h2>Articles in this community:</h2>
            {/*<button type="button" className="btn btn-secondary">+Write New Article</button>*/}
            <ul>
                <li>
                    Community Info
                    {/*<button type="button" className="btn-sm btn-secondary">+</button>*/}
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

                    <CreateArticleArea page={"http://localhost:8001/api/communities/5f0c01b2d552840570235567"}
                                       title={"Show Add Article"}/>

                </div>
            </div>

            <div className={"card"}>
                <div className={"card-body"}>
                    <button type="button" className="btn btn-secondary">Ask Question</button>
                </div>
            </div>


            {/*<SiteDataSection title={"Articles"} page={"/api/sections/5f09ecf5347a081bd0dec961"}/>*/}


        </div>


    )

}

// export default Community


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
        onChangeName: () => dispatch({type: 'CHANGE_NAME'}),
        onFakeLogin: () => dispatch({
            type: 'FAKE_LOGIN',
            userURL: "http://localhost:8001/api/users/5f0aba93ba913107ab69627c"
        })
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(Community)