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

function ArticleAppTabContent(props){

    return(


        <div
            className="tab-pane fade"
            id={props.href}
            role="tabpanel"
            //aria-labelledby="profile-tab"
        >

        <div>
            Placeholder for article
        </div>

        </div>
    )

}

export default connect(mapStateToProps,mapDispatchToProps)(ArticleAppTabContent)
