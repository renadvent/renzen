import React from 'react'

function CreateArticle(props){

}

// <button type="button" className="btn btn-secondary">+</div>

function Community (props){


    return(
<div>

        <p>Articles in this community:</p>
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


</div>


    )

}

export default Community