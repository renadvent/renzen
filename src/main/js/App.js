import React, {useContext} from "react";
import SiteData from "./old_components/Panels/MainBar/SiteData";
import Navbar from "./old_components/Panels/NavBar/nav";
import themeContext from "./CommentComponents/context";
import Community from "./CommentComponents/Community";
import AnnotationBar from "./old_components/Panels/AnnotationBar/AnnotationBar";

import SiteDataSection from "./CommentComponents/SiteDataSection";

//Main JS file
//sets up navbar, and columns for site

function App() {

    //SiteDataSection.loadSections("/api/pages/5efd2911d231b04eecfcd282")

    const context = useContext(themeContext)


    return (

        <div className="container-fluid">
            <Navbar/>
            <div className="row">
                <div className={"col"}>
                    <SiteDataSection title={"Community Discussion"} page={"/api/pages/5efd2911d231b04eecfcd282"}/>
                </div>
                <div className={"col"}>
                    <Community/>
                </div>

                <div className={"col"}>
                        <AnnotationBar/>
                </div>
                {/*<div className={"col"}>*/}
                {/*    <SiteDataSection page={"/api/pages/5f026fe90d93972c10f8b004"}/>*/}
                {/*</div>*/}
            </div>


            {/*<div className="container-fluid">*/}

            {/*    <div className="row">*/}
            {/*        /!*<YourStudyGuide/>*!/*/}
            {/*        <SiteData/>*/}
            {/*        /!*<AnnotationBar/>*!/*/}
            {/*    </div>*/}
            {/*</div>*/}


        </div>




        // );
    )
}

export default App;
