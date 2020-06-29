import React from "react"
import SiteDataSection from "./CommentComponents/SiteDataSection";

function MainTab(props) {

    return (
        <div
            className="tab-pane fade show active"
            id="ALLC"
            role="tabpanel"
            aria-labelledby="home-tab"
        >
            <div className="y">
                <h1>Null Hypothesis</h1>
                <h3>Contents</h3>

                <ul className="list-group">
                    <li className="list-group-item">
                        Definitions{" "}
                        <span className="badge badge-primary badge-pill">14</span>
                    </li>
                    {/*<li className="list-group-item">When To Use</li>*/}
                    {/*<li className="list-group-item">*/}
                    {/*    Walkthroughs{" "}*/}
                    {/*    <span className="badge badge-primary badge-pill">4</span>*/}
                    {/*</li>*/}
                    {/*<li className="list-group-item">General Q&A</li>*/}
                    {/*<li className="list-group-item">Links & Resources</li>*/}
                </ul>
            </div>
            <p></p>
            <hr></hr>
            <SiteDataSection/>

        </div>
    )
}

export default MainTab