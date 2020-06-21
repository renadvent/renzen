import MakeNote from "../MakeNote";
import React from "react";


function YourStudyGuide (){
    return (
        //<div className="row">

            <div id="notes" className="col-3">
                <p></p>
                <h1 className="center">Your Study Guide</h1>

                <ul className="nav nav-tabs">
                    <li className="nav-item">
                        <a className="nav-link active" href="#">
                            Take Notes
                        </a>
                    </li>
                    <li className="nav-item">
                        <a className="nav-link" href="#">
                            View Notes
                        </a>
                    </li>

                    <li className="nav-item">
                        {/* <div id="all-notes"> */}
                        <a className="nav-link" href="#">Team/Class Discussion</a>


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
                        <div className="col-4" id="buttonCol">
                            <input type="checkbox"></input>
                            <span>  Publish Note</span>
                            <div className="dropdown" id="clone">
                                <button
                                    className="btn btn-secondary dropdown-toggle"
                                    type="button"
                                    id="dropdownMenuButton"
                                    data-toggle="dropdown"
                                    aria-haspopup="true"
                                    aria-expanded="false"
                                >
                                    Type of Note
                                </button>

                                <div
                                    className="dropdown-menu"
                                    aria-labelledby="dropdownMenuButton"
                                >
                                    <a className="dropdown-item" href="#">
                                        Definition
                                    </a>
                                    <a className="dropdown-item" href="#">
                                        Overview
                                    </a>
                                    <a className="dropdown-item" href="#">
                                        Clarification
                                    </a>
                                    <a className="dropdown-item" href="#">
                                        Note
                                    </a>
                                    <a className="dropdown-item" href="#">
                                        Doc
                                    </a>
                                    <a className="dropdown-item" href="#">
                                        From Study Guide
                                    </a>
                                    <a className="dropdown-item" href="#">
                                        From User
                                    </a>
                                    <a className="dropdown-item" href="#">
                                        ...New Section
                                    </a>
                                    <a className="dropdown-item" href="#">
                                        ...New Link
                                    </a>
                                </div>
                            </div>
                        </div>
                        <div className="col-8">


                            <MakeNote/>

                        </div>
                    </div>
                </form>

                {/* <StudyGuide /> */}


            </div>

    )
}

export default YourStudyGuide
