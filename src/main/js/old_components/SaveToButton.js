import React, {useEffect, useState} from "react"
import Axios from "axios";

function SaveToButton(props){

    const [savePages,setSavePages] = useState()

    useEffect(()=>{
        return Axios.get("/api/pages").then(all_pages => {

            console.log(all_pages)

            return all_pages.data._embedded.pages.map(page=>{
                return (
                    <a className="dropdown-item" href="#">
                        {console.log("page: "+page._links.self.href)}
                        {page._links.self.href}
                    </a>)
            })

        }).then((study_guides)=>{
            setSavePages(study_guides)
        })

    },[])



    return (

        <div className="dropdown">
            <button
                className="x btn btn-secondary dropdown-toggle"
                type="button"
                id="dropdownMenuButton"
                data-toggle="dropdown"
            >
                Save Question and Answer to Study Guide
            </button>
            <div className="dropdown-menu">
                <a className="dropdown-item" href="#">
                    Your Study Guide
                </a>
                <a className="dropdown-item" href="#">
                    Team Study Guide
                </a>
                <a className="dropdown-item" href="#">
                    Your Notes
                </a>
                {savePages}
            </div>
        </div>
    )
}

export default SaveToButton
