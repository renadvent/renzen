import React from "react"
import DefinitionTab from "./DefinitionTab";
import {createComment} from "../1stParty/functions";
import CommentArea from "./CommentArea";




function SiteDataSection(props){

    let counter=1

    function getNewId(){
        counter=counter+1;
        return counter-1
    }

    function processKeyPress(e) {

        if (e.key === "Enter"){
            //process add content
            console.log("process add content")

            //const content = e.target.innerText

            const content = e.target.value

            let former = document.createElement("p")

            former.innerText=content

            document.getElementById(e.target.id).insertAdjacentElement("beforebegin",former)

            //document.getElementById(e.target.id).insertAdjacentHTML("beforebegin",content)

            const reqOptions = {
                method : 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify(
                    {
                        user:"default",
                        content : content,
                        noteType : "comment",
                        pageSource : "default"
                        })
            }

            fetch("/Notes",reqOptions)

            console.log(e.target.id)
            document.getElementById(e.target.id).value=""
            //document.getElementById(e.target.id).insertAdjacentHTML("beforebegin",content)
        }

    }


    function addNewContent(event){
        console.log(event)
        console.log(event.target)
        console.log(event.target.id)

        let id=event.target.id



        let el = document.createElement("textarea")
        el.setAttribute("id",getNewId())
        el.setAttribute("rows",1)
        el.setAttribute("placeholder", "Type comment here")
        el.autofocus=true
        el.addEventListener("keypress",processKeyPress)
        document.getElementById(id).insertAdjacentElement("afterend",el)

        //get note content and render as a <p> element




    }





    return (

        <div className="card">
            {/* <h2>{props.name}</h2> */}
            <h1>The Docs</h1>

            <ul className="nav nav-tabs">

                <DefinitionTab name={"Getting Started"} id={"#"} linkTo={"#gettingStarted"} active={"active"}/>
                <DefinitionTab name={"Featured"} id={"#"} linkTo={"#featured"}/>
                <DefinitionTab name={"Top Voted"} id={""} linkTo={"#popular"}/>
                <DefinitionTab name={"Newest"} id={"#"} linkTo={"#newest"}/>
                <DefinitionTab name={"Your Definitions"} id={"#"} linkTo={"#yourdefs"}/>

            </ul>

            {/*<button onClick={this => addNewContent(this)}>Add Definition</button>*/}
            <button id={"TEST" + getNewId()} onClick={event => addNewContent(event)}>Add Definition</button>
            {/*<button onClick={addNewContent}>Add Definition</button>*/}
            <p>
                {props.author}'s Definition{" "}
                <span>
          <div className="dropdown">
            <button
                className="x btn btn-secondary dropdown-toggle"
                type="button"
                id="dropdownMenuButton"
                data-toggle="dropdown"
                aria-haspopup="true"
                aria-expanded="false"
            >
              Save to Study Guide
            </button>
            <div className="dropdown-menu" aria-labelledby="dropdownMenuButton">
              <a className="dropdown-item" href="#">
                Your Study Guide
              </a>
              <a className="dropdown-item" href="#">
                Team Study Guide
              </a>
              <a className="dropdown-item" href="#">
                Your Notes
              </a>
            </div>
          </div>
        </span>
                <span>Upvotes+</span>
                <span className="badge badge-primary badge-pill">14</span>
            </p>


            {/*working here*/}
            {props.loadedDefs}

            <p className="info">{props.content}</p>


            <div className="comments">
                {/*{props.comments.map(createComment)}*/}
                <button>Add Comment</button>
            </div>
            <nav aria-label="Page navigation example">
                <ul className="pagination">
                    <li className="page-item"><a className="page-link" href="#">Previous</a></li>
                    <li className="page-item"><a className="page-link" href="#">1</a></li>
                    <li className="page-item"><a className="page-link" href="#">2</a></li>
                    <li className="page-item"><a className="page-link" href="#">3</a></li>
                    <li className="page-item"><a className="page-link" href="#">Next</a></li>
                </ul>
            </nav>
            {/*<button>View More Definitions ^</button>*/}


</div>


    )
}

export default SiteDataSection