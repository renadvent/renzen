import React from 'react'

function CreateArticle(props){

}

// <button type="button" className="btn btn-secondary">+</div>

function Community (props){


    return(
<div>

        <h2>Articles in this community:</h2>
    <button type="button" className="btn btn-secondary">+Write New Article</button>
    <ul>
        <li>
            Community Info
            <button type="button" className="btn-sm btn-secondary">+</button>
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
        <li>Q&A</li>
        <li>Reference</li>

    </ul>

    <h2>Community Updates</h2>
    <ul>
        <li>New Articles</li>
        <li>New Members</li>
        <li>Unanswered Questions</li>
        <li>Accepted Answers</li>
    </ul>


</div>


    )

}

export default Community