import React from "react";
import { connect } from "react-redux";
import * as store from "./Store_Actions";

const mapStateToProps = (state) => {
    return{
        state:state
    }
}

const mapDispatchToProps = (dispatch) => {
    return{

    }
}

function ArticleAppTabContent(){

    return(
        <div>
            Placeholder for article
        </div>
    )

}

export default connect(mapStateToProps,mapDispatchToProps)(ArticleAppTabContent)
