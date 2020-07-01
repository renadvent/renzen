import React, {useEffect, useState} from "react"
import DefinitionTab from "../DefinitionTab";
import CommentTextArea from "./CommentTextArea";
import CommentOptions from "./CommentOptions";

import Axios from "axios";
import SaveToButton from "./SaveToButton";
import CommentNav from "./CommentNav";

import Comment from "./Comment";


function SiteDataSection(props) {

    const [ElementsInSection, setElementsInSection] = useState([])

    let counter = 10000

    function getNewId() {
        counter = counter + 1;
        return counter - 1
    }

    function loadCommentSection() {

        Axios.get("/api/pageSource?pageSource=default").then(notes => {
                //do something with restult
                const arr = []

                notes.data.map(note => {
                    //render a note for each one

                    console.log(note)

                    //create comment
                    arr.push(
                        <div>
                            <Comment content={note} />
                        </div>
                    )
                })

                setElementsInSection(x =>
                    x.concat(arr)
                )
            }
        )
    }

    function addNewReply(e) {
        setElementsInSection(x => x.concat([
            <CommentTextArea noteType={"reply"} placeholder={"Type reply here"} margin={"6rem"}/>])
        )
    }

    function addNewContent(e) {
        setElementsInSection(x => x.concat([
            <CommentTextArea noteType={"def"} placeholder={"Type Definition Here"}/>,
            <CommentOptions click={addNewReply.bind(this)}/>])
        )
    }

    useEffect(() => {
        loadCommentSection()
    }, [])

    return (

        <div className="card">
            {/* <h2>{props.name}</h2> */}
            <h1>The Docs</h1>

            <ul className="nav nav-tabs">

                <DefinitionTab name={"Getting Started"} id={"#a"} linkTo={"#gettingStarted"} active={"active"}/>
                {/*<DefinitionTab name={"Featured"} id={"#b"} linkTo={"#featured"}/>*/}
                {/*<DefinitionTab name={"Top Voted"} id={"#c"} linkTo={"#popular"}/>*/}
                {/*<DefinitionTab name={"Newest"} id={"#d"} linkTo={"#newest"}/>*/}
                {/*<DefinitionTab name={"Your Definitions"} id={"#e"} linkTo={"#yourdefs"}/>*/}

            </ul>
            <button id={"TEST" + getNewId()}
                    onClick={event => addNewContent(event)}>Add Definition
            </button>

            {ElementsInSection}

            {/*<SaveToButton/>*/}

            <div className="comments">
                <button>Add Comment</button>
            </div>
            {/*<CommentNav/>*/}
        </div>


    )
}

export default SiteDataSection