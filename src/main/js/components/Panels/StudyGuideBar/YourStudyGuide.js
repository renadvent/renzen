import MakeNote from "./Components/MakeNote";
import DataLoader from "../../1stParty/DataLoader";
import React from "react";
import {getAnnotationPosition} from "../../1stParty/functions";
import NoteTypeSelector from "./Components/NoteTypeSelector";

//
function YourStudyGuide() {
    return (

        // gets space for this section (left-hand side)
        <div id="notes" className="col-3 ">

            {/*makes study guide section stay on screen*/}
            <div className="sticky-top">


                <h1 id="topPage" className="center">Your Study Guide</h1>

                {/*sets up nav tabs*/}
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
                </ul>

                {/*sets up nav tab contents*/}
                <div className="tab-content" id="noteTabContent">
                    <div className="tab-pane fade show active" id="noteSection" role="tabpanel">

                        {/*name note*/}
                        <form className="typeText" id="nameNote">
                            <input
                                placeholder="Type Note Name Here "
                                type="text"
                                style={{width: "100%"}}
                            />

                            {/*create row*/}
                            <div className="row">
                                <div className="col-4" id="buttonCol">
                                    <input type="checkbox"></input>
                                    <span>  Publish Note</span>

                                    {/*create dropdown for note type selection*/}
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

                                        {/*note dropdown contents*/}
                                        <div
                                            className="dropdown-menu"
                                            aria-labelledby="dropdownMenuButton"
                                        >

                                            <NoteTypeSelector name={"Definition"}/>
                                            {/*<NoteTypeSelector name={"Employee"}/>*/}
                                            {/*<NoteTypeSelector name={"Overview"}/>*/}
                                            {/*<NoteTypeSelector name={"Clarification"}/>*/}
                                            <NoteTypeSelector name={"Note"}/>
                                            {/*<NoteTypeSelector name={"Doc"}/>*/}


                                            {/*<a className="dropdown-item" href="#">*/}
                                            {/*    Definition*/}
                                            {/*</a>*/}
                                            {/*<a className="dropdown-item" href="#">*/}
                                            {/*    Overview*/}
                                            {/*</a>*/}
                                            {/*<a className="dropdown-item" href="#">*/}
                                            {/*    Clarification*/}
                                            {/*</a>*/}
                                            {/*<a className="dropdown-item" href="#">*/}
                                            {/*    Note*/}
                                            {/*</a>*/}
                                            {/*<a className="dropdown-item" href="#">*/}
                                            {/*    Doc*/}
                                            {/*</a>*/}
                                            {/*<a className="dropdown-item" href="#">*/}
                                            {/*    From Study Guide*/}
                                            {/*</a>*/}
                                            {/*<a className="dropdown-item" href="#">*/}
                                            {/*    From User*/}
                                            {/*</a>*/}
                                            {/*<a className="dropdown-item" href="#">*/}
                                            {/*    ...New Section*/}
                                            {/*</a>*/}
                                            {/*<a className="dropdown-item" href="#">*/}
                                            {/*    ...New Link*/}
                                            {/*</a>*/}
                                        </div>
                                    </div>

                                    <div id={"selectedType"}>Note</div>

                                </div>
                                <div className="col-8">
                                </div>
                            </div>
                        </form>
                        <MakeNote/>
                    </div>

                    {/*display loaded notes*/}
                    <div className="tab-pane fade" id="loadedNotes" role="tabpanel">
                        <DataLoader data={"/Notes"}/>
                    </div>
                </div>

                {/*grab text and annotate text*/}
                <button className="btn-block">Grab Selected Text</button>
                <button id="click" onClick={getAnnotationPosition}>CLICK HERE TO TEST ANNOTATION</button>
            </div>
        </div>

    )
}

export default YourStudyGuide
