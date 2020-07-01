import React from "react"
import Axios from "axios";

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

            Axios.post("/api/x_Notes/",{
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

export default InputArea