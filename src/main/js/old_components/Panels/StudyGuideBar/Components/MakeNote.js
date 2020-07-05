import React, {useEffect, useState} from "react"

function MakeNote() {

    const [noteInput, setNoteInput] = useState();

    function addNote() {

        var content = document.getElementById("noteContent").value;

        const requestOptions2 = {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            //body: JSON.stringify({firstName:"fetchFirst",lastName:"fetchLast",description:"IT HAS BEEN POSTED"} )
            body: JSON.stringify({definition : content})
        };

        //POSTING NOTE
        let t = document.getElementById("selectedType")
        fetch("/"+t.innerText+"s", requestOptions2)
        //fetch("/Definitions", requestOptions2)

        document.getElementById("noteContent").value = "";

    }

    function proccessTextInput(e) {

        // const {name, value} = e.target


        // if (e.target.value === "a"){

        //   setNoteInput(prevValue => {
        //     return {
        //       ...prevValue,
        //       [name] : value+"A"

        //     };

        //   })
        // } else{
        //   setNoteInput(e.target.value)
        // }
    }

//adds info to key press
    function processKeyPress(e) {

        //const {value, key} = e

        const key = e.key
        const value = e.target.value

        //add button to button colum
        if (key === "Enter") {

            var b = document.getElementById("clone")
            var cln = b.cloneNode(true);


            document.getElementById("buttonCol").appendChild(cln)

            setNoteInput(value + "\n")
            console.log(key)
            return
        }

        if ((key === "a") && (value !== 'undefined')) {

            setNoteInput(value + "+A")
            //setNoteInput(value + "+<bold>A</bold>")

        } else if (key === "a") {
            setNoteInput("+A")
        } else {
            setNoteInput(value + key)
        }

        console.log(key)


    }

    return (
        <div>
            <textarea
                className="form-control"
                id="noteContent"
                type="text"
                rows="20"
                cols="100"
                placeholder="Type Note Here (use # to  quickly enter and exit styling while typing)"
                // onFocus=""
                value={noteInput}
                //onInput={proccessTextInput}
                //onKeyPress={processKeyPress}
            />
            <p>(Max Note Length 140 words or 1 paragraph)</p>

            <input placeholder="Add Tags Here" style={{width: "100%"}}/>

            <button type="button" className="btn-block" onClick={addNote}>
                Make Note
            </button>


        </div>
    )

}

export default MakeNote