import React from "react";
import YourStudyGuide from "./Panels/StudyGuideBar/YourStudyGuide";
import SiteData from "./Panels/MainBar/SiteData";
import AnnotationBar from "./Panels/AnnotationBar/AnnotationBar";
import Navbar from "./Panels/NavBar/nav";
import SiteDataSection from "./Panels/MainBar/SiteDataComponents/CommentComponents/SiteDataSection";

//Main JS file
//sets up navbar, and columns for site

function App() {
    return (

        <SiteDataSection/>

    //     <div className="container-fluid">
    //         <Navbar/>
    //         <div className="row">
    //             {/*<YourStudyGuide/>*/}
    //             <SiteData />
    //             {/*<AnnotationBar/>*/}
    //         </div>
    //     </div>
    // );
    )
}

export default App;
