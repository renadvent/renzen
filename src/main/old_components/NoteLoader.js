import React, {useEffect, useState} from "react"
import {JSONLoader} from "./JSONLoader";


function NoteLoader(props) {

    const [loadedNotes, setLoadedNotes] = useState()

    useEffect(() => {

        //load from mongoDB database
        JSONLoader(props.data)

            //get description from loaded data
            .then(data => {
                var arr = []
                data.map(x => {
                    arr.push(x.content)
                })

                //convert array into React objects
                //and set the state to this
                setLoadedNotes(
                    <ol>
                        <li>Placeholder</li>
                        {arr.map(g => (<li>{g}</li>))}
                    </ol>
                )

            })
    }, [])

    return (
        <div key={props.key}>
            {loadedNotes}
        </div>
    )
}

export default NoteLoader