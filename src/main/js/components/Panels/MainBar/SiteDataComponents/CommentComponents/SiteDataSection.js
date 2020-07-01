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

    // function addNewReply(e) {
    //     setElementsInSection(x => x.concat([
    //         <CommentTextArea noteType={"reply"} placeholder={"Type reply here"} margin={"6rem"}/>])
    //     )
    // }
    //
    // function addNewDefinition(e) {
    //     setElementsInSection(x => x.concat([
    //         <CommentTextArea reload={loadSection} noteType={"def"} placeholder={"Type Definition Here"}/>,
    //         <CommentOptions reload={loadSection} click={()=>addNewReply.bind(this)}/>])
    //     )
    // }

    //default load all sections

    //----------------------------------------------------------------------------

    function Comment(props) {

        let styleAs ={
            marginLeft:props.indent
        }

        // switch (props.content.noteType) {
        //     case "reply":
        //         styleAs={
        //             // width: "100%",
        //             marginLeft: "6rem"
        //         }
        //         break;
        //     case "def":
        //         styleAs={
        //             width: "100%",
        //         }
        //         break;
        // }

        return (
            <div>
                <hr></hr>

                <div className={"card"} style={styleAs}>
                    <div className={"card-body"} >
                        {props.content.content}

                    </div>
                    <CommentOptions id={props.content.id}
                                    upVotes={props.content.upVotes}
                                    downVotes={props.content.downVotes}
                                    reload={props.reload}
                                    click={props.click}/>
                </div>
            </div>
        );
    }

    //this renders
    function Answer(props) {

        props=props.ref;
        const loadedComments = []

        props.Comments.map(comments => {
            Axios.get(props.Comments).then(comments=>{
                const loadedComments = []
                loadedComments.push(
                    <div>
                        <Comment ref={comments}/>
                    </div>
                )
            })
        })

        return (
            <div>
                <h2>{props.content}</h2>
                <p>{loadedComments}</p>
            </div>
        )

    }

    //renders each question, and loads the questions comments, and the answers
    function Section(props){

        props=props.ref;

        props.Answers.map(answer => {
            Axios.get(props.Answers).then(answers=>{
            const loadedAnswers = []
            loadedAnswers.push(
                <div>
                    <Answer ref={answer}  />
                </div>
            )})})

        props.Comments.map(comments => {
            Axios.get(props.Comments).then(comments=>{
                const loadedComments = []
                loadedComments.push(
                    <div>
                        <Comment ref={comments}/>
                    </div>
                )
            })
        })

        return (
            <div>
                <h2>{props.Question}</h2>
                <p>{loadedComments}</p>
                <h4>{loadedAnswers}</h4>
            </div>
        )
    }

    //this loads each section for the page
    function loadSections(page) {
        //get sections for page
        Axios.get(page).then(sections => {

            const loadedSections = []

                sections.map(section => {
                    loadedSections.push(
                        <div>
                            <Section ref={section} />
                        </div>
                    )
                })
            setElementsInSection(loadedSections);
            }
        )
    }

    //----------------------------------------------------------------------------

    useEffect(() => {
        loadSections("/api/section?pageSource=default&noteType=def")
    }, [])

    return (

        <div className="card">
            <h1>The Docs</h1>

            <ul className="nav nav-tabs">

                <DefinitionTab name={"Getting Started"} id={"#a"} linkTo={"#gettingStarted"} active={"active"}/>
                {/*<DefinitionTab name={"Featured"} id={"#b"} linkTo={"#featured"}/>*/}
                {/*<DefinitionTab name={"Top Voted"} id={"#c"} linkTo={"#popular"}/>*/}
                {/*<DefinitionTab name={"Newest"} id={"#d"} linkTo={"#newest"}/>*/}
                {/*<DefinitionTab name={"Your Definitions"} id={"#e"} linkTo={"#yourdefs"}/>*/}

            </ul>
            <button id={"TEST" + getNewId()}
                    onClick={event => addNewDefinition(event)}>Add Definition
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