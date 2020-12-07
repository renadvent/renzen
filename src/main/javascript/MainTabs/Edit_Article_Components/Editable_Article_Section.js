import React, { useEffect, useState } from "react";

export function Editable_Article_Section(props) {
  const [info, setInfo] = useState({
    header: "",
    body: "",
    // workName: "",
    // tags: "",
    // pollOptions: "",
  });

  //adds info state to parent state array on first render
  useEffect(() => {
    props.update((prevState) => prevState.concat(info));

    try{
      setInfo({
        header:props.text.header,
        body:props.text.body
      })
    }catch{

    }

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
            x[props.index] = info;
            return x;
    });
  }, [info]);

  return (
    <div>
      <div className="input-group mb-3">
        <div className="input-group-prepend">
          <span className="input-group-text">Heading</span>
        </div>
        <input
          type="text"
          name={"header"}
          value={info.header}
          onChange={handleChange}
          rows={5}
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
