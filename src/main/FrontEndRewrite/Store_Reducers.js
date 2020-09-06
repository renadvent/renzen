import * as at from "./Store_Actions"
import Community from "../js/Community_Page/Community/Community";
import React from "react"
import AppTab from "./AppTab";
import {open_community} from "../js/Store/actions";
import ProfileAppTabContent from "./ProfileAppTabContent";
import CommunityAppTabContent from "./CommunityAppTabContent";

//INITIAL STATE

const initialState = {

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
        stream_communities: [],
        open_profiles:[]
    },

    homeTabData: {
        stream_users: [],
        stream_communities: [],
        stream_articles: []
    }

}

const reducer = (state = initialState, action) => {

    switch (action.type) {

        case at.ACTION_createArticle:
            return state

        break

        case at.ACTION_joinCommunity:

            return {
                ...state,
            }

            break

        case at.ACTION_init:

            return {
                ...state,
                homeTabData:{
                    stream_articles: action.payload.articles,
                    stream_users: action.payload.users,
                    stream_communities: action.payload.communities
                }
            }
            break

        case at.ACTION_openCommunity:
            return {
                ...state,
                tabs : {
                    ...state.tabs,
                    open_communities: state.tabs.open_communities.concat(
                        {
                            name: action.payload.name,
                            data: action.payload.data,
                            tab: <AppTab name={action.payload.name} href={"A"+action.payload._id}/>,
                            component:<CommunityAppTabContent payload={action.payload} href={"A"+action.payload._id}/>
                        })
                }
            }
            break

        case at.ACTION_openUser:

            return {
                ...state,
                tabs : {
                    ...state.tabs,
                    open_profiles: state.tabs.open_profiles.concat(
                        {
                            name: action.payload.name,
                            data: action.payload,
                            tab: <AppTab name={action.payload.name} href={"A"+action.payload._id}/>,
                            component:<ProfileAppTabContent payload={action.payload} href={"A"+action.payload._id}/>
                        }
                    )
                }
            }
            break

        case at.ACTION_openArticle:
            return state
            break

        case at.ACTION_register:

            //TODO finish


            return {
                ...state,
                user: {
                    logged_in: true,
                    name: action.payload.name,
                    id: action.payload._id,
                    //url:"",
                    communities: [], //array of objects containing: name,link
                    articles: [],
                    study_guides: [],

                    user_data: {}
                },
            }

            break

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

        //TODO redo
            console.log("ACTION LOGIN")
            console.log(action.payload)

            console.log("LOGINID")
            console.log(action.payload._id)

            return {
                ...state,
                user: {
                    ...state.user,
                    logged_in: true,
                    name: action.payload.name,
                    id: action.payload._id,
                    //url:action.payload.url,
                    communities: action.payload.communities,//a dual name, link object
                    articles: action.payload.articles,
                    study_guides: action.payload.studyGuides,

                    user_data: action.payload
                },
                tabs:{
                    ...state.tabs,
                    open_profiles: state.tabs.open_profiles.concat({
                        name: action.payload.name,
                        data: action.payload,
                        tab: <AppTab name={action.payload.name + " (Your Profile)"} href={"A" + action.payload._id}/>,
                        component: <ProfileAppTabContent payload={action.payload} href={"A" + action.payload._id}/>
                    })

                }
        }

        case at.ACTION_createCommunity:

            // return {
            //     ...state,
            //     open_communities: state.open_communities.concat(
            //         {
            //             name: action.payload.name,
            //             data: action.payload.data,
            //             tab: <AppTab name={action.payload.name}/>,
            //             component: <Community payload={action.payload.data}/>
            //         })
            //
            // }

            break
    }
    return state;
}

export default reducer;