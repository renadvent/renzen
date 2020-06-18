import React from "react"
import Page from "./Page"


function createPage(props){
    return(<Page name={props}/>)
}


function PageSelector(props){

    return(
        <div>
            {props.names.map(createPage)}
        </div>
    )


}


export default PageSelector;