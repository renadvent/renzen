import React, {useState} from "react"
import JSONLoader from "./JSONLoader";

function PageLoader(props) {
    //5ef519e9f0e6dd2efdf70f1e

    //var page = JSONLoader("/Pages/")
    //var page = JSONLoader(props.pageName); //example dummy code

    const requestOptions2 = {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        //body: JSON.stringify({firstName:"fetchFirst",lastName:"fetchLast",description:"IT HAS BEEN POSTED"} )
        body: "5ef519e9f0e6dd2efdf70f1e"
    };

    //POSTING NOTE
    //let t = document.getElementById("selectedType")
    let page = fetch("/Pages", requestOptions2).then(page => {
        return page.json()
    }).then(page => {


            //do below for each section of page
            //return object with render of each section

            //load definitions and render
            const [defRender, setDefRender] = useState()
            setDefRender(
                <ol>
                    {page.definitions.map(def => {
                        return (
                            <li>
                                <p>{def.definition}</p>
                                <p>{def.user}</p>
                            </li>
                        )
                    })}
                </ol>
            )

            //load QA and render
            const [QARender, setQARender] = useState()
            setQARender(
                <div>

                    {props.QAs.map(QA => {
                        return (
                            <div>
                                <h2>QA.qTitle</h2>
                                <p>QA.qContent</p>
                                {QA.As.map(A => {
                                    return (
                                        <div>
                                            <h4>A.title</h4>
                                            <p>A.content</p>
                                        </div>
                                    )
                                })}
                            </div>
                        )
                    })
                    })
                </div>
            )
        }
    )

    let returnObject = {definitions: defRender, QA: QARender}

    return (
        returnObject
    )
}

export default PageLoader