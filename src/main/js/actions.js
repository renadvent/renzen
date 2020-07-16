import Axios from "axios";
import {Provider,} from "react-redux"
import {applyMiddleware, createStore,compose} from "redux";

export const FAKE_LOG_IN = "FAKE_LOGIN"

/*
action creators for redux
 */

//synchronous
export const fake_login_helper = (res) => {
    return {
        type:FAKE_LOG_IN,
        userNameObject: res
    }
}

//asynchronous
export const fake_login = (userURL) => {
    return dispatch=> {
        Axios.get(userURL).then((loadedUser)=>{
            dispatch(fake_login_helper(loadedUser))
        })
    }

}

//finish adding these here