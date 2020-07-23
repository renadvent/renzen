import * as at from "./Store_Actions"
import Community from "../js/Community_Page/Community/Community";
import React from "react"

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
        open_study_guides: []
    },

    homeTabData: {
        users: [],
        communities: [],
        articles: []
    }

}

const reducer = (state = initialState, action) => {
    switch (action.type) {

        case at.ACTION_openCommunity:
            break

        case at.ACTION_openUser:
            break

        case at.ACTION_openArticle:
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

            if (action.payload) {
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
                }
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
}