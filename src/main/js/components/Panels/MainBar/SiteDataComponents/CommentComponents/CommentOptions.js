import React, {useState,useEffect,useRef} from "react"
import Axios from "axios";

function CommentOptions(props) {

    let counter = 200202

    const [upVotes, setUpVotes] = useState(props.upVotes)
    const [downVotes, setDownVotes] = useState(props.downVotes)

    const upRef=useRef(upVotes)
    const downRef=useRef(downVotes)

    useEffect(()=>{
        console.log("use effect")
        console.log(props)

        console.log(props.id)
        //console.log(props.id.toString())

         Axios.patch("/api/x_Notes/"+props.id, {
            downVotes: downVotes,
            upVotes: upVotes,
            pageName: "default"
        })

    },[upVotes,downVotes])


    function getNewId() {
        counter = counter + 1;
        return counter - 1
    }

    function handleChange(event){
        props.click();

    }

    return (
        <div>
            <button id={getNewId()} onClick={handleChange}>Reply</button>

            <button id={getNewId()}
                onClick={()=>setUpVotes(x=> x+1)}
            >UpVote</button>

            {upVotes-downVotes}

            <button id={getNewId()}
                onClick={()=>setDownVotes(x=>x+1)}
            >DownVote</button>
            {/*<button id = {getNewId()} onClick={console.log("PRRRRRRRRRR")}>Reply</button>*/}
        </div>
    )
}

export default CommentOptions