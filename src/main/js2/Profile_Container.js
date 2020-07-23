import React from "react"
import {connect} from "react-redux";
import * as store from "./Store_Actions"

//can be opened as a tab as well as in home page
function Profile_Container(props) {
    return(
        <div>
            <p>{props.user.name}</p>
            <p>{props.user.url}</p>

            <button onClick={props.DISPATCH_logOut}>Log Out</button>
        </div>
    )
}

const mapStateToProps = (state) => {
    return{
        user:state.user
    }
}

const mapDispatchToProps = (dispatch) => {
    return{
        DISPATCH_logOut:()=>dispatch(store.DISPATCH_logOut())
    }
}

export default connect(mapStateToProps, mapDispatchToProps) (Profile_Container)