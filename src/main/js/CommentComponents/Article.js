import React, {useState} from 'react'

function CreateArticleArea(props) {

    const [createState, setCreateState] = useState(false)

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
                        <span className="input-group-text" id="basic-addon3">Article Name: </span>
                    </div>
                    <input type="text" className="form-control" id="basic-url" aria-describedby="basic-addon3"/>
                </div>


                <ArticleCreateMode/>


                <div className="input-group">
                    <div className="input-group-prepend">
                        <span className="input-group-text">Content</span>
                    </div>
                    <textarea rows={10} className="form-control" aria-label="With textarea"></textarea>
                </div>


                <CreateArticleArea title={"Add Section"}/>

                Post Article
                <button type="button"
                        className="btn btn-secondary">

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

function ArticleCreateMode() {

    return (
        <div>
            <label>Heading</label>
            <div className="input-group mb-3">
                <div className="input-group-prepend">
                    <select className="custom-select"
                        // style="width:150px;"
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
                       rows={5}
                       placeholder={"Enter a Heading"}
                       className="form-control" aria-label="Enter question"></input>
            </div>

            <div>


            </div>
        </div>

    )

}

export default CreateArticleArea