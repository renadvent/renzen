import React, { useEffect, useState } from "react";

import io from "socket.io-client";

// const socket = io("https://comment-web-app-test.azurewebsites.net/");
const socket = io("localhost:3000");

function MessageComponent() {
  // const socket = useState(
  //   io("https://comment-web-app-test.azurewebsites.net/")
  // );

  const [messages, setMessages] = useState([]);

  useEffect(() => {
    //let socket = io("https://comment-web-app-test.azurewebsites.net/"); //socket to host server

    socket.on("chat message", function (msg) {
      setMessages((m) => m.concat(msg));
      setMessage("");
      //$("#messages").append($("<li>").text(msg));
    });
  }, []);

  let [message, setMessage] = useState("");

  function handleMessage(e) {
    setMessage(e.target.value);
  }

  function onSubmitComment(e) {
    e.preventDefault();
    socket.emit("chat message", message);
  }

  return (
    <div>
      <div>Test Comment Section</div>
      {/*<ul id="messages"></ul>*/}

      {messages.map((e) => {
        return <li>{e}</li>;
      })}

      <form action="">
        <input
          id="m"
          autoComplete="off"
          value={message}
          onChange={handleMessage}
        />
        <button onClick={onSubmitComment}>Send</button>
      </form>
    </div>
  );
}

export default MessageComponent;
