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
  function NextLogic(single) {
    console.log(single.otherPostsInWork);

    //TODO
    //replace this component with next work
  }

  return (
    <div>
      <ul className="list-group list-group-flush">
        {props.loadedArticles.map((single) => {
          //console.log(single);
          return (
            <Box
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
