import React, {useEffect, useState} from 'react'
import Axios from "axios";


function CreateArticleArea(props) {


    let counter = 1000000

    function getNewId() {
        counter = counter + 1;
        return counter - 1
    }


    const [createState, setCreateState] = useState(false)

    //used for saving
    const [sectionData, setSectionData] = useState([])

    //used for rendering
    const [sectionsCreated, setSectionsCreated] = useState([<SectionArea index={0} update={setSectionData}/>])

    //gets flipped when button clicked. doesn't matter value
    const [post, setPost] = useState(false)

    //used to save get article information
    const [articleData,setArticleData] = useState({
        articleName:"",
        articleDescription:"",
        articleTags:"",
        articleAddToSection:""
    })

    function handleChange(event){
        const {value,name} = event.target
        setArticleData(prevState => {
            return{
                ...prevState,
                [name]:value
            }
        })
    }

        // useEffect(() => {
    //     console.log("in parent")
    //     console.log(sectionData)
    // }, [sectionData])

    //when post is clicked
    //to save
    useEffect(() => {
        if (post) {
            console.log("in parent")
            console.log(sectionData)


            //create content in database for each section

            let contentArray = []

             sectionData.map(section=>{
                 Axios.post("/api/contents",{
                     content:section.body,
                     header:section.header
                 }).then(x=>{
                     console.log("link")
                     console.log(x.data._links.self.href)
                     return x.data._links.self.href
                 })
             })



            // //post article
            // Axios.post("/api/articles",{
            //
            // })


            setPost(false)
        }
    }, [sectionData, post, articleData])

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
                {/*<ArticleCreateMode/>*/}


                {/*input-group mb-3*/}


                {/*<CreateArticleArea title={"Add Section"}/>*/}

                <button type="button" className="btn btn-secondary"

                        onClick={() => {
                            setSectionsCreated(x => x.concat(<SectionArea index={sectionData.length}
                                                                          update={setSectionData}/>))
                        }}
                >
                    Add Section
                </button>


                <button type="button" className="btn btn-secondary"
                >
                    Add Image
                </button>


                {/*reset*/}
                <button type="button"
                        className="btn btn-secondary"
                        onClick={() => {
                            setCreateState(false);
                            setSectionsCreated(<SectionArea index={sectionData.length} update={setSectionData}/>)
                        }}
                >Cancel Article


                </button>


                {/*<button type="button" onClick={}*/}
                {/*        className="btn btn-secondary">Post Article*/}
                {/*</button>*/}

                <button type="button" onClick={() => setPost(x => {
                    return !x
                })}
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

    const [info, setInfo] = useState({header: "", body: ""})

    //adds info state to parent state array on first render
    useEffect(() => {
        props.update(prevState => prevState.concat(info))
    }, [])

    //links input forms to react states
    //changes "info" which will trigger another effect
    function handleChange(event) {
        const {value, name} = event.target
        setInfo(prevState => {
            return {
                ...prevState,
                [name]: value
            }
        })
    }

    //uses function from parent to update parent array of sections
    //when info is updated
    useEffect(() => {
        props.update(x => {
            let dup = x
            dup[props.index] = info;
            return dup
        })
    }, [info])

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