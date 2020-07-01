import React, {useEffect, useRef, useState} from "react"
import CommentOptions from "./CommentOptions";

import Axios from "axios";

function SiteDataSection(props) {

    const [ElementsInSection, setElementsInSection] = useState([])

    let counter = 10000

    function getNewId() {
        counter = counter + 1;
        return counter - 1
    }

    // function addNewReply(e) {
    //     setElementsInSection(x => x.concat([
    //         <InputArea noteType={"reply"} placeholder={"Type reply here"} margin={"6rem"}/>])
    //     )
    // }
    //
    // function addNewDefinition(e) {
    //     setElementsInSection(x => x.concat([
    //         <InputArea reload={loadSection} noteType={"def"} placeholder={"Type Definition Here"}/>,
    //         <CommentOptions reload={loadSection} click={()=>addNewReply.bind(this)}/>])
    //     )
    // }

    //default load all sections

    //----------------------------------------------------------------------------

    function CommentOptions(props) {

        const [upVotes, setUpVotes] = useState(props.upVotes)
        const [downVotes, setDownVotes] = useState(props.downVotes)

        const upRef=useRef(upVotes)
        const downRef=useRef(downVotes)

        useEffect(()=>{

            Axios.patch("/api/x_Notes/"+props.id, {
                downVotes: downVotes,
                upVotes: upVotes,
                pageName: "default"
            })

        },[upVotes,downVotes])

        function handleChange(event){
            props.click();

        }

        return (
            <div>
                <button id={getNewId()} onClick={handleChange}>Reply</button>

                <button id={getNewId()}
                        onClick={()=>setUpVotes(x=> x+1)}
                >UpVote</button>

                {upVotes-downVotes}

                <button id={getNewId()}
                        onClick={()=>setDownVotes(x=>x+1)}
                >DownVote</button>
            </div>
        )
    }

    //----------------------------------------------------------------------------

    function Reply(props) {

        const [loadedReply,setLoadedReply] = useState("loading")

        let styleAs = {
            marginLeft: "6rem",
            width: "100%",
        }

        Axios.get(props.ref).then(reply => {
            setLoadedReply(
                <div>
                    <hr></hr>

                    <div className={"card"} style={styleAs}>
                        <div className={"card-body"}>
                            {props.content.content}

                        </div>
                        <CommentOptions id={props.content.id}
                                        upVotes={props.content.upVotes}
                                        downVotes={props.content.downVotes}
                                        reload={props.reload}
                                        click={props.click}/>
                    </div>
                </div>
            )
        })

        return (
            <div>
                {loadedReply}
            </div>
        );
    }

    //this renders
    function Answer(props) {

        const [loadedAnswer, setLoadedAnswer] = useState("loading")
        const [loadedReplies, setLoadedReplies] = useState([])

        //load answer from reference
        Axios.get(props.ref).then(answer => {
                setLoadedAnswer(answer.content)

                //create react objects from comment references
                setLoadedReplies(
                    answer.reply_refs.map(reply_ref => {
                        return (
                            <div>
                                <Reply ref={reply_ref}/>
                            </div>
                        )
                    })
                )
            }
        )

        return (
            <div>
                <h2>{loadedAnswer}</h2>
                <p>{loadedReplies}</p>
            </div>
        )

    }

    //renders each question, and loads the questions comments, and the answers
    function Section(props) {

        const [loadedAnswers, setLoadedAnswers] = useState([])
        const [loadedReplies, setLoadedReplies] = useState([])
        const [loadedQuestion, setLoadedQuestion] = useState("loading")

        //load section from reference
        Axios.get(props.ref).then(section => {

                //load Question from reference
                Axios.get(section.question_ref).then(question => {
                        setLoadedQuestion(question.content)

                        //create react objects from comment references
                        setLoadedReplies(
                            question.reply_refs.map(reply_ref => {
                                return (
                                    <div>
                                        <Reply ref={reply_ref}/>
                                    </div>
                                )
                            })
                        )
                    }
                )

                //create react object from answer references
                setLoadedAnswers(
                    section.answer_refs.map(answer_ref => {
                        return (
                            <div>
                                <Answer ref={answer_ref}/>
                            </div>
                        )
                    })
                )
            }
        )

        return (
            <div>
                <h2>{loadedQuestion}</h2>
                <p>{loadedReplies}</p>
                <h4>{loadedAnswers}</h4>
            </div>
        )
    }

    //this loads each section for the page
    function loadSections(page) {
        //get sections for page
        Axios.get(page).then(pageObject => {

                const loadedSections = []

                pageObject.section_refs.map(ref => {
                    loadedSections.push(
                        <div>
                            <h1>{pageObject.pageName}</h1>
                            <Section ref={ref}/>
                        </div>
                    )
                })
                setElementsInSection(loadedSections);
            }
        )
    }

    //----------------------------------------------------------------------------

    useEffect(() => {

        loadSections("/api/Sections/default") //load sections for default page
        //loadSections("/api/section?pageSource=default&noteType=def")
    }, [])

    return (

        <div>
            {ElementsInSection}
        </div>

        // <div className="card">
        //     <h1>The Docs</h1>
        //
        //     <ul className="nav nav-tabs">
        //
        //         <DefinitionTab name={"Getting Started"} id={"#a"} linkTo={"#gettingStarted"} active={"active"}/>
        //         {/*<DefinitionTab name={"Featured"} id={"#b"} linkTo={"#featured"}/>*/}
        //         {/*<DefinitionTab name={"Top Voted"} id={"#c"} linkTo={"#popular"}/>*/}
        //         {/*<DefinitionTab name={"Newest"} id={"#d"} linkTo={"#newest"}/>*/}
        //         {/*<DefinitionTab name={"Your Definitions"} id={"#e"} linkTo={"#yourdefs"}/>*/}
        //
        //     </ul>
        //     <button id={"TEST" + getNewId()}
        //             onClick={event => addNewDefinition(event)}>Add Definition
        //     </button>
        //
        //     {ElementsInSection}
        //
        //     {/*<SaveToButton/>*/}
        //
        //     <div className="comments">
        //         <button>Add Comment</button>
        //     </div>
        //     {/*<CommentNav/>*/}
        // </div>


    )
}

export default SiteDataSection