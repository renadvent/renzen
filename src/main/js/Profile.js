import React from 'react'
import {connect} from "react-redux";

function Profile(props){
    return(
        <div>
            This is a profile page
            <p>Username: {props.userName}</p>
            <p>Your Articles</p>
            <p>Your Questions</p>
            <p>Your Comments</p>
            <p>Your Study Guides</p>
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