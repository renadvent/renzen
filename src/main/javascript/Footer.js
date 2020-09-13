import React from "react";

function Footer(props) {
  return (
    <div>
      <br />
      <hr />
      <br />
      <nav className="navbar navbar-expand-lg navbar-light bg-light">
        <a
          className="navbar-brand"
          target={"_blank"}
          href="https://github.com/renadvent/renzen/issues"
        >
          Click here to register an Issue with the site
        </a>
      </nav>
    </div>
  );
}

export default Footer;
