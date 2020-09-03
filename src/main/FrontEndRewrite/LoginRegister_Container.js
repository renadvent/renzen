import React, {useEffect, useState} from "react"
import {connect} from "react-redux";
import Profile_Container from "./Profile_Container";
import * as store from "./Store_Actions"

const mapStateToProps = (state) => {
    return {
        open_communities: state.open_communities,
        isLoggedIn: state.isLoggedIn,

        loadedCommunities:state.homeTabData.communities,
        loadedArticles:state.homeTabData.articles,
        loadedUsers:state.homeTabData.users,

        user:state.user
    }
}

const mapDispatchToProps = (dispatch) => {
    return {

        DISPATCH_openCommunity: () => dispatch(store.DISPATCH_openCommunity()),
        DISPATCH_openUser: () => dispatch(store.DISPATCH_openUser()),
        DISPATCH_openArticle: () => dispatch(store.DISPATCH_openArticle()),

        DISPATCH_logIn: (username, password) =>
            dispatch(store.DISPATCH_logIn({username:username,password:password})),
        DISPATCH_register: (username, password) =>
            dispatch(store.DISPATCH_register({username:username,password:password})),

        DISPATCH_createCommunity: (user,payload) => dispatch(store.DISPATCH_createCommunity(user,payload))

    }
}



function LoginRegister_Container(props) {

    const [userName, setUserName] = useState("");
    const [password, setPassword] = useState("");

    function handleSubmit(){

    }

    return (

        <div>

            <ul className="nav nav-tabs">
                <li className="nav-item">
                    <a className="nav-link active" href="#">Login</a>
                </li>
                <li className="nav-item">
                    <a className="nav-link" href="#">Sign Up</a>
                </li>
            </ul>

            <div className="tab-content" id="myLoginContent">

                <div className="tab-pane fade show active" id="login" role="tabpanel" aria-labelledby="home-tab">
                    <form onSubmit={props.DISPATCH_logIn(userName,password)}>
                        <div className="form-group">
                            <label htmlFor="email">Email</label>
                            <input type="email" className="form-control" name="username"/>
                        </div>
                        <div className="form-group">
                            <label htmlFor="password">Password</label>
                            <input type="password" className="form-control" name="password"/>
                        </div>

                        <button type="submit" className="btn btn-dark">
                            Login
                        </button>

                    </form>
                </div>

                <div className="tab-pane fade" id="signup" role="tabpanel" aria-labelledby="profile-tab">
                    <form onSubmit={props.DISPATCH_register(userName,password)}>
                        <div className="form-group">
                            <label htmlFor="email">Email</label>
                            <input
                                value={userName}
                                onChange={(event) => setUserName(event.target.value)}
                                type="username"
                                className="form-control"
                                name="username"
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="password">Password</label>
                            <input
                                value={password}
                                onChange={(event) => setPassword(event.target.value)}
                                type="password"
                                className="form-control"
                                name="password"
                            />
                        </div>
                        <button type="submit" className="btn btn-dark">
                            Register
                        </button>
                    </form>
                </div>
            </div>
        </div>
    )
}

export default connect(mapStateToProps, mapDispatchToProps)(LoginRegister_Container);