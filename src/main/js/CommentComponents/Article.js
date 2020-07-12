import React, {useState,useRef,useEffect} from 'react'
import Axios from "axios";



function CreateArticleArea(props) {


let counter = 1000000

    function getNewId() {
        counter = counter + 1;
        return counter - 1
    }


    const [createState, setCreateState] = useState(false)

    //used for saving
    const [sectionData,setSectionData] = useState([])

    //used for rendering
    const [sectionsCreated, setSectionsCreated] = useState([<SectionArea update={setSectionData}/>])

    //gets flipped when button clicked. doesn't matter value
    const [post,setPost]=useState(false)

    useEffect(()=>{
        console.log(sectionData)
    },[sectionData])

    useEffect(()=>{
        if (post){
            console.log(sectionData)
            setPost(false)
        }
    },[sectionData,post])

    if (createState === true) {
        return (

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
                    <input type="text" className="form-control" id="basic-url" aria-describedby="basic-addon3"/>
                </div>

                <div className="input-group mb-3">
                    <div className="input-group-prepend">
                        <span className="input-group-text" id="basic-addon3">Description</span>
                    </div>
                    <input type="text" className="form-control" id="basic-url" aria-describedby="basic-addon3"/>
                </div>

                <div className="input-group mb-3">
                    <div className="input-group-prepend">
                        <span className="input-group-text" id="basic-addon3">Article Name: </span>
                    </div>
                    <input type="text" className="form-control" id="basic-url" aria-describedby="basic-addon3"/>
                </div>


                {sectionsCreated}
                {/*<ArticleCreateMode/>*/}


                {/*input-group mb-3*/}


                {/*<CreateArticleArea title={"Add Section"}/>*/}

                <button type="button" className="btn btn-secondary"

                        onClick={() => {
                            setSectionsCreated(x => x.concat(<SectionArea update={setSectionData}/>))
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
                            setSectionsCreated(<SectionArea update={setSectionData}/>)
                        }}
                >Cancel Article


                </button>


                {/*<button type="button" onClick={}*/}
                {/*        className="btn btn-secondary">Post Article*/}
                {/*</button>*/}

                <button type="button" onClick={()=>setPost(x=> {return !x})}
                        className="btn btn-secondary">Post Article
                </button>


            </div>


        )
    }

    if (createState === false) {
        return (
            <div>

                <button type="button"
                        className="btn btn-secondary"
                        onClick={() => setCreateState(true)}>{props.title}</button>

                {/*<CreateArticleArea/>*/}

            </div>
        )

    }

}

{/*<select className="custom-select"*/
}

// style="width:150px;"y

function SectionArea(props) {

    const [info,setInfo]=useState({header:"",body:""})

    //add this state to parent array
    useEffect(()=>{
        props.update(prevState => prevState.concat(info))
    },[])

    function handleChange(event){

        const {value,name}=event.target

        console.log(value)
        console.log(name)

        setInfo(prevState => {
            return {
                ...prevState,
                [name]:value
            }
        })
    }

    return (
        <div>
            <label>Heading</label>
            <div className="input-group mb-3">
                <div className="input-group-prepend">
                    <select className="input-group-text"

                    >
                        <option></option>
                        <option>Topic:</option>
                        <option>Background on:</option>
                        <option>How Do I...</option>
                        <option>What is...</option>
                        <option>When Do I...</option>
                        <option>What About When...</option>
                        <option>Does That Mean...</option>
                        <option>So Then How Does...</option>

                    </select>
                </div>
                <input type="text"
                       name={"header"}
                       value={info.header}
                       onChange={handleChange}
                       rows={5}
                       placeholder={"Enter a Heading"}
                       className="form-control" aria-label="Enter question"/>
            </div>

            <div className="input-group">
                <div className="input-group-prepend">
                    <span className="input-group-text">Content</span>
                </div>
                <textarea
                    name={"body"}
                    value={info.body}
                    onChange={handleChange}
                    rows={10} className="form-control" aria-label="With textarea"/>
            </div>

        </div>

    )

}

export default CreateArticleArea