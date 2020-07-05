import React, {useEffect, useState} from "react"
import Axios from "axios";

function SiteDataSection(props) {

    let [page_ref, setPageRef] = useState("/api/pages/5efd2911d231b04eecfcd282")

    useEffect(() => {
        //console.log("__SDKLLJJFKLALKFJAJFALJAFKLAJFLKAJFKALJFKALKJFKL")
        //ReactDOM.unstable_batchedUpdates(() => {
        loadSections(page_ref)
        //})
        //load sections for default page
    }, [page_ref])

    const [ElementsInSection, setElementsInSection] = useState([])

    let counter = 10000

    function getNewId() {
        counter = counter + 1;
        return counter - 1
    }

    function reload() {
        //ReactDOM.unstable_batchedUpdates(() => {
        //setElementsInSection(()=>[])
        loadSections(page_ref)
        //})
    }

    function InputArea(props) {

        let counter = 1

        function getNewId() {
            counter = counter + 1;
            return counter - 1
        }

        let areaStyle = {
            width: "70%",
            marginLeft: props.margin
        }

        const [inputText, setInputText] = useState(props.placeholder)

        return (
            <div>
            <textarea id={getNewId()} rows={1} placeholder={props.placeholder}
                //autoFocus={true}
                      style={areaStyle} className={"form-control"}
                // onKeyPress={props.action}
                      onKeyPress={(event => props.action(event, props.section_refs))
                      }
                //   onKeyPress={(event)=> {
                //       console.log(props)
                //       props.action(event,props.section_refs)
                //   }}
                // section_ref={props.section_refs}
                // onKeyPress={processKeyPress}
            />
            </div>

        )
    }

    //----------------------------------------------------------------------------

    function askNewQuestion(e) {

        //on "enter", POST text as new CONTENT, add href to content to new SECTION,
        //add href to section to Page

        //SPRING IS STRIPPING ID FIELD FOR SOME REASON

        if (e.key === "Enter") {

            const content = e.target.value

            //create new content
            Axios.post("/api/contents/", {
                user: "default",
                content: content,
                noteType: "question",
                upVotes: 0,
                downVotes: 0,
                reply_refs: []
            }).then(postedContent => {

                //create new section and post ref in
                Axios.post("/api/sections/", {
                    question_ref: postedContent.data._links.self.href,
                    answer_refs: []
                }).then(postedSection => {

                    //get sections_refs for page, add new ref, patch
                    Axios.get(page_ref).then(page => {
                        //page.data.section_refs.push(postedSection.data._links.href)
                        //add to beginning

                        page.data.section_refs.unshift(postedSection.data._links.self.href)
                        //page.data.section_refs.unshift(postedSection.data._links.href)
                        return (page)
                    }).then((page) => {
                        Axios.patch(page_ref, {
                            section_refs: page.data.section_refs
                        })
                    }).then(() => {
                        reload();
                    })
                })
            })
        }
    }

    function AnswerQuestion(e, refer) {

        if (e.key === "Enter") {

            const content = e.target.value

            Axios.post("/api/contents", {
                user: "default",
                content: content,
                upVotes: 0,
                downVotes: 0,
                reply_refs: []
            }).then(postedContent => {
                Axios.get(refer).then(question => {
                    //post new content as an answer
                    question.data.answer_refs.push(postedContent.data._links.self.href)
                    Axios.patch(refer, {
                        answer_refs: question.data.answer_refs
                    })
                })
            }).then(() => {
                reload()
            })
        }
    }

    function replyToQuestionOrAnswer(e, refer) {

        if (e.key === "Enter") {

            const content = e.target.value

            Axios.post("/api/contents/", {
                user: "default",
                content: content,
                upVotes: 0,
                downVotes: 0,
                reply_refs: null
            }).then(postedContent => {
                Axios.get(refer).then(replyingTo => {
                    //post new content as an answer
                    console.log("refer: " + refer)
                    console.log("replyingto: " + replyingTo.data.reply_refs)
                    replyingTo.data.reply_refs.push(postedContent.data._links.self.href)
                    // replyingTo.data.reply_refs.unshift(postedContent.data._links.self.href)
                    Axios.patch(refer, {
                        reply_refs: replyingTo.data.reply_refs
                    })
                })

            }).then(() => {
                reload()
            })
        }

    }

    //----------------------------------------------------------------------------

    function ReplyOptions(props) {

        const [upVotes, setUpVotes] = useState(props.upVotes)
        const [downVotes, setDownVotes] = useState(props.downVotes)

        // const upRef = useRef(upVotes)
        // const downRef = useRef(downVotes)

        useEffect(() => {

            Axios.patch(props.src,{
            //Axios.patch("/api/contents/" + props.src.id, {
                downVotes: downVotes,
                upVotes: upVotes,
                //pageName: "default"
            }).catch(() => {
                    console.log("nothing to patch")
                }
            )

        }, [upVotes, downVotes])

        return (
            <div>
                <button id={getNewId()} onClick={(event) => replyToQuestionOrAnswer(event)}>Reply</button>

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

        useEffect(() => {

            Axios.get(props.refer).then(reply => {
                console.log("getting replies")
                setLoadedReply(
                    <div>
                        <hr></hr>

                        <div className={"card"} style={styleAs}>
                            <div className={"card-body"}>
                                {reply.data.content}

                            </div>

                            <ReplyOptions upVotes={reply.data.upVotes}
                                          downVotes={reply.data.downVotes}
                                          src={props.refer}/>

                        </div>
                    </div>
                )
            })
        }, [])

        return (
            <div>
                {loadedReply}
            </div>
        );
    }

    //this renders
    function Answer(props) {

        //console.log("getting answer: "+)

        const [loadedAnswer, setLoadedAnswer] = useState("loading answer")
        const [loadedReplies, setLoadedReplies] = useState([])

        useEffect(() => {

            //load answer from reference
            Axios.get(props.refer).then(answer => {
                    console.log("getting answers")
                    console.log(answer.data.content)
                    console.log(answer.data.reply_refs)

                    setLoadedAnswer(answer.data.content)

                    // create react objects from comment references
                    setLoadedReplies(
                        answer.data.reply_refs.map(reply_ref => {
                            return (
                                <div>
                                    <Reply refer={reply_ref}/>
                                </div>
                            )
                        })
                    )
                }
            )
        }, [])

        return (
            <div>
                <p>{loadedAnswer}</p>
                <p>{loadedReplies}</p>
                <InputArea placeholder={"Enter A New Reply"}
                           section_refs={props.refer}
                           action={replyToQuestionOrAnswer
                           }/>
            </div>
        )

    }

    //renders each question, and loads the questions comments, and the answers
    function Section(props) {

        const [loadedAnswers, setLoadedAnswers] = useState([])
        const [loadedReplies, setLoadedReplies] = useState([])
        const [loadedQuestion, setLoadedQuestion] = useState("loading question")

        const [replyRes, setReplyRse] = useState()

        //load section from reference

        useEffect(() => {

            Axios.get(props.refer).then(section => {
                    console.log("getting sections")
                    console.log(section.data)

                    //what the input section attaches itself to when posting
                    setReplyRse(section.data.question_ref)

                    //load Question from reference
                    Axios.get(section.data.question_ref).then(question => {
                            setLoadedQuestion(question.data.content)
                            //create react objects from comment references
                            setLoadedReplies(
                                question.data.reply_refs.map(reply_ref => {
                                    return (
                                        <div>
                                            <Reply key={"ia" + getNewId()} refer={reply_ref}/>
                                            {/*<Reply key={"ia" + getNewId()} refer={reply_ref}/>*/}
                                        </div>
                                    )
                                })
                            )
                        }
                    )

                    //create react object from answer references

                    setLoadedAnswers(
                        section.data.answer_refs.map(answer_ref => {
                                return (
                                    <Answer key={"ia" + getNewId()} refer={answer_ref}/>
                                )
                            }
                        ))
                }
            )

        }, [])

        return (
            <div>
                <h2>{loadedQuestion}</h2>

{/*fix reply upvotes/downvotes*/}
                <ReplyOptions src={replyRes}/>


                {loadedReplies}

                {/*not really section_refs, but reply_ref*/}
                <InputArea key={"ia" + getNewId()} placeholder={"Enter A New Reply"}

                    // section_refs={props.refer}
                           section_refs={replyRes}
                           action={replyToQuestionOrAnswer}/>
                {loadedAnswers}
                <hr/>
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

                            //console.log(refer)

                            return (
                                <div key={"ia" + getNewId()}>
                                    <Section key={"s"+getNewId()} refer={refer}/>
                                    <InputArea key={"ia" + getNewId()} placeholder={"Answer Question"}

                                        // this is now being passed
                                               section_refs={refer}
                                               action={AnswerQuestion}
                                        //action={(event,section_refs)=>{AnswerQuestion(event,section_refs)}}
                                    />
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
        <div>
            <h1>The Docs</h1>
            <InputArea key={"ia" + getNewId()} placeholder={"Enter A New Question"} action={(event) => {
                askNewQuestion(event)
            }}/>
            {ElementsInSection}
        </div>
    )
}

export default SiteDataSection