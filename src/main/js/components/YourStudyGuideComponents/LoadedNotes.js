import React, {useEffect,useState} from "react"




function NoteObject(props) {

    const [loadedNotes, setLoadedNotes] = useState()


//function LoadNotes() {
    useEffect(()=>{
        const requestOptions = {
            method: 'GET',
        };

        fetch("/mongo", requestOptions)
            .then(response => {
                return response.json()
            })
            .then(data => {

                console.log(data)
                console.log(data[1].description)

                var arr = []

                data.map(x => {
                    console.log(x.description)
                    arr.push(x.description)
                })

                setLoadedNotes(
                    <ol>
                        <li>Placeholder</li>
                        {arr.map(g => (<li>{g}</li>))}
                        {console.log("LOADED LOADED")}
                    </ol>
                )



            })


    },[])
//}



    return (
        <div key={props.key} >
            <p>hi</p>
            {loadedNotes}
        </div>
    )
}

export default NoteObject