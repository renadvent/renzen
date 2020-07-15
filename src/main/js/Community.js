import React from 'react'
import SiteDataSection from "./SiteDataSection";
import CreateArticleArea from "./Article";

function CreateArticle(props){

}

// <button type="button" className="btn btn-secondary">+</div>

function Community (props){


    return(
<div>

        <h2>Articles in this community:</h2>
    {/*<button type="button" className="btn btn-secondary">+Write New Article</button>*/}
    <ul>
        <li>
            Community Info
            {/*<button type="button" className="btn-sm btn-secondary">+</button>*/}
        </li>

        <ul>
            <li>Purpose</li>
            <li>Affiliated Topics</li>
            <li>Affiliated Communities</li>
            <li>Moderators</li>
        </ul>
        <li>Getting Started</li>
        <li>Main Concepts</li>
        <li>Walkthroughs</li>
        <li>Study Guides</li>
        <li>Q&A</li>
        <li>Reference</li>

    </ul>



    <div className={"card"}>
        <div className={"card-body"}>

    <CreateArticleArea page={"http://localhost:8001/api/communities/5f0c01b2d552840570235567"} title={"Show Add Article"}/>

        </div>
    </div>

    <div className={"card"}>
        <div className={"card-body"}>
            <button type="button" className="btn btn-secondary">Ask Question</button>
        </div>
    </div>


    {/*<SiteDataSection title={"Articles"} page={"/api/sections/5f09ecf5347a081bd0dec961"}/>*/}



</div>


    )

}

export default Community