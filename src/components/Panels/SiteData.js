import fillerData from "../../FillerData";
import React from "react";
import DefCard from "../SiteDataComponents/Definitions";

function createDefinition(props) {
    return (
        <div>
            <DefCard
                id={props.id}
                key={props.id}
                content={props.content}
                name={props.name}
                comments={props.comments}
                uses={props.uses}
                QA={props.QA}
                Walkthrough={props.Walkthrough}
                author={props.author}
            />
        </div>
    );
}

function aClick() {

    //
    // function getPositionXY(element) {
    //     var rect = element.getBoundingClientRect();
    //     document.getElementById('annoBar').innerHTML =
    //         'X: ' + rect.x + ', ' + 'Y: ' + rect.y
    // }

    var el = document.getElementById("annoBar")
    el.style.position="absolute"
    //el.style={"{top:50px;left:100px;}"}

    el.style["top"]="50px"
    el.style["left"]="100px"

    el.innerText="SWITCHED AGAIN!!"

}

function SiteData() {
    return (
        <div id="mid" className="col-6">


            <div id="hold">
                <button data-placement="right" type="button" className="btn btn-lg btn-danger" data-toggle="popover" title="Popover title"
                        data-content="And here's some amazing content. It's very engaging. Right?" onClick={aClick}
                data-container="#mid">Click to toggle popover
                </button>
            </div>

            <ul className="nav nav-tabs">
                <li className="nav-item" role="presentation">
                    <a
                        className="nav-link active"
                        id="home-tab"
                        data-toggle="tab"
                        href="#ALLC"
                        role="tab"
                        aria-controls="profile"
                        aria-selected="true"
                    >
                        Study Guide
                    </a>
                </li>
                <li className="nav-item">
                    <a
                        className="nav-link"
                        id="profile-tab"
                        data-toggle="tab"
                        href="#wiki"
                        role="tab"
                        aria-controls="profile"
                        aria-selected="false"
                    >
                        Wikipedia
                    </a>
                </li>
                <li className="nav-item">
                    <a
                        className="nav-link"
                        id="sandbox-tab"
                        data-toggle="tab"
                        href="#codesandbox"
                        role="tab"
                        aria-controls="profile"
                        aria-selected="false"
                    >
                        CodeSandbox.io
                    </a>
                </li>
                <li className="nav-item">
                    <a
                        className="nav-link"
                        id="boot-tab"
                        data-toggle="tab"
                        href="#boot"
                        role="tab"
                        aria-controls="profile"
                        aria-selected="false"
                    >
                        Bootstrap Docs
                    </a>
                </li>
            </ul>

            <div className="tab-content" id="myTabContent">
                <div
                    className="tab-pane fade"
                    id="wiki"
                    role="tabpanel"
                    aria-labelledby="profile-tab"
                >
                    {" "}
                    <iframe
                        title="search"
                        src="https://www.wikipedia.org/"
                        width="100%"
                        height="600"
                    ></iframe>
                </div>
                <div
                    className="tab-pane fade"
                    id="codesandbox"
                    role="tabpanel"
                    aria-labelledby="contact-tab"
                >
                    <iframe
                        title="sandbox"
                        src="https://codesandbox.io/dashboard/recent"
                        width="100%"
                        height="600"
                    ></iframe>
                </div>

                <div
                    className="tab-pane fade"
                    id="boot"
                    role="tabpanel"
                    aria-labelledby="contact-tab"
                >
                    <iframe
                        title="boot_docs"
                        src="https://getbootstrap.com/docs/4.5/components/alerts/"
                        width="100%"
                        height="600"
                    ></iframe>
                </div>

                <div
                    className="tab-pane fade show active"
                    id="ALLC"
                    role="tabpanel"
                    aria-labelledby="home-tab"
                >
                    <div className="y">
                        <h1>{fillerData[0].name}</h1>
                        <h3>Contents</h3>

                        <ul className="list-group">
                            <li className="list-group-item">


                                Definitions{" "}
                                <span className="badge badge-primary badge-pill">14</span>
                            </li>
                            <li className="list-group-item">When To Use</li>
                            <li className="list-group-item">
                                Walkthroughs{" "}
                                <span className="badge badge-primary badge-pill">4</span>
                            </li>
                            <li className="list-group-item">General Q&A</li>
                            <li className="list-group-item">Links & Resources</li>
                        </ul>
                    </div>


                    <p></p>

                    <hr></hr>


                    {fillerData.map(createDefinition)}
                    <h1>Interactive Examples</h1>
                    <h1>Screenshots</h1>
                </div>
            </div>


        </div>
    )
}

export default SiteData