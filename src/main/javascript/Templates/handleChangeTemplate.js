import React, { useState } from "react";

/**
 * handle change on input fields
 * @param props
 * @returns {JSX.Element}
 * @constructor
 */
function React_Component(props) {
  const [info, setInfo] = useState({ header: "", body: "" });

  function handleChange(event) {
    const { value, name } = event.target;
    setInfo((prevState) => {
      return {
        ...prevState,
        [name]: value,
      };
    });
  }

  return (
    <input
      type="text"
      name={"header"}
      value={info.header}
      onChange={handleChange}
      rows={5}
      className="form-control"
      aria-label="Enter question"
    />
  );
}
