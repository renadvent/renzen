import React from "react";
import YourStudyGuide from "./Panels/YourStudyGuide";
import SiteData from "./Panels/SiteData";
import AnnotationBar from "./Panels/AnnotationBar";
//import data from "./fromDatabase"

import Navbar from "./nav";

//----------------------------------------------------------

//const express = require ("express");
const https = require("https");
const bodyParser = require("body-parser");

//const app = express();
//app.use(bodyParser.urlencoded({extended: true}));

//----------------------------------------------------------


//const client = require('./client'); // <3>


function App() {


    return (
        <div className="container-fluid">

            {/*<p><EmpApp/></p>*/}

            <Navbar/>

            <div className="row">
                <YourStudyGuide/>

                <SiteData />

                <AnnotationBar/>
            </div>
        </div>
    );
}

export default App;
