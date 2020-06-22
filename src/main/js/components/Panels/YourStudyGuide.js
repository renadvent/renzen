import MakeNote from "../YourStudyGuideComponents/MakeNote";
import NoteObject from "../YourStudyGuideComponents/LoadedNotes";
import React from "react";


function aClick() {

    //
    // function getPositionXY(element) {
    //     var rect = element.getBoundingClientRect();
    //     document.getElementById('annoBar').innerHTML =
    //         'X: ' + rect.x + ', ' + 'Y: ' + rect.y
    // }

    var el = document.getElementById("annoBar")
    el.style.position = "absolute"
    //el.style.margin="auto auto auto auto"
    //el.style={"{top:50px;left:100px;}"}


    var d = document.getElementById("topPage")
    //var rect=d.getBoundingClientRect();

    //console.log(rect.x)
    //console.log(rect.y)

    //rect.

    var offset = $(d).offset()

    console.log(offset.left)
    console.log(offset.top)

    //el.scroll(rect.x,rect.y)
    //el.style["left"]=offset.x+"px"
    el.style["top"] = offset.top + "px"

    el.innerText = "SHOW ANNOTATION HERE!!"

}

function YourStudyGuide() {
    return (
        //<div className="row">

        <div id="notes" className="col-3 ">
            <div className="sticky-top">
                <p></p>
                <h1 id="topPage" className="center">Your Study Guide</h1>

                <ul className="nav nav-tabs">
                    <li className="nav-item" role="presentation">
                        <a className="nav-link active" href="#noteSection" data-toggle="tab" role="tab">
                            Take Notes
                        </a>
                    </li>
                    <li className="nav-item">
                        <a className="nav-link" href="#loadedNotes" data-toggle="tab" role="tab">
                            View Notes
                        </a>
                    </li>

                    <li className="nav-item">

                        <a className="nav-link" href="#" data-toggle="tab" role="tab">Team/Class Discussion</a>


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

                <div className="tab-content" id="noteTabContent">
                    <div className="tab-pane fade show active" id="noteSection" role="tabpanel">


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


                                </div>
                            </div>
                        </form>

                        <MakeNote/>


                    </div>

                    <div className="tab-pane fade" id="loadedNotes" role="tabpanel">

                        <NoteObject/>


                    </div>
                </div>

                <button className="btn-block">Grab Selected Text</button>


                <button id="click" onClick={aClick}>CLICK HERE TO TEST ANNOTATION</button>
            </div>
            {/* <StudyGuide /> */}


        </div>

    )
}

export default YourStudyGuide
