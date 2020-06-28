import React,{useState} from "react"
import DefinitionTab from "./DefinitionTab";
import {createComment} from "../1stParty/functions";
import CommentArea from "./CommentArea";
import CommentTextArea from "./CommentTextArea";




function SiteDataSection(props){



    let [ElementsInSection,setElementsInSection] = useState([])

    // function useEffect(){
    //     setElementsInSection([])
    // }




    let counter=10000

    function getNewId(){
        counter=counter+1;
        return counter-1
    }



    function loadCommentSection(){

    }

    // //when pressing enter, save the comment to the database
    // function createTextArea(former) {
    //     let el = document.createElement("textarea")
    //     el.setAttribute("id", getNewId())
    //     el.setAttribute("rows", 1)
    //     el.setAttribute("placeholder", "Type comment here")
    //     el.autofocus = true
    //     el.style.width="70%"
    //     el.style.marginLeft="6rem"
    //     // el.style.alignContent="flex-end"
    //     //el.setAttribute("style",)
    //
    //     el.className="form-control"
    //     el.addEventListener("keypress", processKeyPress)
    //     console.log(former)
    //     former.insertAdjacentElement("afterend",el)
    //     return el;
    // }

    function createReply(former) {
        //createTextArea(former)

        // function createTextArea(former) {
        //     let el = document.createElement("textarea")
        //     el.setAttribute("id", getNewId())
        //     el.setAttribute("rows", 1)
        //     el.setAttribute("placeholder", "Type comment here")
        //     el.className="form-control"
        //     el.style.width="70%"
        //     el.style.marginLeft="6rem"
        //     el.autofocus = true
        //     el.addEventListener("keypress", processKeyPress)
        //     console.log(former)
        //     //former.insertAdjacentElement("afterend",el)
        //
        //     document.getElementById(former.target.id).insertAdjacentElement("beforebegin",el)
        //     el.insertAdjacentHTML("beforebegin","<span>++++</span>")
        //
        // }

        let Arep = document.createElement("a")
        Arep.innerText="Reply"
        Arep.href="#"
        Arep.id=getNewId()
        Arep.addEventListener("click",(former) => createTextArea(former),false)
        former.insertAdjacentElement("afterend",Arep)
    }

//



    function addNewContent(event){
        console.log(event)
        console.log(event.target)
        console.log(event.target.id)

        let id=event.target.id


        setElementsInSection(ElementsInSection.concat(<CommentTextArea/>))
        //ElementsInSection.push(<CommentTextArea/>)

        // let el = document.createElement("textarea")
        // el.setAttribute("id",getNewId())
        // el.setAttribute("rows",1)
        // el.setAttribute("placeholder", "Type comment here")
        // el.autofocus=true
        // el.className="form-control"
        // el.addEventListener("keypress",processKeyPress)
        // document.getElementById(id).insertAdjacentElement("afterend",el)

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
            {ElementsInSection}


            {/*<button onClick={addNewContent}>Add Definition</button>*/}
            <p>
                {/*{props.author}'s Definition{" "}*/}
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
            {/*{props.loadedDefs}*/}

            {/*<p className="info">{props.content}</p>*/}


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