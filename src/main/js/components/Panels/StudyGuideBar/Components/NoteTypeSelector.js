import React,{useState} from "react"

function NoteTypeSelector(props){

    const [nType, setNType] = useState(props.name)

    function onSelect(e) {

        let x = document.getElementById("selectedType")
        x.innerText=nType
        //console.log(x.innerText)

    }

    return(

        <a id={props.name} className="dropdown-item" href="#" onClick={onSelect}>
            {props.name}
        </a>

    )
}

export default NoteTypeSelector