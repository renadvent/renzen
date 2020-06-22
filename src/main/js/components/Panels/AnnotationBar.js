import React from "react";
import defaultText from "../AnnotationBarComponents/DefaultText"
import {annotateSelection, highlightSelection} from "../1stParty/functions";

function AnnotationBar(){

    return(

    <div id="addTo" className="col-3">
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

    )
}

export default AnnotationBar