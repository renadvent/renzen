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

export const ACTION_init="ACTION_init"

//ACTION CREATORS
//HOME-PAGE ACTION DISPATCHES


export function DISPATCH_init(){
    return(dispatch) => {


        Axios.get("/getHomeStreams").then(res=>{
            console.log(res)
            console.log(res.data._embedded.collectionModels[1]._embedded)



            console.log("UNDER")

            let base = res.data._embedded.collectionModels

            console.log(base[0])
            let articles = (!Object.keys(base[0]).length ? null : base[0]._embedded.articleStreamComponentCoes)
            let profiles = (!Object.keys(base[1]).length ? null : base[1]._embedded.profileStreamComponentCoes)
            let communities = (!Object.keys(base[2]).length ? null : base[2]._embedded.communityStreamComponentCoes)


            dispatch({
                type: ACTION_init,
                //payload:res.data._embedded.collectionModels

                payload:
                    {
                        articles : articles,
                        users : profiles,
                        communities : communities
                }
                //payload: res.data
            })
        })
    }
}


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

export function DISPATCH_openUser(url) {
    return (dispatch) => {
        Axios.get(url).then(res=>{

            console.log("PAYLOAD")
            console.log(res.data)

            dispatch({
                type: ACTION_openUser,
                payload:res.data
            })
        })

    }
}

export function DISPATCH_openArticle(url) {
    return (dispatch) => {
        dispatch({
            type: ACTION_openArticle
        })
    }
}

export function DISPATCH_logIn(payload) {
    return (dispatch) => {
        Axios.post("/login",
            {
                password: payload.password,
                username: payload.username
            }).then(res => {
                dispatch({
                    type: ACTION_logIn,
                    payload: res.data

            // console.log("RESDATA:")
            // console.log(res.data)
            //
            // if (res.status) {
            //     dispatch({
            //         type: ACTION_logIn,
            //         payload: res.data
            //     })
            // } else {
            //     dispatch({
            //         type: ACTION_logIn,
            //         payload: null
            //     })
            })
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

export function DISPATCH_createCommunity(payload) {
    return (dispatch, getState) => {
        Axios.post("/createCommunity", {
            name:payload.name,
            creatorID:payload.creatorID
            // params: {
            //     user: user
            // },
            // data: {
            //     name: payload.name,
            //     description: payload.description
            // }
        }).then(res=>{
            dispatch({
                type: ACTION_openCommunity,
                payload: res.data
                // type: ACTION_createCommunity,
                // payload: res.data
            })
        // }).then(
        //     DISPATCH_openCommunity(res.data._links["Tab_Version"])
        //
        // )
    })
}}

//COMMUNITY-PAGE ACTION DISPATCHES

export function DISPATCH_createArticle() {

}

export function DISPATCH_createPost() {

}

export function DISPATCH_createReply() {

}