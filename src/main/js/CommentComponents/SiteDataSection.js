import React, {useEffect, useRef, useState} from "react"
import Axios from "axios";
import ReactDOM from 'react-dom';

function SiteDataSection(props) {


    let x=1;

    useEffect(() => {
        //console.log("__SDKLLJJFKLALKFJAJFALJAFKLAJFLKAJFKALJFKALKJFKL")
        ReactDOM.unstable_batchedUpdates(() => {
            loadSections("/api/pages/5efd2911d231b04eecfcd282")
        })
        //load sections for default page
    }, [x])

    const [ElementsInSection, setElementsInSection] = useState([])

    let counter = 10000

    function getNewId() {
        counter = counter + 1;
        return counter - 1
    }

    function InputArea(props){

        let counter=1

        function getNewId(){
            counter=counter+1;
            return counter-1
        }

        //add a new definition/reply
        function processKeyPress(e) {

            if (e.key === "Enter"){

                const content = e.target.value

                Axios.post("/api/Contents/",{
                    user:"default",
                    content:content,
                    noteType:props.noteType,
                    pageSource:"default"
                })

                props.reload();
            }

        }

        let areaStyle = {
            width: "70%",
            marginLeft: props.margin
        }

        return(
            <div>
            <textarea id={getNewId()} rows={1} placeholder={props.placeholder}
                      autoFocus={true} style={areaStyle} className={"form-control"}
                      onKeyPress={processKeyPress}/>
            </div>

        )
    }

    //----------------------------------------------------------------------------

    function askNewQuestion(){

        //on "enter", POST text as new CONTENT, add href to content to new SECTION,
        //add href to section to Page

        //reload

    }

    function AnswerQuestion(){

        //show input text area at bottom of section

        //on "enter" POST text as CONTENT, add href to Section.answer_refs

        //reload

    }

    function replyToQuestionOrAnswer(){

        //show input text area at bottom of question/answer comments

        //on "enter" POST text as CONTENT, add href to Content.replies

        //reload

    }

    //----------------------------------------------------------------------------

    function ReplyOptions(props) {

        const [upVotes, setUpVotes] = useState(props.upVotes)
        const [downVotes, setDownVotes] = useState(props.downVotes)

        const upRef = useRef(upVotes)
        const downRef = useRef(downVotes)

        useEffect(() => {

            Axios.patch("/api/contents/" + props.id, {
                downVotes: downVotes,
                upVotes: upVotes,
                pageName: "default"
            })

        }, [upVotes, downVotes])

        return (
            <div>
                <button id={getNewId()} onClick={props.refer.click}>Reply</button>

                <button id={getNewId()}
                        onClick={() => setUpVotes(x => x + 1)}
                >UpVote
                </button>

                {upVotes - downVotes}

                <button id={getNewId()}
                        onClick={() => setDownVotes(x => x + 1)}
                >DownVote
                </button>
            </div>
        )
    }

    //----------------------------------------------------------------------------

    function Reply(props) {

        const [loadedReply, setLoadedReply] = useState("loading reply")

        let styleAs = {
            marginLeft: "6rem",
            width: "100%",
        }

        Axios.get(props.refer).then(reply => {
            console.log("getting replies")
            setLoadedReply(
                <div>
                    <hr></hr>

                    <div className={"card"} style={styleAs}>
                        <div className={"card-body"}>
                            {reply.data.content}

                        </div>

                        {/*<ReplyOptions src={reply}/>*/}

                        {/*<ReplyOptions id={props.content.id}*/}
                        {/*                upVotes={props.content.upVotes}*/}
                        {/*                downVotes={props.content.downVotes}*/}
                        {/*                reload={props.reload}*/}
                        {/*                click={props.click}/>*/}
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

        const [loadedAnswer, setLoadedAnswer] = useState("loading answer")
        const [loadedReplies, setLoadedReplies] = useState([])

        useEffect(()=>{

        //load answer from reference
        Axios.get(props.refer).then(answer => {
            console.log("getting answers")
            console.log(answer.data.content)
            console.log(answer.data.reply_refs)

            // loadedAnswer=answer.data.content
            setLoadedAnswer(answer.data.content)
            //t=answer.data.content

                //create react objects from comment references
                // setLoadedReplies(
                //     answer.data.reply_refs.map(reply_ref => {
                //         return (
                //             <div>
                //                 <Reply refer={reply_ref}/>
                //             </div>
                //         )
                //     })
                // )
            }
        )
        },[])

        return (
            <div>
                <h2>{loadedAnswer}</h2>
                <p>{loadedReplies}</p>
                <InputArea placeholder={"Enter A New Reply"} action={replyToQuestionOrAnswer}/>
            </div>
        )

    }

    //renders each question, and loads the questions comments, and the answers
    function Section(props) {

        const [loadedAnswers, setLoadedAnswers] = useState([])
        const [loadedReplies, setLoadedReplies] = useState([])
        const [loadedQuestion, setLoadedQuestion] = useState("loading question")

        //load section from reference

        useEffect(()=>

        Axios.get(props.refer).then(section => {
            console.log("getting sections")
            console.log(section.data)

                //load Question from reference
                Axios.get(section.data.question_ref).then(question => {
                    console.log("getting questions")
                    console.log(question.data)

                        setLoadedQuestion(question.data.content)

                        //create react objects from comment references
                        // setLoadedReplies(
                        //     question.data.reply_refs.map(reply_ref => {
                        //         return (
                        //             <div>
                        //                 <Reply key={"ia"+getNewId()} refer={reply_ref}/>
                        //             </div>
                        //         )
                        //     })
                        // )
                    }
                )

                //create react object from answer references

            // const arr_ref = []
            //
            // section.data.answer_refs.map(answer_ref=>{
            //     arr_ref.push(
            //         <div>
            //             <Answer key={"ia"+getNewId()} refer={answer_ref}/>
            //         </div>
            //     )
            // })
            //
            // setLoadedAnswers(arr_ref)


                setLoadedAnswers(
                    section.data.answer_refs.map(answer_ref => {
                        return (
                                <Answer key={"ia"+getNewId()} refer={answer_ref}/>
                        )
                    }
                ))
            }
        )

        ,[])

        return (
            <div>
                <h2>{loadedQuestion}</h2>
                <p>{loadedReplies}</p>
                <InputArea key={"ia"+getNewId()} placeholder={"Enter A New Reply"} action={replyToQuestionOrAnswer}/>
                <h4>{loadedAnswers}</h4>
            </div>
        )
    }

    //this loads each section for the page
    function loadSections(page) {
        //get sections for page
        Axios.get(page).then(pageObject => {
                {
                    console.log("getting section refs")

                    console.log(pageObject.data)
                    console.log(pageObject.data.section_refs)

                    //pageObject.data.
                    setElementsInSection(
                        pageObject.data.section_refs.map(refer => {

                            console.log(refer)

                            return (
                                <div key={"ia"+getNewId()}>
                                    <Section refer={refer}/>
                                    <InputArea key={"ia"+getNewId()} placeholder={"Answer Question"} action={AnswerQuestion}/>
                                </div>
                            )
                        })
                    )
                }
            }
        )
    }

    //----------------------------------------------------------------------------

    return (
        <div >
            <h1>The Docs</h1>
            <InputArea key={"ia"+getNewId()} placeholder={"Enter A New Question"} action={askNewQuestion}/>
            {ElementsInSection}
        </div>
    )
}

export default SiteDataSection