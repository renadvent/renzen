import React, {useState} from "react"

//const express = require ("express");


const https = require("https");
const bodyParser = require("body-parser");

// const app = express();
// app.use(bodyParser.urlencoded({ extended: false }));
// app.use(bodyParser.json());


const axios = require('axios')


function MakeNote() {

    const [noteInput, setNoteInput] = useState();



    function LoadNotes() {
        const requestOptions = {
            method: 'GET',
            //headers: { 'Content-Type': 'application/json' },
            //body: JSON.stringify({firstName:"fetchFirst",lastName:"fetchLast",description:"IT HAS BEEN POSTED"} )
            //body: JSON.stringify({ firstName:"User",lastName:"name",description: content })
        };

        //var allNotes =

        fetch("/mongo", requestOptions)
            .then(response => {
                    return response.json()

                }
            )
            .then(data => {
                console.log(data)
                console.log(data[1].description)

                var arr = []

                console.log("asfsadfsadfsafsdfasfsfsdfsadfsa")
                console.log("Array?" + Array.isArray(arr))


                data.map((x => {
                    console.log(x.description)
                    arr.push(x.description)
                    //arr.push( "<li>"+x.description+"</li>")
                }))

                console.log("Array?" + Array.isArray(arr))
                console.log(arr)
                return arr

            }).finally( p=>{
                console.log(p)
                return (<ol>{p.map(g => (<li>{g}</li>))}</ol>)
            }

        )

            // }).then(y => {
            //     console.log("Passed Array?"+Array.isArray(y))
            //
            // })

        // console.log(result[0])

        //return result
        //return <div>HI</div>
        //return <div>{result.map(x=>{return <li>{x.description}</li>})}</div>

        //
        // }).then((x) => {
        //     console.log(x)
        //         return (
        //             <div>
        //                 {x.map((d, idx) => {
        //                     return (<li key={idx}>{d.description}</li>)
        //                 })}
        //             </div>
        //         )
        //     }
        // )
        // alert(data)
        // //console.log(data)
        // console.log(allNotes)

    }

    function NoteObject(){
        return (
            <div>
                {LoadNotes()}
            </div>
        )
    }

    function addNote() {
        var content = document.getElementById("noteContent").value;
        var put = document.createElement("p");
        put.classList.add("card");
        put.classList.add("x");
        put.textContent = content;
        //document.getElementById("all-notes").appendChild(put);
        document.getElementById("noteContent").value = "";

        //---------------------------------

        var inputData = {firstName: "User", lastName: "User", description: content}

        const requestOptions = {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            //body: JSON.stringify({firstName:"fetchFirst",lastName:"fetchLast",description:"IT HAS BEEN POSTED"} )
            body: JSON.stringify({firstName: "User", lastName: "name", description: content})
        };

        //posting note
        fetch("/mongo", requestOptions)
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

    //loadNotes()

    return (
        <div>
            <NoteObject/>
            {/*<LoadNotes/>*/}

            <textarea
                className="form-control"
                id="noteContent"
                type="text"
                rows="20"
                cols="100"
                placeholder="Type Note Here (use # to  quickly enter and exit styling while typing)"
                onFocus=""
                value={noteInput}
                // onInput={proccessTextInput}
                onKeyPress={processKeyPress}
            ></textarea>
            <p>(Max Note Length 140 words or 1 paragraph)</p>

            <input placeholder="Add Tags Here" style={{width: "100%"}}/>

            <button type="button" className="btn-block" onClick={addNote}>
                Make Note
            </button>


        </div>
    )

}

export default MakeNote