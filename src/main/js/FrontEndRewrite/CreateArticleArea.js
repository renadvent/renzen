import React, {useEffect, useState} from 'react'
import ArticleSection from "./ArticleSection";
import * as store from "./Store_Actions";
import {connect} from "react-redux";

const mapStateToProps = (state) => {
    return {
        // open_communities: state.tabs.open_communities,
        // open_profiles:state.tabs.open_profiles,

        open:state.tabs.open,

        user_communities:state.user.communities,

        user:state.user
    }
}

const mapDispatchToProps = (dispatch) => {
    return {
        DISPATCH_createArticle:(payload,user,community,sectionData)=>
            dispatch(store.DISPATCH_createArticle(payload,user,community,sectionData))
    }
}


function CreateArticleArea(props) {

    const [thisCommunity,setThisCommunity] = useState(props.community)

    const [createState, setCreateState] = useState(false)

    //used for saving
    const [sectionData, setSectionData] = useState([])

    //used for rendering
    const [sectionsCreated, setSectionsCreated] = useState([<ArticleSection index={0} update={setSectionData}/>])

    //used to save get article information
    const [articleData, setArticleData] = useState({
        articleName: "",
        articleDescription: "",
        articleTags: "",
        articleAddToSection: ""
    })

    function handleChange(event) {
        const {value, name} = event.target
        setArticleData(prevState => {
            return {
                ...prevState,
                [name]: value
            }
        })
    }

    return (
        <div>

            {/*<div className={createState ? 'd-block' : 'd-none'}>*/}
                <div>

                    <label htmlFor="basic-url">{props.title}</label>


                    <div className="input-group mb-3">
                        <div className="input-group-prepend">
                            <label className="input-group-text" htmlFor="inputGroupSelect01">Add To Section</label>
                        </div>
                        <select className="custom-select" id="inputGroupSelect01">
                            <option selected>Choose...</option>
                            <option value="1">One</option>
                            <option value="2">Two</option>
                            <option value="3">Three</option>
                        </select>
                    </div>

                    <div className="input-group mb-3">
                        <div className="input-group-prepend">
                            <span className="input-group-text" id="basic-addon3">Tags</span>
                        </div>
                        <input
                            name={"articleTags"}
                            value={articleData.articleTags}
                            onChange={handleChange}

                            type="text" className="form-control" id="basic-url" aria-describedby="basic-addon3"/>
                    </div>

                    <div className="input-group mb-3">
                        <div className="input-group-prepend">
                            <span className="input-group-text" id="basic-addon3">Description</span>
                        </div>
                        <input
                            name={"articleDescription"}
                            value={articleData.articleDescription}
                            onChange={handleChange}

                            type="text" className="form-control" id="basic-url" aria-describedby="basic-addon3"/>
                    </div>

                    <div className="input-group mb-3">
                        <div className="input-group-prepend">
                            <span className="input-group-text" id="basic-addon3">Article Name: </span>
                        </div>
                        <input
                            name={"articleName"}
                            value={articleData.articleName}
                            onChange={handleChange}
                            type="text" className="form-control" id="basic-url" aria-describedby="basic-addon3"/>
                    </div>

                    {sectionsCreated}

                    <button type="button" className="btn btn-secondary"

                            onClick={() => {
                                setSectionsCreated(x => x.concat(<ArticleSection index={sectionData.length}
                                                                                 update={setSectionData}/>))
                            }}
                    >
                        Add Section
                    </button>


                    <button type="button" className="btn btn-secondary"
                    >
                        Add Image
                    </button>

                    <button type="button"
                            className="btn btn-secondary"
                            onClick={() => {
                                setCreateState(false);
                                setSectionsCreated(<ArticleSection index={sectionData.length} update={setSectionData}/>)
                            }}
                    >Cancel Article


                    </button>

                    <button type="button" onClick={() =>
                        props.DISPATCH_createArticle(articleData,props.user.id,thisCommunity,sectionData)}
                            className="btn btn-secondary">Post Article
                    </button>


                </div>
            {/*</div>*/}
        </div>
    )

}
export default connect(mapStateToProps, mapDispatchToProps)(CreateArticleArea)
