import React,{useState} from "react"
import DefinitionTab from "./DefinitionTab";
import {createComment} from "../1stParty/functions";
import CommentArea from "./CommentArea";
import CommentTextArea from "./CommentTextArea";
import CommentOptions from "./CommentOptions";




function SiteDataSection(props){

    const [ElementsInSection,setElementsInSection] = useState([])

    let counter=10000

    function getNewId(){
        counter=counter+1;
        return counter-1
    }

    function loadCommentSection(){

        const reqOptions = {
            method : 'GET',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(
                {
                    user:"default",
                    content : content,
                    noteType : "comment",
                    pageSource : "default"
                })
        }

        let notes=fetch("/Notes",reqOptions)



    }

    function addNewReply(e){
        setElementsInSection( x => x.concat([

            <CommentTextArea replyTo={false} placeholder={"Type reply here"} margin={"6rem"}/>]))

            // <CommentTextArea replyTo={false} placeholder={"Type reply here"} margin={"6rem"}/>,
            // <CommentOptions click={addNewReply.bind(this)}/>]))
    }

    function addNewContent(e){

        setElementsInSection( x => x.concat([

            <CommentTextArea replyTo={true} placeholder={"Type Definition Here"}/>,
            <CommentOptions click={addNewReply.bind(this)}/>]))


        //setElementsInSection( x => return(...x,...[<CommentTextArea/>,<CommentOptions click={addNewContent.bind(this)}/>])



        //setElementsInSection(ElementsInSection.concat([<CommentTextArea/>,<CommentOptions click={addNewContent.bind(this)}/>]))
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