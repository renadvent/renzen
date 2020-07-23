//literals

const new_Community = function(){
    return{
        id:"",
        name:"",
        description:"",
        creator:"",

        topics:[],
        articles:[],
        questions:[],
        users:[],
        moderators:[],
    }
}

const new_User = function(){
    return{
        id:"",
        name:"",
        password:"",

        articles:[],
        questions:[],
        communities:[],
        studyGuides:[]
    }
}

const new_Content = function(){
    return{
        id:"",

        content:"",

        upVotes:"",
        downVotes:"",

        heading:"",
        description:"",
        tags:[],

        discussion:""


    }
}