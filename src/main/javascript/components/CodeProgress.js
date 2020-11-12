import React from "react";

export function CodeProgress() {
  return (
    <div className={"card"}>
      <h6 className={"card-header"}>Progress</h6>

      <div className={"card-body"}>
        Web-Server
        <div className="progress">
          <div
            className="progress-bar"
            style={{ width: "85%" }}
            role="progressbar"
            aria-valuenow="85"
            aria-valuemin="0"
            aria-valuemax="100"
          >
            85%
          </div>
        </div>
      </div>

      <div className={"card-body"}>
        Web-Client
        <div className="progress">
          <div
            className="progress-bar"
            role="progressbar"
            style={{ width: "85%" }}
            aria-valuenow="85"
            aria-valuemin="0"
            aria-valuemax="100"
          >
            85%
          </div>
        </div>
      </div>

      <div className={"card-body"}>
        Desktop App
        <div className="progress">
          <div
            className="progress-bar"
            role="progressbar"
            style={{ width: "65%" }}
            aria-valuenow="65"
            aria-valuemin="0"
            aria-valuemax="100"
          >
            65%
          </div>
        </div>
      </div>
    </div>
  );
}
