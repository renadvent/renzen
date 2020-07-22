import React, {useState,useEffect} from "react"

/*

 */

function CommunityContent(props){

    const [openArticle,setOpenArticle] = useState("")

    return(
        <div>
            <CommunityArticleList setOpenArticle={setOpenArticle}/>
            <Article source={openArticle}/>
        </div>
    )
}

export default CommunityContent