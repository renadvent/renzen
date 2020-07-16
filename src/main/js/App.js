import React, {useState} from "react";
import Navbar from "./nav";
import Community from "./Community_Page/Community";

import SiteDataSection from "./Community_Page/SiteDataSection";
import {annotateSelection} from "../old_components/functions";
import StudyGuidePanel from "./StudyGuide_Panel/StudyGuidePanel";
import Home from "./Home_Page/Home";
import Profile from "./Profile_Page/Profile";
import {connect} from "react-redux";
import * as actionCreators from "./actions"
import CommunityLayout from "./Community_Page/CommunityLayout";

/*
lays out the single page web-app
 */

function App() {

    const [tabs, setTabs] = useState([])
    const [tabContent, setTabContent] = useState([])

    return (
        <div className="container-fluid">
            <Navbar/>
            <div>
                <div className="container-fluid">
                    <div className="row">
                        <div className={"col-9"}>
                            <ul className="nav nav-tabs" id="myTab" role="tablist">
                                <li className="nav-item">
                                    <a className="nav-link active" id="home-tab" data-toggle="tab" href="#home"
                                       role="tab"
                                    >Home</a>
                                </li>
                                <li className="nav-item">
                                    <a className="nav-link" id="profile-tab" data-toggle="tab" href="#yourprofile"
                                       role="tab"
                                    >Your Profile</a>
                                </li>
                                <li className="nav-item">
                                    <a className="nav-link" id="default-com" data-toggle="tab" href="#com" role="tab"
                                    >Default Community</a>
                                </li>
                                {tabs}
                                <li className="nav-item">
                                    <a className="nav-link" id="add-tab" data-toggle="tab" href="#" role="tab"
                                    >+</a>
                                </li>
                            </ul>
                            <div className="tab-content" id="myTabContent">
                                {tabContent}
                                <div className="tab-pane fade show active" id="home" role="tabpanel"
                                     aria-labelledby="home-tab">
                                    <Home setTabContent={setTabContent} setTabs={setTabs}/>
                                </div>
                                <div className="tab-pane fade" id="yourprofile" role="tabpanel">
                                    <Profile/>
                                </div>




                                <CommunityLayout/>


                            </div>
                        </div>
                        <div className={"col-3"}>
                            <div className={"col"}>
                                <StudyGuidePanel/>
                            </div>
                            <div className={"col"}>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

const mapStateToProps = state => {
    return {
        userName: state.userName,
        userURL: state.userURL,
        userNameObject: state.userNameObject
    }
}

const mapDispatchToProps = dispatch => {
    return {

    }
}

export default connect(mapStateToProps, mapDispatchToProps)(App)