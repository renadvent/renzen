const initialState={
    userName:"not logged in",
    userNameObject: null,
    loggedIn:false
}

const reducer = (state = initialState,action) => {

    if(action.type==="CHANGE_NAME"){
        return{
            userName: "REDUX_LOGIN"
        }
    }

    return state;
}

export default  reducer