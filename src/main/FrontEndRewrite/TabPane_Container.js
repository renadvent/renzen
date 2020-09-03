import React, {useEffect} from "react"
import Home from "../js/Home_Page/Home";
import {connect} from "react-redux";
import Home_Container from "./Home_Container";
import * as store from "./Store_Actions"

/*
sets up main tab (HOME) and content
hosts other opened tabs when they are opened
 */

const mapStateToProps = (state) => {
    return {
        open_communities: state.open_communities
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

    return (
        <div id={"tabsAndContents"}>
            <div id={"tab-list"}>
                <ul className="nav nav-tabs" id="app-tabs" role="tablist">
                    <li className="nav-item">
                        <a
                            className="nav-link active"
                            id="home-tab"
                            data-toggle="tab"
                            href="#home"
                            role="tab"
                        >
                            Home
                        </a>
                    </li>


                    {/*gets component from component portion of coummunity object*/}
                    {props.open_communities.map(community=>{
                        return(community.tab)
                    })}
                </ul>
            </div>

            <div id={"tabContents"}>

                <div
                    className="tab-pane fade show active"
                    id="home-contents"
                    role="tabpanel"
                    aria-labelledby="home-tab"
                >
                    <Home_Container/>
                </div>

            </div>

        </div>
    )
}

export default connect(mapStateToProps, mapDispatchToProps)(TabPane_Container)