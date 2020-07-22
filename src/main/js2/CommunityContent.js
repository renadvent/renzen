import React, {useEffect, useState} from "react"
import Axios from "axios";

/*

 */

function CommunityContent(props) {

    /*
    Holds the mid column community content
     */

    useEffect(() => {

    })

    //url
    const [openArticle, setOpenArticle] = useState("")

    return (
        <div>
            <CommunityTopicList data={props.data} setOpenArticle={setOpenArticle}/>
            <InlineForm_CreateArticle/>
            <Article data={openArticle}/>
        </div>
    )
}

//inital renderes from props.data
//uses topics, topics.topic.name, topics.topic.articles
function CommunityTopicList(props) {

    /*
    renders community topics and articles as a list
     */

    const [show_InlineForm_CreateArticle, setShow_InlineForm_CreateArticle] = useState(false)
    let data = props.data

    return (
        <div>
            <h3>Topics in this community</h3>
            <p>Number of Topics in Community: {props.data.topics.length}</p>

            <button onClick={()=>setShow_InlineForm_CreateArticle(prevState => !prevState)}>
                {show_InlineForm_CreateArticle ? "Cancel" : "Add Topic to Community"}
            </button>

            <div className={show_InlineForm_CreateArticle ? "d-block" : "d-none"}>
                <InlineForm_CreateArticle/>
            </div>

            {data.topics.map(topic => {
                    return (
                        <div>
                            <p>{topic.name} #{topic.articles.length}</p>
                            {topic.articles.map(article => {
                                return (
                                    <a onClick={props.setOpenArticle(article.link)} href={article.link}>{article.name}</a>
                                )
                            })}
                        </div>
                    )
                }
            )}
        </div>
    )
}

function InlineForm_CreateArticle(props){

    return(
        <div></div>
    )

}

//renders from props.source
//needs to load articleName, articleDescription, sections to render itself
function Article(props) {

    const [data,setData]= useState({})

    useEffect(()=>{
        loadArticle(props.source)
    })

    async function loadArticle(props){
        let articleRes = await Axios.get(props.source)
        setData(articleRes.data)
    }

    return(
        <div>
            <p>{data.articleName}</p>
            <p>{data.articleDescription}</p>
            <ArticleButtons/>
            {data.sections.map(section => {
                return(
                    <Section source={section}/>
                )
            })}
        </div>
    )
}

//renders from props.source
//needs to load HEADING, CONTENT, DISCUSSION_URL to render itself
function Section(props) {

    const [data,setData] = useState({})
    const [showInlineDiscussion,setShowInlineDiscussion]=useState(false)

    useEffect(()=>{
        loadSection(props.source)
    })

    async function loadSection(){
        let sectionRes = await Axios.get(props.source)
        setData(sectionRes.data)
    }

    return (
        <div>
            <p>{data.heading}</p>
            <p>{data.content}</p>

            <button onClick={()=>setShowInlineDiscussion(prevState => !prevState)}>
                {showInlineDiscussion ? "Hide Discussion" : "Show Discussion"}
            </button>

            <div className={showInlineDiscussion ? "d-block" : "d-none"}>
                <Inline_Discussion/>
            </div>
        </div>
    )
}


//will use rewritten Discussion
function Inline_Discussion() {

    return(
        <div></div>
    )

}

export default CommunityContent