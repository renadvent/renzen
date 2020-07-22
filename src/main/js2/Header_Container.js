import React from "react"

function Header_Container(props){
    return (
        <nav  id = {"header-component"} className="navbar navbar-expand-lg navbar-light bg-light">
            <a className="navbar-brand" href="#">
                Renzen
            </a>
            <button
                className="navbar-toggler"
                type="button"
                data-toggle="collapse"
                data-target="#navbarSupportedContent"
            >
                <span className="navbar-toggler-icon"></span>
            </button>
        </nav>
    );
}

export default Header_Container