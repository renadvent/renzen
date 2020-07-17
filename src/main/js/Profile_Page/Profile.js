import React from 'react'
import {connect} from "react-redux";

function Profile(props) {
    return (
        <div>
            This is a profile page
            <p>Username: {props.userName}</p>
            <hr/>
            <p>Your Communities</p>

            <ul>
                {props.communities ? props.communities.map(community => {
                    return (
                        <li>{community}</li>
                    )
                }) : <li>You aren't in any communities yet!</li>}
            </ul>

            <p>Your Articles</p>

            <ul>
                {props.articles ? props.articles.map(article => {
                    return (
                        <li>{article}</li>
                    )
                }) : <li>You haven't written any articles yet!</li>}
            </ul>

            <p>Your Questions</p>
            <p>Your Comments</p>
            <p>Your Study Guides</p>
            <hr/>
            <p>Activity from Communities</p>
            <hr/>
            <p>Your Friends</p>
            <p>Your Messages</p>
        </div>
    )
}


const mapStateToProps = state => {
    return {
        userName: state.userName,
        communities: state.userNameCom,
        articles:state.userArticles
    }
}

export default connect(mapStateToProps)(Profile)

//export default Profile