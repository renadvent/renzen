import React, {useState,useEffect} from "react"
import Axios from "axios"
import CreateArticleArea from "./Article";

import {connect} from 'react-redux'

function Home(props) {

    const [userName,setUserName] = useState()
    const [password,setPassword] = useState()

    const [createCom,setCreateCom] = useState(false)


    function handleRegister(props){
        props.preventDefault();

        Axios.post("/api/users",{
            userName: userName,
            password: password,
            communities: ["default"],
            contentList :[]
        })

        setPassword("")
        setUserName("")

    }

    function handleNewCommunity(props){

        Axios.post("/api/communities",{

        })

    }

    function handleCreateComClick(){
        setCreateCom(prevState => !prevState)
    }

    return(
        <div>
            <p>Redux Value:{props.userName}</p>
            <button onClick={props.onChangeName}>Fake Login by Redux</button>

            <button onClick={handleCreateComClick}
                //onClick={handleNewCommunity}
                    type="submit" className="btn btn-dark">Create New Community +</button>

            <div className={createCom ? 'd-block' : 'd-none'}>

                <CreateCommunitySection/>

            </div>

            <LoginRegisterSection/>

            <ListSection/>



        </div>
    )



    function CreateCommunitySection(){

        const [comData,setcomData] = useState({
            name:"",
            description:""
            //articles:[],
            //comDiscussionSections:"",
            //users:"default",
        })

        function handleChange(event) {
            const {value, name} = event.target
            setcomData(prevState => {
                return {
                    ...prevState,
                    [name]: value
                }
            })
        }

        function handleSubmit(event){

            Axios.post("/api/communities",{
                name:comData.name,
                description:comData.description

                //create other fields in server

            })


        }


        return(
            <div>

                <div className="input-group mb-3">
                    <div className="input-group-prepend">
                        <span className="input-group-text" id="basic-addon3">Community Name</span>
                    </div>
                    <input
                        name={"name"}
                        value={comData.name}
                        onChange={handleChange}
                        type="text" className="form-control" id="basic-url" aria-describedby="basic-addon3"/>
                </div>


                <div className="input-group mb-3">
                    <div className="input-group-prepend">
                        <span className="input-group-text" id="basic-addon3">Description</span>
                    </div>
                    <input
                        name={"description"}
                             value={comData.description}
                             onChange={handleChange}


                        type="text" className="form-control" id="basic-url" aria-describedby="basic-addon3"/>
                </div>

                <button onClick={handleSubmit}
                    type="submit" className="btn btn-dark">Add Community</button>

                <hr/>

            </div>
        )
    }

    function ListSection(){

        //load communities
        const [allCommunities,setAllCommunities] = useState([])
        useEffect(()=>{
            Axios.get("/api/communities").then(comObjects=>{
                console.log(comObjects)
                setAllCommunities( ()=> {
                    return comObjects.data._embedded.communities.map(comObject=>{
                        console.log(comObject)
                        return(<div><a href={comObject._links.self.href} >+{comObject.name}</a></div>)
                        // return(<a href={comObject.data._links.self.href}>{comObject.data.name}</a>)
                    })
                    }
                )
            })
        },[])

        //const setTabs=props.setTan

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
                    <CreateArticleArea page={url} title={"Add Article"}/>
                    </div>)
            })

            // event.preventDefault()
            // event.stopImmediatePropagation()

            //<CreateArticleArea page={"url"} title={"Add Article"}/>

        }

        //load articles
        const [allArticles,setAllArticles] = useState([])
        useEffect(()=>{
            Axios.get("/api/articles").then(comObjects=>{
                console.log(comObjects)
                setAllArticles( ()=> {
                        return comObjects.data._embedded.articles.map(comObject=>{
                            console.log(comObject)
                            return(<div>
                                <a
                                    onClick={(event) => handleSelectArticle(event,comObject._links.self.href)}
                                >

                                    {/*href={comObject._links.self.href} >*/}
                                +{comObject.articleName}</a>
                            </div>)
                            // return(<a href={comObject.data._links.self.href}>{comObject.data.name}</a>)
                        })
                    }
                )
            })
        },[])

        //load users
        const [allUsers,setAllUsers] = useState([])
        useEffect(()=>{
            Axios.get("/api/users").then(comObjects=>{
                console.log(comObjects)
                setAllUsers( ()=> {
                        return comObjects.data._embedded.users.map(comObject=>{
                            //console.log(comObject)
                            return(<div><a href={comObject._links.self.href} >{comObject.userName}</a></div>)
                            // return(<a href={comObject.data._links.self.href}>{comObject.data.name}</a>)
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

    function LoginRegisterSection(){
        return(

            <div>



        <form action="/login" method="POST">
            <div className="form-group">
                <label htmlFor="email">Email</label>
                <input type="email" className="form-control" name="username"/>
            </div>
            <div className="form-group">
                <label htmlFor="password">Password</label>
                <input type="password" className="form-control" name="password"/>
            </div>
            <button type="submit" className="btn btn-dark">Login</button>
        </form>

        <hr/>

        <form onSubmit={event => handleRegister(event)}>
            <div className="form-group">
                <label htmlFor="email">Email</label>
                <input value={userName} onChange={(event => setUserName(event.target.value))}
                       type="username" className="form-control" name="username"/>
            </div>
            <div className="form-group">
                <label htmlFor="password">Password</label>
                <input value={password} onChange={(event => setPassword(event.target.value))}
                       type="password" className="form-control" name="password"/>
            </div>
            <button  type="submit" className="btn btn-dark">Register</button>
        </form>

            </div>
        )
    }



}

//export default Home

const mapStateToProps = state => {
    return{
        userName: state.userName
    }
}

const mapDispatchToProps = dispatch =>{
    return {
        onChangeName: ()=> dispatch({type:'CHANGE_NAME'})
    }
}

export default connect(mapStateToProps,mapDispatchToProps)(Home)