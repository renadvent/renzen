import React from "react"
import Axios from "axios";

function CommentTextArea(props){

    let counter=1

    function getNewId(){
        counter=counter+1;
        return counter-1
    }


    //transforms text area to comment
    //and add it to database
    function processKeyPress(e) {

        if (e.key === "Enter"){
            //process add content
            console.log("process add content")

            //const content = e.target.innerText

            const content = e.target.value

            let former = document.createElement("div")
            former.innerText=content

            let username = document.createElement("div")
            username.innerText="defaultUser"

            document.getElementById(e.target.id).insertAdjacentElement("beforebegin",former)
            document.getElementById(e.target.id).insertAdjacentElement("beforebegin",username)

            // const reqOptions = {
            //     method : 'POST',
            //     headers: {'Content-Type': 'application/json'},
            //     body: JSON.stringify(
            //         {
            //             user:"default",
            //             content : content,
            //             noteType : props.noteType,
            //             pageSource : "default"
            //         })
            // }
            //
            // fetch("/Notes",reqOptions)

            Axios.post("/api/x_Notes",{
                user:"default",
                content:content,
                noteType:props.noteType,
                pageSource:"default"
            })

            console.log(e.target.id)
            document.getElementById(e.target.id).remove()

        }

    }

    ///"6rem"

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

export default CommentTextArea