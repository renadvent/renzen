import React from "react";
import UseCases from "../SiteDataComponents/UseCases";
import QA from "../SiteDataComponents/QA";
import Comment from "../SiteDataComponents/Comment";
import Collapse from "../SiteDataComponents/collapse";
import {replaceSelection} from "../3rdParty/replaceSelection";

function aClick() {

    //
    // function getPositionXY(element) {
    //     var rect = element.getBoundingClientRect();
    //     document.getElementById('annoBar').innerHTML =
    //         'X: ' + rect.x + ', ' + 'Y: ' + rect.y
    // }

    var el = document.getElementById("annoBar")
    el.style.position = "absolute"
    //el.style={"{top:50px;left:100px;}"}

    el.style["top"] = "50px"
    el.style["left"] = "100px"

    el.innerText = "SWITCHED AGAIN!!"

}

export function getAnnotationPosition() {
    var el = document.getElementById("annoBar")
    el.style.position = "absolute"
    var d = document.getElementById("topPage")
    var offset = $(d).offset()
    el.style["top"] = offset.top + "px"
    el.innerText = "SHOW ANNOTATION HERE!!"
}

export function createComment(props) {
    return (
        <div>
            <Comment author={props.author} comment={props.comment} key={props.id}/>
        </div>
    );
}

export function createUse(props) {
    return (
        <div>
            <UseCases situation={props.situation} example={props.example}/>

            <span>
        <button class="x">Save to Study Guide</button>
      </span>
        </div>
    );
}

export function createQA(props) {
    return (
        <div>
            <QA Q={props.Q} A={props.A}/>
        </div>
    );
}

export function createWalkthrough(props) {
    return (
        <div>
            <h3>
                <a href="">{props.name} </a>
                <span class="badge badge-secondary">{props.type}</span>
            </h3>
            <p>
                <span class="badge badge-secondary">{props.description}</span>
            </p>

            {props.tags.map((x) => {
                return (
                    <span>
            <span class="badge badge-secondary">{x}</span>
            <span
                style={{borderRadius: "5px", backgroundColor: "#00cec9"}}
            />
          </span>
                );
            })}

            <Collapse
                name={props.name}
                description={props.description}
                key={props.id}
                id={props.id}
            />
        </div>
    );
}

export function createDefinition(props) {
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

export function annotateSelection() {
    if (document.getSelection) {
        var content = document.getSelection();
        var put = document.createElement("p");
        put.textContent = content;

        document.getElementById("addTo").appendChild(put);

        var Annotation = document.createElement("textarea");

        document.getElementById("addTo").appendChild(Annotation);
    }
}

export function highlightSelection() {
    if (document.getSelection) {
        replaceSelection(
            "<a href='google.com'><b>" + document.getSelection() + "</b></a>"
        );
    }
}