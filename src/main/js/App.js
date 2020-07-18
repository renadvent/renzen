import React, {useState,useEffect} from "react";
import Navbar from "./nav";
import StudyGuidePanel from "./StudyGuide_Panel/StudyGuidePanel";
import Home from "./Home_Page/Home";
import Profile from "./Profile_Page/Profile";
import {connect} from "react-redux";
import CommunityLayout from "./Community_Page/CommunityLayout";
import Axios from "axios";

/*
lays out the single page web-app
 */

function App(props) {

    const [tabs, setTabs] = useState([])
    const [tabContent, setTabContent] = useState([])

    useEffect(()=>{

        async function foo(){

            function doSomething(){
                return Axios.get("/api/users")
            }

            let x= await doSomething()
            console.log("async test")
            console.log(x)
        }

        foo()
    })

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


                                {/*{tabs}*/}
                                {props.openCommunitiesTab}


                                <li className="nav-item">
                                    <a className="nav-link" id="add-tab" data-toggle="tab" href="#" role="tab"
                                    >+</a>
                                </li>
                            </ul>
                            <div className="tab-content" id="myTabContent">


                                {/*{tabContent}*/}
                                {props.openCommunitiesContent}

                                <div className="tab-pane fade show active" id="home" role="tabpanel"
                                     aria-labelledby="home-tab">
                                    {/*<Home setTabContent={setTabContent} setTabs={setTabs}/>*/}
                                    <Home/>
                                </div>
                                <div className="tab-pane fade" id="yourprofile" role="tabpanel">
                                    <Profile/>
                                </div>




                                <CommunityLayout comURL={"http://localhost:8001/api/communities/5f0c01b2d552840570235567"}/>


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
        userNameObject: state.userNameObject,

        openCommunitiesTab:state.openCommunitiesTab,
        openCommunitiesContent:state.openCommunitiesContent,
    }
}

const mapDispatchToProps = dispatch => {
    return {

    }
}

export default connect(mapStateToProps, mapDispatchToProps)(App)