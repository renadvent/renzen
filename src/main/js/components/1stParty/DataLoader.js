import React, {useEffect, useState} from "react"
import {JSONLoader} from "./JSONLoader";


function DataLoader(props) {

    const [loadedNotes, setLoadedNotes] = useState()

    useEffect(() => {

        //load from mongoDB database
        JSONLoader(props.data)

            //get description from loaded data
            .then(data => {
                var arr = []
                data.map(x => {
                    arr.push(x.definition)
                })

                //convert array into React objects
                //and set the state to this
                setLoadedNotes(
                    <ol>
                        <li>Placeholder</li>
                        {arr.map(g => (<li>{g}</li>))}
                        {console.log("LOADED")}
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

export default DataLoader