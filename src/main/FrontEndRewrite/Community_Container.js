import React, {useState,useEffect} from "react"
import Axios from "axios";
import Community from "../js/Community_Page/Community/Community";
import CommunityContent from "./CommunityContent";
import CommunitySidebar from "./CommunitySidebar";
import ArticleArea from "./CreateArticleArea";
import CreateArticleArea from "./CreateArticleArea";

function Community_Container(props){

    const [data,setData]=useState()

    // useEffect(()=>{
    //     loadCommunity()
    // })
    //
    // async function loadCommunity() {
    //     let temp = await Axios.get(props.source)
    //     setData(temp.data)
    // }

    return(
        <div id={"community-container"} className="tab-pane fade" role="tabpanel">
            <h1 style={{ textAlign: "center" }}>Community Homepage</h1>
            <hr></hr>
            <div className="row">
                <div id={"left-side-bar"} className={"col-5"}>
                    {/*<CommunitySidebar data={data}/>*/}
                </div>
                <div id={"main-content"} className={"col-7"}>
                    <CommunityContent data={data}/>
                </div>
            </div>



        </div>
    )
}

export default Community_Container