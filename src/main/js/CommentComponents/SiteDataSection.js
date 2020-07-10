import React, {useEffect, useState} from "react"
import Axios from "axios";
import SaveToButton from "../old_components/SaveToButton";


//Site wide Docs--Community Docs--Personal Docs


function Template(props) {

    //create content in templates to promote uniformity and consistency

    //response/article templates
    //community/book templates

    const templateTypes = {}

    /*



    Who, What, Where, When, Why, How

    How to....

    What is...

    When do I...

    What about when...

    Why do/is...

    How is...

    ____: How and Why

    */


}


function SiteDataSection(props) {

    //props.content
    //props.sections
    //props.pages

    let [page_ref, setPageRef] = useState(props.page)

    const [ElementsInSection, setElementsInSection] = useState([])

    let counter = 10000

    function getNewId() {
        counter = counter + 1;
        return counter - 1
    }

    useEffect(() => {
        loadSections(page_ref)
    }, [page_ref])

    function reload() {
        window.location.reload(false);
        loadSections(page_ref)
    }

    function InputArea(props) {

        let counter = 1

        function getNewId() {
            counter = counter + 1;
            return counter - 1
        }

        let areaStyle = {
            //width: "70%",
            marginLeft: props.margin
        }

        return (
            <div>
            <textarea id={getNewId()} rows={1} placeholder={props.placeholder}
                      style={areaStyle} className={"form-control"}
                      onKeyPress={(event => props.action(event, props.section_refs))
                      }
                // cols={50} style={{height:auto}}
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

                        //add to beginning
                        page.data.section_refs.unshift(postedSection.data._links.self.href)
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

        useEffect(() => {

            console.log(props.src)

            Axios.patch(props.src, {
                upVotes: props.upVotes
            }).catch(() => {
                    //console.log("nothing to patch")
                }
            )

        }, [props.upVotes, props.src])

        useEffect(() => {

            Axios.patch(props.src, {
                downVotes: props.downVotes
            }).catch(() => {
                    //console.log("nothing to patch")
                }
            )

        }, [props.downVotes, props.src])

        return (
            <div>
                <button type={"button"} className={"btn btn-outline-secondary"} id={getNewId()}
                        onClick={() => props.testUp(x => x + 1)}
                >UpVote
                </button>

                {props.upVotes - props.downVotes}

                <button id={getNewId()} type={"button"} className={"btn btn-outline-secondary"}
                        onClick={() => props.testDown(x => x + 1)}
                > DownVote
                </button>
                {/*<button id={getNewId()}>Share with another community</button>*/}


                <div id={getNewId()} className="dropdown d-inline-block">
                    <button
                        className="x btn btn-secondary dropdown-toggle"
                        type="button"
                        id="dropdownMenuButton"
                        data-toggle="dropdown"
                    >
                        Share with another community
                    </button>
                    <div className="dropdown-menu">
                        <a className="dropdown-item" href="#">
                            A Community you're in
                        </a>
                        <a className="dropdown-item" href="#">
                            Another Community you're in
                        </a>
                    </div>
                </div>





            </div>
        )
    }

    //----------------------------------------------------------------------------

    function Reply(props) {

        const [loadedReply, setLoadedReply] = useState("loading reply")

        const [upVotes, setUpVotes] = useState()
        const [downVotes, setDownVotes] = useState()
        const [replyRes, setReplyRes] = useState(props.refer)

        let styleAs = {
            marginLeft: "6rem",
            //width: "100%",
        }

        useEffect(() => {
            Axios.get(props.refer).then(reply => {
                console.log("getting replies")

                setDownVotes(reply.data.downVotes)
                setUpVotes(reply.data.upVotes)
                setReplyRes(props.refer)
            })
        }, [])

        useEffect(() => {

            Axios.get(props.refer).then(reply => {

                console.log("getting replies")

                setLoadedReply(
                    <div>

                        <div className={"card"} style={styleAs}>
                            <div className={"card-body"}>
                                {reply.data.content}

                            </div>

                            <ReplyOptions
                                src={replyRes}
                                upVotes={upVotes}
                                downVotes={downVotes}
                                testUp={(x) => setUpVotes(x)}
                                testDown={(x) => setDownVotes(x)}
                            />
                        </div>
                    </div>
                )
            })
        }, [upVotes, downVotes])

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

        const [upVotes, setUpVotes] = useState()
        const [downVotes, setDownVotes] = useState()
        const [replyRes, setReplyRes] = useState(props.refer)


        ///annotated wrong, copy and paste
        useEffect(() => {
            Axios.get(props.refer).then(reply => {
                setDownVotes(reply.data.downVotes)
                setUpVotes(reply.data.upVotes)
                setReplyRes(props.refer)
            })
        }, [])

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
                                    <Reply key={"rep" + getNewId()} refer={reply_ref}/>
                                </div>
                            )
                        })
                    )
                }
            )
        }, [])

        return (
            <div>

                <div className={"card"}>
                    <div className={"card-body"}>
                        <h4>
                            {loadedAnswer}<SaveToButton/>
                        </h4>
                        <ReplyOptions
                            src={replyRes}
                            upVotes={upVotes}
                            downVotes={downVotes}
                            testUp={(x) => setUpVotes(x)}
                            testDown={(x) => setDownVotes(x)}
                        />
                    </div>
                </div>

                {loadedReplies}
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

        const [upVotes, setUpVotes] = useState()
        const [downVotes, setDownVotes] = useState()

        //
        // const [navLinks, setNavLinks] = setState()

        //load section from reference

        useEffect(() => {

            Axios.get(props.refer).then(section => {
                    console.log("getting sections")

                    //what the input section attaches itself to when posting
                    setReplyRse(section.data.question_ref)

                    //load Question from reference
                    Axios.get(section.data.question_ref).then(question => {
                            setLoadedQuestion(question.data.content)

                            setDownVotes(question.data.downVotes)
                            setUpVotes(question.data.upVotes)

                            //create react objects from comment references
                            setLoadedReplies(
                                question.data.reply_refs.map(reply_ref => {
                                    return (
                                        <div>
                                            <Reply key={"ia" + getNewId()}
                                                   refer={reply_ref}/>
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
                                    <div className={"card"}>
                                        <div className={"card-body"}>
                                            <Answer key={"ia" + getNewId()} refer={answer_ref}/>
                                        </div>
                                    </div>
                                )
                            }
                        ))
                }
            )

        }, [])

        return (
            <div>

                {/*<div className={"card"}>*/}
                {/*    <div className={"card-body"}>*/}

                <div className={"card"}>
                    <div className={"card-body"}>
                        <h2>
                            {loadedQuestion}
                            <SaveToButton/>
                        </h2>

                        <ReplyOptions
                            src={replyRes}
                            upVotes={upVotes}
                            downVotes={downVotes}
                            testUp={(x) => setUpVotes(x)}
                            testDown={(x) => setDownVotes(x)}
                        />


                        {loadedReplies}

                        {/*not really section_refs, but reply_ref*/}
                        <InputArea key={"ia" + getNewId()} placeholder={"Enter A New Reply"}
                                   section_refs={replyRes}
                                   action={replyToQuestionOrAnswer}/>
                        {loadedAnswers}
                        {/*<hr/>*/}

                        {/*    </div>*/}
                        {/*</div>*/}


                    </div>
                </div>

            </div>
        )
    }

    //this loads each section for the page
    function loadSections(page) {

        // const [navLinks, setNavLinks] =useState()

        // setElementsInSection

        //get sections for page
        Axios.get(page).then(pageObject => {
                {


                    //----------------------------------------

                    //create sections for nav
                    //
                    // let ref_arr=pageObject.data.section_refs
                    //
                    // //first
                    // navLinks.push(ref_arr[0])
                    //
                    //
                    // //last
                    // navLinks.push(ref_arr[ref_arr.length-1])


                    //----------------------------------------


                    console.log("getting section refs")

                    //pageObject.data.
                    setElementsInSection(
                        pageObject.data.section_refs.map(refer => {

                            return (
                                <div key={"ia" + getNewId()}>


                                    <div className={"card"}>
                                        <div className={"card-body"}>

                                            <Section key={"s" + getNewId()} refer={refer}/>
                                            <InputArea key={"ia" + getNewId()} placeholder={"Answer Question"}
                                                       section_refs={refer}
                                                       action={AnswerQuestion}
                                            />

                                        </div>
                                    </div>
                                </div>
                            )
                        })
                    )
                }
            }
        )
    }

    //----------------------------------------------------------------------------

    function CommentNav(props) {

        return (
            <nav aria-label="Page navigation example">
                <ul className="pagination">
                    <li className="page-item"><a className="page-link" href="#">Previous</a></li>
                    <li className="page-item"><a className="page-link" href="#">1</a></li>
                    <li className="page-item"><a className="page-link" href="#">2</a></li>
                    <li className="page-item"><a className="page-link" href="#">3</a></li>
                    <li className="page-item"><a className="page-link" href="#">Next</a></li>
                </ul>
            </nav>
        )
    }

    //----------------------------------------------------------------------------

    return (
        <div>
            {/*<p>Articles in your community</p>*/}
            {/*<p>(initial articles: Getting Started, Main Concepts, Reference, FAQ</p>*/}
            {/*<p>Create New Article</p>*/}

            {/*<h1>The Docs</h1>*/}
            <h1>{props.title}</h1>


            <div>
                Question Type
                <div className="btn-group" role="group" aria-label="Basic example">
                    <button type="button" className="btn btn-secondary">How Do I...</button>
                    <button type="button" className="btn btn-secondary">What is...</button>
                    <button type="button" className="btn btn-secondary">When Do I...</button>
                </div>
            </div>

            {/*            <div>*/}
            {/*                Answer Type*/}
            {/*                <div className="btn-group" role="group" aria-label="Basic example">*/}
            {/*                    <button type="button" className="btn btn-secondary">What About When...</button>*/}
            {/*                    <button type="button" className="btn btn-secondary">Does That Mean...</button>*/}
            {/*                    <button type="button" className="btn btn-secondary">So Then How Does...</button>*/}
            {/*                </div>*/}
            {/*            </div>*/}

            <div>
                Reply Type
                <div className="btn-group" role="group" aria-label="Basic example">
                    <button type="button" className="btn btn-secondary">I agree with...</button>
                    <button type="button" className="btn btn-secondary">I disagree with...</button>
                    <button type="button" className="btn btn-secondary">Thank you...</button>
                    <button type="button" className="btn btn-secondary">I have a suggestion...</button>
                </div>
            </div>

            <div>
                Follow Up Question
                <div className="btn-group" role="group" aria-label="Basic example">
                    <button type="button" className="btn btn-secondary">What About When...</button>
                    <button type="button" className="btn btn-secondary">Does That Mean...</button>
                    <button type="button" className="btn btn-secondary">So Then How Does...</button>
                </div>
            </div>
            <div>
                Note Type
                <div className="btn-group" role="group" aria-label="Basic example">
                    <button type="button" className="btn btn-secondary">Definition...</button>
                    <button type="button" className="btn btn-secondary">Background...</button>
                    <button type="button" className="btn btn-secondary">Explanation...</button>
                </div>
            </div>

            <div>Definition--Comparable Traits, Comparable Terms/Topics</div>


            <div className="container-fluid">
                <div className="row">
                    <div className="col-4">
                        <select className="custom-select"
                            // style="width:150px;"
                        >
                            <option>How Do I...</option>
                            <option>What is...</option>
                            <option>When Do I...</option>
                        </select>
                    </div>
                    <div className="col-8">
                        <InputArea key={"ia" + getNewId()} placeholder={"Enter A New Question or Note"}
                                   action={(event) => {
                                       askNewQuestion(event)
                                   }}/>
                    </div>
                </div>
            </div>

            <div className="input-group mb-3">
                <div className="input-group-prepend">
                    <select className="custom-select"
                        // style="width:150px;"
                    >
                        <option>How Do I...</option>
                        <option>What is...</option>
                        <option>When Do I...</option>
                    </select>
                </div>
                <input type="text"
                       rows={5}
                       placeholder={"Enter a question"}
                       className="form-control" aria-label="Enter question"/>
            </div>
            <textarea/>


            {ElementsInSection}
            <CommentNav/>
        </div>
    )
}

export default SiteDataSection