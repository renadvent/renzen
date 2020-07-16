import React, {useEffect,useState} from "react"
import Axios from "axios";
import ArticleArea from "../Community_Page/ArticleArea";
import {useSelector} from "react-redux";
import {connect} from "react-redux"
import * as actionTypes from "../Store/actions"
import {open_community} from "../Store/actions";

const mapStateToProps = state => {
    return{

    }
}

const mapDispatchToProps = dispatch => {
    return {
        onOpenCommunity:(comURL)=>dispatch(open_community((comURL)))
        // onChangeName: () => dispatch({type: 'CHANGE_NAME'}),
        // onFakeLogin: () => dispatch({
        //     type: 'FAKE_LOGIN',
        //     userURL: "http://localhost:8001/api/users/5f0aba93ba913107ab69627c"
        // })
    }
}

function ListSection(props){

    //took away set tab content... is a problem.
    //have to change it to dispatch


        //load communities
        const [allCommunities,setAllCommunities] = useState([])
        useEffect(()=>{
            Axios.get("/api/communities").then(comObjects=>{
                setAllCommunities( ()=> {
                        return comObjects.data._embedded.communities.map(comObject=>{
                            return(<div
                                onClick={()=>props.onOpenCommunity(comObject._links.self.href)}>
                                <a
                                    // href={comObject._links.self.href}
                                >+{comObject.name}</a>
                            </div>)
                        })
                    }
                )
            })
        },[])

        let counter =1

        function getNewId() {
            counter = counter + 1;
            return counter - 1
        }

        function handleSelectArticle(event,url){

            //create a new tab
            event.preventDefault()
            let id="C"+getNewId()
            props.setTabs(prevState=>{
                return prevState.concat(
                    <li className="nav-item">
                        <a className="nav-link" id={"T"+getNewId()} data-toggle="tab" href={"#"+id} role="tab"
                           aria-controls="profile" aria-selected="false">url</a>
                    </li>
                )
            })

            props.setTabContent(prevState=>{
                return prevState.concat(
                    <div className="tab-pane fade" id={id} role="tabpanel"
                         aria-labelledby="home-tab">
                        <ArticleArea page={url} title={"Add Article"}/>
                    </div>)
            })
        }

        //load articles
        const [allArticles,setAllArticles] = useState([])
        useEffect(()=>{
            Axios.get("/api/articles").then(comObjects=>{
                setAllArticles( ()=> {
                        return comObjects.data._embedded.articles.map(comObject=>{
                            return(<div>
                                <a
                                    onClick={(event) => handleSelectArticle(event,comObject._links.self.href)}
                                >
                                    +{comObject.articleName}</a>
                            </div>)
                        })
                    }
                )
            })
        },[])

        //load users
        const [allUsers,setAllUsers] = useState([])
        useEffect(()=>{
            Axios.get("/api/users").then(comObjects=>{
                setAllUsers( ()=> {
                        return comObjects.data._embedded.users.map(comObject=>{
                            return(<div><a
                                href={comObject._links.self.href}
                            >{comObject.userName}</a></div>)
                        })
                    }
                )
            })
        },[])

        return(
            <div className="container-fluid">
                <div className={"row"}>
                    <div className={"col"}>
                        <h4>All Users</h4>
                        {allUsers}
                    </div>
                    <div className={"col"}>
                        <h4>All Articles</h4>
                        {allArticles}
                    </div>
                    <div className={"col"}>
                        <h4>All Communities</h4>
                        {allCommunities}
                    </div>
                </div>
            </div>
        )
}

export default connect(mapStateToProps, mapDispatchToProps)(ListSection)