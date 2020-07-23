import React, {useEffect, useState} from "react"
import {connect} from "react-redux";
import Profile_Container from "./Profile_Container";

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

        DispatchOpenCommunity: () => dispatch(),
        DispatchOpenUser: () => dispatch(),
        DispatchOpenArticle: () => dispatch(),

        DispatchLogin: (username, password) => dispatch(),
        DispatchRegister: (username, password) => dispatch(),

        DispatchCreateCommunity: () => dispatch()
    }
}

/*
The home page/tab
includes:
login/register actions
open articles/users/communities
shows own profile
create communities
 */

function Home_Container(props) {
    return (
        <div>
            <div className={props.isLoggedIn ? "d-block" : "d-none"}>
                <InLine_NewCommunity action={props.DispatchCreateCommunity}/>
                <Profile_Container/>
            </div>

            <div className={!props.isLoggedIn ? "d-block" : "d-none"}>
                <LoginRegister_Container/>
                {/*<RegisterContainer action={props.onRegister}/>*/}
            </div>

            <div className="container-fluid">
                <div className={"row"}>
                    <div className={"col"}>
                        <h4>All Users</h4>
                        <Stream source={props.loadedUsers} dispatch={props.DispatchOpenUser}/>
                    </div>
                    <div className={"col"}>
                        <h4>All Articles</h4>
                        <Stream source={props.loadedArticles} dispatch={props.DispatchOpenArticle}/>
                    </div>
                    <div className={"col"}>
                        <h4>All Communities</h4>
                        <Stream source={props.loadedCommunities} dispatch={props.DispatchOpenCommunity}/>
                    </div>
                </div>
            </div>
        </div>
    )
}

function Stream(props){
    return(
        <div>
            {props.source.map(single=>{
                return(
                    <div onClick={props.DispatchOpenUser(single.link)}>
                        {single.name}
                    </div>
                )
            })}
        </div>
    )
}

function InLine_NewCommunity(props){
    return(
        <div></div>
    )
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
                    <form onSubmit={props.DispatchLogin(userName,password)}>
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
                    <form onSubmit={props.DispatchRegister(userName,password)}>
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

//not sure if correct...
export default connect(mapStateToProps, mapDispatchToProps)(Home_Container, Login_Container);