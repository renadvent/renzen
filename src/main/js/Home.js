import React, {useState,useEffect} from "react"
import Axios from "axios"

function Home() {

    const [userName,setUserName] = useState()
    const [password,setPassword] = useState()


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

    return(
        <div>


            {/*<form action="/login" method="POST">*/}
            {/*    <div className="form-group">*/}
            {/*        <label htmlFor="email">Email</label>*/}
            {/*        <input type="email" className="form-control" name="username"/>*/}
            {/*    </div>*/}
            {/*    <div className="form-group">*/}
            {/*        <label htmlFor="password">Password</label>*/}
            {/*        <input type="password" className="form-control" name="password"/>*/}
            {/*    </div>*/}
            {/*    <button type="submit" className="btn btn-dark">Login</button>*/}
            {/*</form>*/}

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


            <div className="container-fluid">
                <div className={"row"}>
                    <div className={"col"}>
                        <h4>All Users</h4>
                    </div>
                    <div className={"col"}>
                        <h4>All Articles</h4>
                    </div>
                    <div className={"col"}>
                        <h4>All Communities</h4>
                    </div>
                </div>
            </div>

        </div>




    )

}

export default Home