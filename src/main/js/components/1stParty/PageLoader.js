import React from "react"
import JSONLoader from "./JSONLoader";

function PageLoader(props) {

    var page = JSONLoader("/page/894248203423"); //example dummy code


    //do below for each section of page
    //return object with render of each section

    //load definitions and render them as a list
    //map to array, then map
    const [defRender, setDefRender] = useState()
    setDefRender(
        <ol>
            {page.defs.map(def => {
                return(
                    <li>
                        <p>{def.defintion}</p>
                        <p>{def.user}</p>
                    </li>
                )
            })}
        </ol>
    )
    let returnObject= {definitions : defRender}

    return (
        returnObject
    )

    // setDefRender(
    //     <ol>
    //         {page.defs.map(arr => arr.map(def => {
    //             return (<li>{def}</li>)
    //         }))}
    //     </ol>
    // )

}