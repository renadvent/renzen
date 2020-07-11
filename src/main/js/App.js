import React, {useContext} from "react";
import SiteData from "./old_components/Panels/MainBar/SiteData";
import Navbar from "./old_components/Panels/NavBar/nav";
import themeContext from "./CommentComponents/context";
import Community from "./CommentComponents/Community";
import AnnotationBar from "./old_components/Panels/AnnotationBar/AnnotationBar";

import SiteDataSection from "./CommentComponents/SiteDataSection";
import {annotateSelection, highlightSelection} from "./old_components/1stParty/functions";

//Main JS file
//sets up navbar, and columns for site

function App() {

    //SiteDataSection.loadSections("/api/pages/5efd2911d231b04eecfcd282")

    const context = useContext(themeContext)


    return (

        <div className="container-fluid">
            <Navbar/>
            <h1 style={{textAlign:"center"}}>Community Homepage</h1>
            <div className="row">
                <div className={"col"}>
                    <div className="btn-group-vertical">
                        <form>
                            <button type="button" className="btn btn-secondary" onClick={annotateSelection}>
                                Add Annotation
                            </button>
                        </form>
                        <button type="button" className="btn btn-secondary" onClick={highlightSelection}>
                            Highlight
                        </button>
                        <button type="button" className="btn btn-secondary" onClick={highlightSelection}>
                            Show Markup
                        </button>
                    </div>
                    <SiteDataSection title={"Community Discussion"} page={"/api/pages/5efd2911d231b04eecfcd282"}/>
                </div>
                <div className={"col"}>
                    <Community/>
                </div>

                {/*<div className={"col"}>*/}
                {/*        <AnnotationBar/>*/}
                {/*</div>*/}
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
