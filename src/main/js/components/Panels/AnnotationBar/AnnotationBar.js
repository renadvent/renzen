import React from "react";
import defaultText from "./AnnotationBarComponents/DefaultText"
import {annotateSelection, highlightSelection} from "../../1stParty/functions";
import WebsiteTab from "../MainBar/SiteDataComponents/WebsiteTab";
import DataLoader from "../../1stParty/DataLoader";

function AnnotationBar() {

    return (

        <div id="addTo" className="col-3">


            <ul className="nav nav-tabs">
                {/*<WebsiteTab name={"The Docs"} linkTo={"#DocSec"} active={"active"}/>*/}
                <WebsiteTab name={"Annotations"} linkTo={"#AnnoSec"} active={"active"}/>
            </ul>

            {/*<div className="tab-content" id="myTabContent">*/}

            {/*</div>*/}

            <div className="tab-content" id="myTabContent2">

                {/*<div className="tab-pane fade show active"*/}
                {/*     id="DocSec"*/}
                {/*     role="tabpanel"*/}
                {/*     aria-labelledby="profile-tab">*/}

                {/*    <h3>Definitions</h3>*/}
                {/*    <DataLoader data="/Definitions"/>*/}

                {/*        /!*<h3>Notes</h3>*!/*/}
                {/*        /!*<DataLoader data="/Notes"/>*!/*/}


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
                    <div className="btn-group-vertical">
                        <form>
                            <button type="button" onClick={annotateSelection}>
                                Add Annotation
                            </button>
                        </form>
                        <button type="button" onClick={highlightSelection}>
                            Highlight
                        </button>
                    </div>
                    <div id="annoBar">
                        {defaultText}
                    </div>

                </div>

            </div>


        </div>

    )
}

export default AnnotationBar