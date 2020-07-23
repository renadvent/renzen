import * as at from "./Store_Actions"

//INITIAL STATE

const initialState={

    user:{
        logged_in:false,
        user_name:"",
        user_url:"",
        communities:[], //array of objects containing: name,link
        articles:[],
        study_guides:[],

        user_data:{}
    },

    tabs:{
        open_communities: [],
        open_study_guides: []
    },

    homeTabData:{
        users:[],
        communities: [],
        articles: []
    }

}

const reducer = (state=initialState,action)=>{
    switch (action.type) {

        case at.ACTION_openCommunity:
            break
        case at.ACTION_openUser:
            break
        case at.ACTION_openArticle:
            break

    }
}