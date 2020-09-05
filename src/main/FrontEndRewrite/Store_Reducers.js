import * as at from "./Store_Actions"
import Community from "../js/Community_Page/Community/Community";
import React from "react"
import AppTab from "./AppTab";
import {open_community} from "../js/Store/actions";

//INITIAL STATE

const initialState = {

    //temp
    open_communities: [],

    user: {
        logged_in: false,
        name: "",
        id: "",
        //url:"",
        communities: [], //array of objects containing: name,link
        articles: [],
        study_guides: [],

        user_data: {}
    },

    tabs: {
        open_communities: [],
        open_study_guides: []
    },

    homeTabData: {
        users: [],
        communities: [],
        articles: []
    }

}

const reducer = (state = initialState, action) => {

    // const findLink = (link){
    //     link._links.find()
    // }

    switch (action.type) {

        case at.ACTION_init:
            console.log("REDUCING INIT")
            console.log(action.payload)

            //get profiles
            //_links is an array
            //action.payload._links.find((link)=>{return (link.rel==="profileTabLink")})
            console.log("-----------STREAM VERSIOn")
            //console.log(action.payload[1].links[0]["Stream_Version"])

            return {
                ...state,
                homeTabData:{
                    articles: action.payload.articles,
                    users: action.payload.users,
                    communities: action.payload.communities
                }
            }
            break

        case at.ACTION_openCommunity:
            return {
                ...state,
                tabs : {
                    open_communities: state.open_communities.concat(
                        {
                            name: action.payload.name,
                            data: action.payload.data,
                            //tab: null,
                            tab: <AppTab name={action.payload.name}/>,
                            component: <Community payload={action.payload.data}/>
                        })
                }
            }
            break

        case at.ACTION_openUser:
            return state
            break

        case at.ACTION_openArticle:
            return state
            break

        case at.ACTION_register:


        case at.ACTION_logOut:
            return {
                ...state,
                user: {
                    logged_in: false,
                    name: "",
                    id: "",
                    //url:"",
                    communities: [], //array of objects containing: name,link
                    articles: [],
                    study_guides: [],

                    user_data: {}
                },
            }

        case at.ACTION_logIn:

            //if (action.payload) {
                return {
                    ...state,
                    user: {
                        ...state.user,
                        logged_in: true,
                        name: action.payload.username,
                        id: action.payload.id,
                        //url:action.payload.url,
                        communities: action.payload.communities,//a dual name, link object
                        articles: action.payload.articles,
                        study_guides: action.payload.studyGuides,

                        user_data: action.payload
                    }
               // }
            }

        case at.ACTION_createCommunity:

            return {
                ...state,
                open_communities: state.open_communities.concat(
                    {
                        name: action.payload.name,
                        data: action.payload.data,
                        tab: <AppTab name={action.payload.name}/>,
                        component: <Community payload={action.payload.data}/>
                    })

            }

            break
    }
    return state;
}

export default reducer;