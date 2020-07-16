import React from 'react'
import {connect} from "react-redux";

function Profile(props){
    return(
        <div>
            This is a profile page
            <p>Username: {props.userName}</p>
            <hr/>
            <p>Your Articles</p>
            <p>Your Questions</p>
            <p>Your Comments</p>
            <p>Your Study Guides</p>
            <hr/>
            <p>Your Friends</p>
            <p>Your Messages</p>
        </div>
    )
}


const mapStateToProps = state => {
    return{
        userName: state.userName
    }
}

export default connect(mapStateToProps)(Profile)

//export default Profile