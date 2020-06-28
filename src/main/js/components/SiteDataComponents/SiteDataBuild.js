import React, {useEffect, useState} from "react";
import {createComment, createQA, createUse, createWalkthrough} from "../1stParty/functions";
import DefinitionTab from "./DefinitionTab";
import SiteDataSection from "./SiteDataSection";

function SiteDataBuild(props) {

    const [loadedDefs, setLoadedDefs] = useState()

    useEffect(() => {

        //Load Definitions
        fetch("/Definitions", {method: 'GET'})
            .then(response => {
                return response.json()
            })

            .then(data => {

                setLoadedDefs(
                    <div>
                        <ol>
                            {data.map(obj => {
                            return (
                                <li>{obj.definition}</li>
                            )
                            })
                        }
                        </ol>
                    </div>
                )


            })
    }, [])


    return (

        <div>

            <SiteDataSection loadedDefs={loadedDefs}/>

            {/*    <h1>When to Use</h1>*/}
            {/*    <button>Add Case</button>*/}

            {/*    {props.uses.map(createUse)}*/}

            {/*<hr></hr>*/}
            {/*<h1>Walkthroughs + Articles</h1>*/}
            {/*<button>Add Walkthrough</button>*/}
            {/*{props.Walkthrough.map(createWalkthrough)}*/}
            {/*<button>View More Walkthroughs ^</button>*/}
            {/*<hr></hr>*/}
            {/*<h1>General Q + A</h1>*/}
            {/*<button>Add Question</button>*/}
            {/*{props.QA.map(createQA)}*/}
            {/*<button>View More Q + A ^</button>*/}
        </div>
    );
}

export default SiteDataBuild;
