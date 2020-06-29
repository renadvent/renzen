import React, {useState,useEffect} from "react"









function CommentOptions(props) {


    let counter = 200202

    useEffect(()=>{
        console.log("use effect")
        console.log(props)
    })


    function getNewId() {
        counter = counter + 1;
        return counter - 1
    }

    function handleChange(event){
        console.log("handling click")
        console.log(props.click)
        props.click();
    }


    return (
        <div>
            <button id={getNewId()} onClick={handleChange}>Reply
            </button>
            {/*<button id = {getNewId()} onClick={console.log("PRRRRRRRRRR")}>Reply</button>*/}
        </div>
    )
}

export default CommentOptions