import React from "react"

function SaveToButton(props){

    return (

        <div className="dropdown">
            <button
                className="x btn btn-secondary dropdown-toggle"
                type="button"
                id="dropdownMenuButton"
                data-toggle="dropdown"
                aria-haspopup="true"
                aria-expanded="false"
            >
                Save to Study Guide
            </button>
            <div className="dropdown-menu" aria-labelledby="dropdownMenuButton">
                <a className="dropdown-item" href="#">
                    Your Study Guide
                </a>
                <a className="dropdown-item" href="#">
                    Team Study Guide
                </a>
                <a className="dropdown-item" href="#">
                    Your Notes
                </a>
            </div>
        </div>
    )
}

export default SaveToButton
