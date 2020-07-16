import * as actionTypes from "./actions"
import Axios from "axios";

const initialState={
    userName:"not logged in",
    userURL:"",
    userNameObject: null,


    loggedIn:false,
    userNameCom:[],

    openStudyGuides:[],
    activeStudyGuide: null,

    openCommunities:[],
    activeCommunity:null,

    openArticles:[],
    activeArticle:null,
}
//
const reducer = (state = initialState,action) => {

    if (action.type===actionTypes.OPEN_ARTICLE){
        return {
            ...state,
            activeArticle: action.activeArticle,
            openArticles: state.openArticles.concat(action.activeArticle)
        }
    }

    //------------------------------------------------------------

    if (action.type===actionTypes.FAKE_LOG_IN){

        console.log(JSON.stringify(action))

        return{
            ...state,
            userNameObject: action.userNameObject,
            userNameCom: action.userNameObject.data.communities,
            userName: action.userNameObject.data.userName
            // userURL: action.
        }
    }

    if(action.type==="CHANGE_NAME"){
        console.log("CHANGE NAME")
        return{
            ...state,
            userName: "REDUX_LOGIN"
        }
    }

    //------------------------------------------------------------

    // if (action.type==="FAKE_LOGIN"){
    //
    //     console.log("FAKE_LOGIN:"+JSON.stringify(action))
    //
    //     Axios.get(action.userURL).then(userResponse=>{
    //
    //
    //         console.log("FAKE_LOGIN:"+JSON.stringify(userResponse.data))
    //         return{
    //             ...state,
    //             userURL: action.userURL,
    //             userNameObject:userResponse.data,
    //             userNameCom:userResponse.data.communities
    //         }
    //     })
    // }

    return state;
}

export default  reducer