import React, { useState } from "react";
import { connect } from "react-redux";
import { HomePage_StateToProps as mapStateToProps } from "../maps/StateToProps";
import { HomePage_mapDispatchToProps as mapDispatchToProps } from "../maps/DispatchToProps";
import CommentSection from "./CommentSection";

import { v4 as uuidv4 } from "uuid";
import likeIcon from "../icons/like.png";
import Box from "./Box";

// import { ReactComponent as likeIcon } from "../icons/like.svg";

// const likeIcon = "../icons/like.svg";

function HomePageStream(props) {
  return (
    <div>
      <ul className="list-group list-group-flush">
        {props.loadedArticles.map((single,i) => {
          //console.log(single);
          return (
            <Box
            key={i}
              single={single}
              uuid={single.UUID}
              dispatchOpen={props.dispatchOpen}
            />
          );
        })}
      </ul>
    </div>
  );
}

export default connect(mapStateToProps, mapDispatchToProps)(HomePageStream);
