//Object literals

const new_Article = function(){
    return{
        id:"",
        name:"",

        author:"",
        community:"",

        description:""
    }
}

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

const new_Content = function(){
    return{
        id:"",
        creator:"",

        content:"",

        upVotes:"",
        downVotes:"",

        header:"",
        description:"",
        tags:[],

        discussion:""
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

const new_StudyGuide = function(){
    return{
        id:""
    }
}

