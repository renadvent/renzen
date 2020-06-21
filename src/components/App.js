import React, {useState} from "react";


//const client = require('./client'); // <3>

import EmpApp from "./EmpApp"
//import data from "./fromDatabase"
import definitions from "../definitions.js";
import DefCard from "./Definitions";
import {replaceSelection} from "./replaceSelection";
import PageSelector from "./PageSelector";
import Page from "./Page";

import StudyGuide from "./studyGuide";
import Discussion from "./Discussion";
import collapse from "./collapse";

import Navbar from "./nav";
import MakeNote from "./MakeNote";


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

function selection() {
    if (document.getSelection) {
        var content = document.getSelection();
        var put = document.createElement("p");
        put.textContent = content;

        document.getElementById("addTo").appendChild(put);

        var Annotation = document.createElement("textarea");

        document.getElementById("addTo").appendChild(Annotation);
    }
}

function createHighlight() {
    if (document.getSelection) {
        replaceSelection(
            "<a href='google.com'><b>" + document.getSelection() + "</b></a>"
        );
    }
}


function App() {












    return (
        <div className="container-fluid">


            {/*{client({method: 'GET', path: '/api/employees'}).then(response => {*/}
            {/*  console.log(response.entity._embedded.employees)})};*/}
            {/*});*/}

            <p><EmpApp /></p>

            <Navbar/>

            <div className="row">
                <div id="notes" className="col-3">
                    <p></p>
                    <h1 className="center">Your Study Guide</h1>

                    <ul class="nav nav-tabs">
                        <li class="nav-item">
                            <a class="nav-link active" href="#">
                                Take Notes
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">
                                View Notes
                            </a>
                        </li>

                        <li class="nav-item">
                            {/* <div id="all-notes"> */}
                            <a class="nav-link" href="#">Team/Class Discussion</a>


                        </li>

                        {/* <li class="nav-item dropdown">
              <a
                class="nav-link dropdown-toggle"
                data-toggle="dropdown"
                href="#"
                role="button"
                aria-haspopup="true"
                aria-expanded="false"
              >
                Dropdown
              </a>
              <div class="dropdown-menu">
                <a class="dropdown-item" href="#">
                  Action
                </a>
                <a class="dropdown-item" href="#">
                  Another action
                </a>
                <a class="dropdown-item" href="#">
                  Something else here
                </a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="#">
                  Separated link
                </a>
              </div>
            </li> */}
                    </ul>

                    <button className="btn-block">Grab Selected Text</button>

                    <form className="typeText">
                        <input
                            placeholder="Type Note Name Here "
                            type="text"
                            style={{width: "100%"}}
                        />

                        <div className="row">
                            <div class="col-4" id="buttonCol">
                                <input type="checkbox"></input>
                                <span>  Publish Note</span>
                                <div class="dropdown" id="clone">
                                    <button
                                        class="btn btn-secondary dropdown-toggle"
                                        type="button"
                                        id="dropdownMenuButton"
                                        data-toggle="dropdown"
                                        aria-haspopup="true"
                                        aria-expanded="false"
                                    >
                                        Type of Note
                                    </button>

                                    <div
                                        class="dropdown-menu"
                                        aria-labelledby="dropdownMenuButton"
                                    >
                                        <a class="dropdown-item" href="#">
                                            Definition
                                        </a>
                                        <a class="dropdown-item" href="#">
                                            Overview
                                        </a>
                                        <a class="dropdown-item" href="#">
                                            Clarification
                                        </a>
                                        <a class="dropdown-item" href="#">
                                            Note
                                        </a>
                                        <a className="dropdown-item" href="#">
                                            Doc
                                        </a>
                                        <a class="dropdown-item" href="#">
                                            From Study Guide
                                        </a>
                                        <a class="dropdown-item" href="#">
                                            From User
                                        </a>
                                        <a class="dropdown-item" href="#">
                                            ...New Section
                                        </a>
                                        <a class="dropdown-item" href="#">
                                            ...New Link
                                        </a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-8">


                                <MakeNote />

                            </div>
                        </div>
                    </form>

                    {/* <StudyGuide /> */}


                </div>

                <div className="col-6">
                    <ul class="nav nav-tabs">
                        <li class="nav-item" role="presentation">
                            <a
                                class="nav-link active"
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
                        <li class="nav-item">
                            <a
                                class="nav-link"
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
                        <li class="nav-item">
                            <a
                                class="nav-link"
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
                        <li class="nav-item">
                            <a
                                class="nav-link"
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

                    <div class="tab-content" id="myTabContent">
                        <div
                            class="tab-pane fade"
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
                            class="tab-pane fade"
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
                            class="tab-pane fade"
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
                            class="tab-pane fade show active"
                            id="ALLC"
                            role="tabpanel"
                            aria-labelledby="home-tab"
                        >
                            <div className="y">
                                <h1>{definitions[0].name}</h1>
                                <h3>Contents</h3>

                                <ul class="list-group">
                                    <li class="list-group-item">


                                        Definitions{" "}
                                        <span class="badge badge-primary badge-pill">14</span>
                                    </li>
                                    <li class="list-group-item">When To Use</li>
                                    <li class="list-group-item">
                                        Walkthroughs{" "}
                                        <span class="badge badge-primary badge-pill">4</span>
                                    </li>
                                    <li class="list-group-item">General Q&A</li>
                                    <li class="list-group-item">Links & Resources</li>
                                </ul>
                            </div>


                            <p></p>

                            <hr></hr>


                            {definitions.map(createDefinition)}
                            <h1>Interactive Examples</h1>
                            <h1>Screenshots</h1>
                        </div>
                    </div>
                </div>

                <div id="addTo" className="col-3">
                    {/* <h1>Community Annotations</h1> */}
                    <div class="btn-group-vertical">
                        <form>
                            <button type="button" onClick={selection}>
                                Add Annotation
                            </button>
                        </form>
                        {/* <button>Add Note as Definition</button>
            <button>Add Note as Comment</button> */}
                        {/* <button>Add Note as Section</button> */}
                        {/* <button>Add Note as Synonymous/Alternative</button> */}
                        {/* <button>Add Link/Reference</button> */}
                        <button type="button" onClick={createHighlight}>
                            Highlight
                        </button>
                    </div>
                    <h3>Purpose of Site</h3>
                    <ul>
                        <li>
                            to curate knowledge in a way that is useful to students, and
                            organized in a way that is easy to study
                        </li>
                        <li>
                            Because no single definition or example makes sense to all people,
                            we encourage definitions to be rewritten in as many ways as
                            possible, while still meaning the same thing, we also encourage
                            annotation
                        </li>
                        <li>
                            There is no one right answer
                        </li>
                    </ul>
                    <h3>How to Use</h3>
                    <ul>
                        <li>
                            Select the definitions and examples that make the most sense to
                            you, and add them to your studyguide
                        </li>
                        <li>
                            Give back by Annotating, Adding, Commenting, Upvoting, and
                            Clarifying Content on the topic
                        </li>
                        <li>Add your own Walkthroughs and Examples</li>
                        <li>Ask and Answer Questions</li>
                    </ul>
                    <h3>Unique Features</h3>
                    <ul>
                        <li>
                            Mark up your notes the same way you would mark up a piece of paper
                        </li>
                        <li>
                            Add links or specific screenshots of content that you find useful
                        </li>
                        <li>
                            View different sources of notes all on the same page, while the
                            app keeps track of what page you took notes on/from so you can
                            always get back. This works on both the site and Wikipedia.
                        </li>
                    </ul>
                    <h3>Ways to Mark up</h3>
                    <ul>
                        <li>
                            Use "//" to add a title to a note. Use it again to end the note
                        </li>
                        <li>Use "#" to bring up and exit "styling quickly"</li>
                    </ul>
                    <hr></hr>
                </div>
            </div>
        </div>
    );
}

export default App;
