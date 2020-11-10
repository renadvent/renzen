import React, { useEffect, useState } from "react";
import { connect } from "react-redux";
import Home_Container from "./Home_Page";

import { TabPane_StateToProps as mapStateToProps } from "./maps/StateToProps";
import { TabPane_mapDispatchToProps as mapDispatchToProps } from "./maps/DispatchToProps";

// import io from "socket.io-client";
// import MessageComponent from "./components/MessageComponent";
//
// let socket = io("https://comment-web-app-test.azurewebsites.net/"); //socket to host server

// socket.on("chat message", function (msg) {
//   $("#messages").append($("<li>").text(msg));
// });

function Tab_Pane(props) {
  //initial load of site

  //loads initially, and updates article names etc when a new tab is opened
  useEffect(() => {
    props.DISPATCH_init();
  }, []);

  //updates active tab when new ones are opened
  useEffect(() => {
    if (props.open.length > 0) {
      $("#tabA" + props.open[props.open.length - 1].data._id).tab("show");
    } else {
      $("#home-tab").tab("show");
    }
  }, [props.open]);

  useEffect(() => {
    if (OpenFromInkSource !== null) {
      props.DISPATCH_openCreateArticleTab("5f92319abce4e159c51a0a11");
    }
  }, []);

  // let [message, setMessage] = useState("");
  //
  // function handleMessage(e) {
  //   setMessage(e.target.value);
  // }
  //
  // function onSubmitComment(e) {
  //   e.preventDefault();
  //   socket.emit("chat message", message);
  // }

  return (
    <div id={"tabsAndContents"}>
      {/*<div>Test Comment Section</div>*/}
      {/*<ul id="messages"></ul>*/}
      {/*<form action="">*/}
      {/*  <input*/}
      {/*    id="m"*/}
      {/*    autoComplete="off"*/}
      {/*    value={message}*/}
      {/*    onChange={handleMessage}*/}
      {/*  />*/}
      {/*  <button onClick={onSubmitComment}>Send</button>*/}
      {/*</form>*/}

      {/*<MessageComponent />*/}

      {/*<embed*/}
      {/*  type="text/html"*/}
      {/*  src="https://comment-web-app-test.azurewebsites.net/"*/}
      {/*  width="500"*/}
      {/*  height="200"*/}
      {/*/>*/}

      <div id={"tab-list"}>
        <ul className="nav nav-tabs" id="app-tabs" role="tablist">
          <li className="nav-item">
            <a
              className="nav-link active"
              id="home-tab"
              data-toggle="tab"
              href="#home-contents"
              role="tab"
            >
              Home
            </a>
          </li>
          {console.log(props)}
          {props.open.map((open) => {
            return open.tab;
          })}
        </ul>
      </div>

      <div id={"tabContents"} className={"tab-content"}>
        <div
          className="tab-pane fade show active"
          id="home-contents"
          role="tabpanel"
          aria-labelledby="home-tab"
        >
          <Home_Container />
        </div>

        {props.open.map((open) => {
          return open.component;
        })}
      </div>
    </div>
  );
}

export default connect(mapStateToProps, mapDispatchToProps)(Tab_Pane);
