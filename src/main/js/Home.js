import React, {useState,useEffect} from "react"
import Axios from "axios"

function Home() {

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
                        return(<div><a href={comObject._links.self.href} >{comObject._links.self.href}</a></div>)
                        // return(<a href={comObject.data._links.self.href}>{comObject.data.name}</a>)
                    })
                    }
                )
            })
        },[])

        //load articles
        const [allArticles,setAllArticles] = useState([])
        useEffect(()=>{
            Axios.get("/api/articles").then(comObjects=>{
                console.log(comObjects)
                setAllArticles( ()=> {
                        return comObjects.data._embedded.articles.map(comObject=>{
                            console.log(comObject)
                            return(<div><a href={comObject._links.self.href} >{comObject._links.self.href}</a></div>)
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
                            console.log(comObject)
                            return(<div><a href={comObject._links.self.href} >{comObject._links.self.href}</a></div>)
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

export default Home