import React, {useEffect,useState} from "react"


function DiscussionInputArea(props) {

    let counter = 1

    function getNewId() {
        counter = counter + 1;
        return counter - 1
    }

    let areaStyle = {
        //width: "70%",
        marginLeft: props.margin
    }

    return (

        <textarea id={getNewId()} rows={1} placeholder={props.placeholder}
                  style={areaStyle} className={"form-control"}
                  onKeyPress={(event => props.action(event, props.section_refs))
                  }
        />

    )
}

export default DiscussionInputArea