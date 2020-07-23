import Axios from "axios";

export const ACTION_openCommunity = "ACTION_openCommunity"
export const ACTION_openUser = "ACTION_openUser"
export const ACTION_openArticle = "ACTION_openArticle"
export const ACTION_logIn = "ACTION_logIn"
export const ACTION_logOut = "ACTION_logOut"
export const ACTION_register = "ACTION_register"
export const ACTION_createCommunity = "ACTION_createCommunity"

export const ACTION_createArticle = "ACTION_createArticle"
export const ACTION_createPost = "ACTION_createPost"
export const ACTION_createReply = "ACTION_createReply"

//ACTION CREATORS
//HOME-PAGE ACTION DISPATCHES

export function DISPATCH_openCommunity(com_url) {
    return (dispatch) => {
        Axios.get(com_url).then(res => {
            dispatch({
                type: ACTION_openCommunity,
                payload: res.data
            })
        })
    }
}

export function DISPATCH_openUser() {

}

export function DISPATCH_openArticle() {

}

export function DISPATCH_logIn(payload) {
    return (dispatch) => {
        Axios.post("/login/",
            {
                password: payload.password,
                username: payload.username
            }).then(res => {
            //?
            if (res.status) {
                dispatch({
                    type: ACTION_logIn,
                    payload: res.data
                })
            } else {
                dispatch({
                    type: ACTION_logIn,
                    payload: null
                })
            }
        })
    }
}

export function DISPATCH_logOut() {
    return {
        type: ACTION_logOut
    }
}

export function DISPATCH_register(payload) {
    return (dispatch) => {
        Axios.post("/register",
            {
                password: payload.password,
                username: payload.username
            }).then(res => {
            dispatch({
                type: ACTION_register,
                payload: res.data
            })
        })
    }

}

export function DISPATCH_createCommunity(user, payload) {
    return (dispatch, getState) => {
        Axios.post("/createCommunity", {
            params: {
                user: user
            },
            data: {
                name: payload.name,
                description: payload.description
            }
        }).then(res=>{
            dispatch({
                type: ACTION_createCommunity,
                payload: res.data
            })
        })
    }


}

//COMMUNITY-PAGE ACTION DISPATCHES

export function DISPATCH_createArticle() {

}

export function DISPATCH_createPost() {

}

export function DISPATCH_createReply() {

}