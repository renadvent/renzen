import React from "react";
import {replaceSelection} from "../AnnotationBarComponents/replaceSelection";
import defaultText from "../AnnotationBarComponents/DefaultText"


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


function AnnotationBar(){

    return(

    <div id="addTo" className="col-3">
        {/* <h1>Community Annotations</h1> */}
        <div className="btn-group-vertical">
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

        <div id="annoBar">

            {defaultText}
        </div>


    </div>
    )

}

export default AnnotationBar