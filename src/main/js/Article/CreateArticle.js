import React, { useEffect, useState } from "react";

function CreateArticle(props) {
  const [info, setInfo] = useState({ header: "", body: "" });

  //adds info state to parent state array on first render
  useEffect(() => {
    props.update((prevState) => prevState.concat(info));
  }, []);

  //links input forms to react states
  //changes "info" which will trigger another effect
  function handleChange(event) {
    const { value, name } = event.target;
    setInfo((prevState) => {
      return {
        ...prevState,
        [name]: value,
      };
    });
  }

  //uses function from parent to update parent array of sections
  //when info is updated
  useEffect(() => {
    props.update((x) => {
      let dup = x;
      dup[props.index] = info;
      return dup;
    });
  }, [info]);

  return (
    <div>
      <label>Heading</label>
      <div className="input-group mb-3">
        <div className="input-group-prepend">
          <select className="input-group-text">
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
        <input
          type="text"
          name={"header"}
          value={info.header}
          onChange={handleChange}
          rows={5}
          placeholder={"Enter a Heading"}
          className="form-control"
          aria-label="Enter question"
        />
      </div>

      <div className="input-group">
        <div className="input-group-prepend">
          <span className="input-group-text">Content</span>
        </div>
        <textarea
          name={"body"}
          value={info.body}
          onChange={handleChange}
          rows={10}
          className="form-control"
          aria-label="With textarea"
        />
      </div>
    </div>
  );
}

export default CreateArticle;
