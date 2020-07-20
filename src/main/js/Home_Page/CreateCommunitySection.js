import Axios from "axios";
import React, { useState, useEffect } from "react";

function CreateCommunitySection() {
  const [comData, setcomData] = useState({
    name: "",
    description: "",
    //articles:[],
    //comDiscussionSections:"",
    //users:"default",
  });

  function handleChange(event) {
    const { value, name } = event.target;
    setcomData((prevState) => {
      return {
        ...prevState,
        [name]: value,
      };
    });
  }

  function handleSubmit(event) {
    Axios.post("/api/communities", {
      name: comData.name,
      description: comData.description,

      //create other fields in server
    });
  }

  return (
    <div>
      <div className="input-group mb-3">
        <div className="input-group-prepend">
          <span className="input-group-text" id="basic-addon3">
            Community Name
          </span>
        </div>
        <input
          name={"name"}
          value={comData.name}
          onChange={handleChange}
          type="text"
          className="form-control"
          id="basic-url"
          aria-describedby="basic-addon3"
        />
      </div>
      <div className="input-group mb-3">
        <div className="input-group-prepend">
          <span className="input-group-text" id="basic-addon3">
            Description
          </span>
        </div>
        <input
          name={"description"}
          value={comData.description}
          onChange={handleChange}
          type="text"
          className="form-control"
          id="basic-url"
          aria-describedby="basic-addon3"
        />
      </div>
      <button onClick={handleSubmit} type="submit" className="btn btn-dark">
        Add Community
      </button>
      <hr />
    </div>
  );
}

export default CreateCommunitySection;
