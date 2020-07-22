import React from "react"
import {connect} from "react-redux";
import Community from "../js/Community_Page/Community/Community";
import Profile from "../js/Profile_Page/Profile";

const mapStateToProps = (state) => {
    return {
        open_communities: state.open_communities,
        isLoggedIn: state.isLoggedIn,

    }
}

const mapDispatchToProps = (dispatch) => {
    return {
        onOpenCommunity: () => dispatch(),
        onOpenProfile:()=>dispatch(),
        onOpenArticle:()=>dispatch(),

        onLogin:()=>dispatch(),
        onRegister:()=>dispatch(),

        onCreateCommunity:()=>dispatch()
    }
}

function Home_Container(props){
    return (
        <div>
            <div className={props.isLoggedIn ? "d-block" : "d-none"}>
                <Create_NewCommunity action={props.onCreateCommunity}/>
                <ProfileContainer/>
            </div>

            <div className={!props.isLoggedIn ? "d-block" : "d-none"}>
                <LoginContainer action={props.onLogin}/>
                <RegisterContainer action={props.onRegister}/>
            </div>

            <CommunityStream action={props.onOpenCommunity}/>
            <ProfileStream action={props.onOpenProfile}/>
            <ArticleStream action={props.onOpenArticle}/>

        </div>
    )
}

export default connect(mapStateToProps, mapDispatchToProps)(Home_Container);