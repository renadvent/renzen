import React, {useContext, useState} from "react";
import Navbar from "./old_components/Panels/NavBar/nav";
import themeContext from "./CommentComponents/context";
import Community from "./CommentComponents/Community";

import SiteDataSection from "./CommentComponents/SiteDataSection";
import {annotateSelection} from "./old_components/1stParty/functions";
import AnnotationBar from "./old_components/Panels/AnnotationBar/AnnotationBar";
import Home from "./Home";
import Profile from "./Profile";


//Main JS file
//sets up navbar, and columns for site

function App() {

    //SiteDataSection.loadSections("/api/pages/5efd2911d231b04eecfcd282")

    const context = useContext(themeContext)


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
                                       aria-controls="home" aria-selected="true">Home</a>
                                </li>

                                <li className="nav-item">
                                    <a className="nav-link" id="profile-tab" data-toggle="tab" href="#yourprofile"
                                       role="tab"
                                       aria-controls="profile" aria-selected="false">Your Profile</a>
                                </li>

                                <li className="nav-item">
                                    <a className="nav-link" id="profile-tab" data-toggle="tab" href="#com" role="tab"
                                       aria-controls="profile" aria-selected="false">Default Community</a>
                                </li>

                                {tabs}

                                <li className="nav-item">
                                    <a className="nav-link" id="profile-tab" data-toggle="tab" href="#" role="tab"
                                       aria-controls="profile" aria-selected="false">+</a>
                                </li>


                            </ul>

                            <div className="tab-content" id="myTabContent">

                                {tabContent}

                                <div className="tab-pane fade show active" id="home" role="tabpanel"
                                     aria-labelledby="home-tab">

                                    <Home setTabContent={setTabContent} setTabs={setTabs}/>


                                </div>


                                <div className="tab-pane fade show active" id="yourprofile" role="tabpanel"
                                     aria-labelledby="home-tab">

                                    <Profile/>


                                </div>


                                <div className="tab-pane fade" id="com" role="tabpanel" aria-labelledby="profile-tab">


                                    {/*<h1 style={{textAlign: "center"}} className={"sticky-top"}>Community Homepage</h1>*/}
                                    <h1 style={{textAlign: "center"}}>Community Homepage</h1>


                                    {/*<form className="form-inline my-2 my-lg-0">*/}
                                    {/*    <input*/}
                                    {/*        className="form-control mr-sm-2"*/}
                                    {/*        type="search"*/}
                                    {/*        placeholder="Search Community"*/}
                                    {/*        aria-label="Search"*/}
                                    {/*        style={{textAlign: "center"}}*/}
                                    {/*    />*/}
                                    {/*    <button className="btn btn-outline-success my-2 my-sm-0" type="submit">*/}
                                    {/*        Search*/}
                                    {/*    </button>*/}
                                    {/*</form>*/}


                                    <hr></hr>
                                    <div className="row">


                                        <div className={"col-5"}>


                                            {/*<div className={"stick-top"}>*/}


                                            <ul className="nav nav-tabs" id="myTab" role="tablist">
                                                <li className="nav-item">
                                                    <a className="nav-link active" id="comDiscTag" data-toggle="tab"
                                                       href="#comDisc"
                                                       role="tab"
                                                       aria-controls="home" aria-selected="true">Community
                                                        Discussion</a>
                                                </li>

                                                <li className="nav-item">
                                                    <a className="nav-link" id="artDiscTab" data-toggle="tab"
                                                       href="#artDisc"
                                                       role="tab"
                                                       aria-controls="profile" aria-selected="false">Article
                                                        Discussion</a>
                                                </li>
                                            </ul>


                                            <div className="tab-content" id="myTabContent2">


                                                <div className="tab-pane fade show active" id="comDisc" role="tabpanel"
                                                     aria-labelledby="home-tab">

                                                    <div className="btn-group-vertical">
                                                        <form>
                                                            <button type="button" className="btn btn-secondary"
                                                                    onClick={annotateSelection}>
                                                                Add Annotation
                                                            </button>
                                                        </form>
                                                        {/*<button type="button" className="btn btn-secondary"*/}
                                                        {/*        onClick={highlightSelection}>*/}
                                                        {/*    Highlight*/}
                                                        {/*</button>*/}
                                                        {/*<button type="button" className="btn btn-secondary"*/}
                                                        {/*        onClick={highlightSelection}>*/}
                                                        {/*    Show Markup*/}
                                                        {/*</button>*/}
                                                    </div>
                                                    <h2>Community Updates</h2>
                                                    <ul>
                                                        <li>New Articles</li>
                                                        <li>New Members</li>
                                                        <li>Unanswered Questions</li>
                                                        <li>Accepted Answers</li>
                                                        <li>Events</li>
                                                        <li>Questions about the Community</li>
                                                    </ul>
                                                    <SiteDataSection title={"Community Discussion"}
                                                                     page={"/api/pages/5efd2911d231b04eecfcd282"}/>


                                                </div>


                                                <div className="tab-pane fade show active" id="artDisc" role="tabpanel"
                                                     aria-labelledby="home-tab">

                                                    None Yet


                                                </div>
                                            </div>


                                            {/*</div>*/}


                                        </div>
                                        <div className={"col-7"}>
                                            <Community/>
                                        </div>
                                    </div>
                                    <div className="tab-pane fade" id="contact" role="tabpanel"
                                         aria-labelledby="contact-tab">...
                                    </div>
                                </div>
                            </div>
                        </div>


                        <div className={"col-3"}>
                            <div className={"col"}>
                                <AnnotationBar/>
                            </div>
                            <div className={"col"}>
                                {/*<SiteDataSection page={"/api/pages/5f026fe90d93972c10f8b004"}/>*/}
                            </div>
                        </div>

                    </div>


                </div>
            </div>
        </div>

    )
}

export default App;
