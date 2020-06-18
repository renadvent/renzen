import React, {useState} from "react";



function Page(props){

    var [backgroundColor, setBackGroundColor] = useState({
        backgroundColor: "blue"
      });

    return(<button type="button"         style={backgroundColor}
    onMouseOver={() => setBackGroundColor({ backgroundColor: "black" })}
    onMouseOut={() => setBackGroundColor({ backgroundColor: "blue" })}>{props.name}</button>)
}

export default Page;