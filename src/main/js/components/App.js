import React from "react";
import YourStudyGuide from "./Panels/YourStudyGuide";
import SiteData from "./Panels/SiteData";
import AnnotationBar from "./Panels/AnnotationBar";
import Navbar from "./Panels/nav";
import PageLoader from "./1stParty/PageLoader";

//Main JS file
//sets up navbar, and columns for site

function App() {
    return (
        <div className="container-fluid">
            <PageLoader/>
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
