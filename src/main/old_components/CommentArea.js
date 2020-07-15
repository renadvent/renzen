import React from "react"

function CommentArea(){

    function processKeyPress(e) {

        console.log("process add content")

        if (e.target.value == "enter"){
            //process add content
            console.log("process add content")
        }
    }
    return (
        <div>
            <textarea placeholder={"Type Comment Here"} rows={1} onKeyPress={processKeyPress} />
        </div>
    )
}

export default CommentArea