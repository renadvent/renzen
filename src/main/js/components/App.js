import React from "react";
import YourStudyGuide from "./Panels/StudyGuideBar/YourStudyGuide";
import SiteData from "./Panels/MainBar/SiteData";
import AnnotationBar from "./Panels/AnnotationBar/AnnotationBar";
import Navbar from "./Panels/NavBar/nav";
import PageLoader from "./1stParty/PageLoader";

//Main JS file
//sets up navbar, and columns for site

function App() {

    //let content = PageLoader();


    return (
        <div className="container-fluid">
            {/*/!*<PageLoader/>*!/*/}
            {/*{content.definitions}*/}
            {/*{content.QA}*/}
            <Navbar/>
            <div className="row">
                <YourStudyGuide/>
                <SiteData />
                <AnnotationBar/>
            </div>
        </div>
    );
}

export default App;
