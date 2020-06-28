import React from "react"

function CommentTextArea(props){

    let counter=1

    function getNewId(){
        counter=counter+1;
        return counter-1
    }


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

            const reqOptions = {
                method : 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify(
                    {
                        user:"default",
                        content : content,
                        noteType : "comment",
                        pageSource : "default"
                    })
            }

            fetch("/Notes",reqOptions)

            console.log(e.target.id)
            document.getElementById(e.target.id).remove()

            let el = createTextArea(former);

            createReply(former)
        }

    }



    let areaStyle = {
        width: "70%",
        marginLeft:"6rem"
    }

    return(
        <div>
            <textarea id={getNewId()} rows={1} placeholder={"Type comment here"}
                      autoFocus={true} style={areaStyle} className={"form-control"}
                      onKeyPress={processKeyPress}/>
        </div>

    )
}

export default CommentTextArea