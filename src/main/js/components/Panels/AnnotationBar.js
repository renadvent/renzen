import React from "react";
import {replaceSelection} from "../AnnotationBarComponents/replaceSelection";
import defaultText from "../AnnotationBarComponents/DefaultText"

function annotateSelection() {
    if (document.getSelection) {
        var content = document.getSelection();
        var put = document.createElement("p");
        put.textContent = content;

        document.getElementById("addTo").appendChild(put);

        var Annotation = document.createElement("textarea");

        document.getElementById("addTo").appendChild(Annotation);
    }
}

function highlightSelection() {
    if (document.getSelection) {
        replaceSelection(
            "<a href='google.com'><b>" + document.getSelection() + "</b></a>"
        );
    }
}

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