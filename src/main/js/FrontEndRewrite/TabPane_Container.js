import React, {useEffect} from "react"
import {connect} from "react-redux";
import Home_Container from "./Home_Container";
import * as store from "./Store_Actions"

/*
sets up main tab (HOME) and content
hosts other opened tabs when they are opened
 */

const mapStateToProps = (state) => {
    return {
        // open_communities: state.tabs.open_communities,
        // open_profiles:state.tabs.open_profiles,
        // open_articles:state.tabs.open_articles
        open:state.tabs.open
    }
}

const mapDispatchToProps = (dispatch) => {
    return {
        DISPATCH_init: () => dispatch(store.DISPATCH_init())
    }
}

function TabPane_Container(props) {

    useEffect(()=>{

        props.DISPATCH_init();

    },[])

    useEffect(()=>{

        if ((props.open.length)>0){
            $('#tabA'+props.open[props.open.length-1].data._id).tab('show')
        }


        // $('#app-tabs li:last-child a').tab('show')
    },[props.open])

    return (
        <div id={"tabsAndContents"}>
            <div id={"tab-list"}>
                <ul className="nav nav-tabs" id="app-tabs" role="tablist">

                    <li className="nav-item">
                        <a
                            className="nav-link active"
                            id="home-tab"
                            data-toggle="tab"
                            href="#home-contents"
                            role="tab"
                        >
                            Home
                        </a>
                    </li>

                    {/*<li className="nav-item">*/}
                    {/*    <a*/}
                    {/*        className="nav-link"*/}
                    {/*        id="profile-tab"*/}
                    {/*        data-toggle="tab"*/}
                    {/*        href="#profile-contents"*/}
                    {/*        role="tab"*/}
                    {/*    >*/}
                    {/*        Profile*/}
                    {/*    </a>*/}
                    {/*</li>*/}


                    {/*gets component from component portion of coummunity object*/}
                    {props.open.map(open=>{
                        return(open.tab)
                    })}

                    {/*{props.open_profiles.map(profile=>{*/}
                    {/*    return(profile.tab)*/}
                    {/*})}*/}

                    {/*{props.open_articles.map(article=>{*/}
                    {/*    return(article.tab)*/}
                    {/*})}*/}

                </ul>
            </div>

            <div id={"tabContents"} className={"tab-content"}>

                <div
                    className="tab-pane fade show active"
                    id="home-contents"
                    role="tabpanel"
                    aria-labelledby="home-tab"
                >
                    <Home_Container/>

                </div>



                {/*<div*/}
                {/*    className="tab-pane fade"*/}
                {/*    id="profile-contents"*/}
                {/*    role="tabpanel"*/}
                {/*    aria-labelledby="profile-tab"*/}
                {/*>*/}
                {/*    <Profile_Container/>*/}

                {/*</div>*/}

                {props.open.map(open=>{
                    return(open.component)
                })}

                {/*{props.open_profiles.map(profile=>{*/}
                {/*    return(profile.component)*/}
                {/*})}*/}

                {/*{props.open_articles.map(article=>{*/}
                {/*    return(article.component)*/}
                {/*})}*/}

            </div>

        </div>
    )
}

export default connect(mapStateToProps, mapDispatchToProps)(TabPane_Container)