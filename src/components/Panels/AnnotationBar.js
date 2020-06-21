import React from "react";
import {replaceSelection} from "../AnnotationBarComponents/replaceSelection";


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
    return (
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
    )
}

export default AnnotationBar