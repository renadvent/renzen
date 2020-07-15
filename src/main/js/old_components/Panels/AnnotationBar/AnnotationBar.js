import React from "react";
import defaultText from "./AnnotationBarComponents/DefaultText"
import {annotateSelection, highlightSelection} from "../../1stParty/functions";
import WebsiteTab from "../../SiteDataComponents/WebsiteTab";
import NoteLoader from "../StudyGuideBar/Components/NoteLoader";

function AnnotationBar() {

    return (

        <div id="addTo" >
            {/*//className="col-3">*/}


            <ul className="nav nav-tabs">
                {/*<WebsiteTab name={"The Docs"} linkTo={"#DocSec"} active={"active"}/>*/}
                <WebsiteTab name={"Default Study Guide"} linkTo={"#AnnoSec"} active={"active"}/>
                <WebsiteTab name={"+"} linkTo={""} active={""}/>
            </ul>

            {/*<div className="tab-content" id="myTabContent">*/}

            {/*</div>*/}

            <div className="tab-content" id="myTabContent2">

                {/*<div className="tab-pane fade show active"*/}
                {/*     id="DocSec"*/}
                {/*     role="tabpanel"*/}
                {/*     aria-labelledby="profile-tab">*/}

                {/*    <h3>Definitions</h3>*/}
                {/*    <NoteLoader data="/Definitions"/>*/}

                {/*        /!*<h3>Notes</h3>*!/*/}
                {/*        /!*<NoteLoader data="/Notes"/>*!/*/}


                {/*    <h3>Overviews</h3>*/}
                {/*    <h3>When to Use/Purposes</h3>*/}
                {/*    <h3>Walkthroughs</h3>*/}
                {/*    <h3>General Q&A</h3>*/}
                {/*    <h3>Links & Resources</h3>*/}
                {/*    <h3>Interactive Examples</h3>*/}


                {/*</div>*/}

                <div className="tab-pane fade show active"
                     id="AnnoSec"
                     role="tabpanel"
                     aria-labelledby="profile-tab">

                    {/* <h1>Community Annotations</h1> */}
                    {/*<div className="btn-group-vertical">*/}
                    {/*    <form>*/}
                    {/*        <button type="button" className="btn btn-secondary" onClick={annotateSelection}>*/}
                    {/*            Add Annotation*/}
                    {/*        </button>*/}
                    {/*    </form>*/}
                    {/*    <button type="button" className="btn btn-secondary" onClick={highlightSelection}>*/}
                    {/*        Highlight*/}
                    {/*    </button>*/}
                    {/*    <button type="button" className="btn btn-secondary" onClick={highlightSelection}>*/}
                    {/*        Show Markup*/}
                    {/*    </button>*/}
                    {/*</div>*/}
                    <div id="annoBar">
                        <button>Add Note</button>
                        {defaultText}
                    </div>

                </div>

            </div>


        </div>

    )
}

export default AnnotationBar