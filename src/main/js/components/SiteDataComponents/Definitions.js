import React from "react";
import Comment from "./Comment";
import UseCases from "./UseCases";
import QA from "./QA";
import Collapse from "./collapse";

function createComment(props) {
  return (
    <div>
      <Comment author={props.author} comment={props.comment} key={props.id} />
    </div>
  );
}

function createUse(props) {
  return (
    <div>
      <UseCases situation={props.situation} example={props.example} />

      <span>
        <button class="x">Save to Study Guide</button>
      </span>
    </div>
  );
}

function createQA(props) {
  return (
    <div>
      <QA Q={props.Q} A={props.A} />
    </div>
  );
}

function createWalkthrough(props) {
  return (
    <div>
      <h3>
        <a href="">{props.name} </a>
        <span class="badge badge-secondary">{props.type}</span>
      </h3>
      <p>
        <span class="badge badge-secondary">{props.description}</span>
      </p>

      {props.tags.map((x) => {
        return (
          <span>
            <span class="badge badge-secondary">{x}</span>
            <span
              style={{ borderRadius: "5px", backgroundColor: "#00cec9" }}
            ></span>
          </span>
        );
      })}

      <Collapse
        name={props.name}
        description={props.description}
        key={props.id}
        id={props.id}
      />
    </div>
  );
}

function Def_Card(props) {
  return (
    <div className="card">
      {/* <h2>{props.name}</h2> */}
      <h1>Definitions + Overviews + Documentation </h1>

      <ul class="nav nav-tabs">
            <li class="nav-item" role="presentation">
              <a
                class="nav-link active"
                id="home-tab"
                data-toggle="tab"
                href="#featured"
                role="tab"
                aria-controls="profile"
                aria-selected="true"
              >
                Featured
              </a>
            </li>
            <li class="nav-item">
              <a
                class="nav-link"
                id="profile-tab"
                data-toggle="tab"
                href="#popular"
                role="tab"
                aria-controls="profile"
                aria-selected="false"
              >
                Top Voted
              </a>
            </li>
            <li class="nav-item">
              <a
                class="nav-link"
                id="sandbox-tab"
                data-toggle="tab"
                href="#newest"
                role="tab"
                aria-controls="profile"
                aria-selected="false"
              >
                Newest
              </a>
            </li>
            <li class="nav-item">
              <a
                class="nav-link"
                id="boot-tab"
                data-toggle="tab"
                href="#yourdefs"
                role="tab"
                aria-controls="profile"
                aria-selected="false"
              >
                Your Definitions
              </a>
            </li>
          </ul>



      <button>Add Definition</button>
      <p>
        {props.author}'s Definition{" "}
        <span>
          <div class="dropdown">
            <button
              class="x btn btn-secondary dropdown-toggle"
              type="button"
              id="dropdownMenuButton"
              data-toggle="dropdown"
              aria-haspopup="true"
              aria-expanded="false"
            >
              Save to Study Guide
            </button>
            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
              <a class="dropdown-item" href="#">
                Your Study Guide
              </a>
              <a class="dropdown-item" href="#">
                Team Study Guide
              </a>
              <a class="dropdown-item" href="#">
                Your Notes
              </a>
            </div>
          </div>
        </span>
        <span>Upvotes+</span>
        <span class="badge badge-primary badge-pill">14</span>
      </p>
      <p className="info">{props.content}</p>
      <div className="comments">
        {props.comments.map(createComment)}
        <button>Add Comment</button>
      </div>
      <nav aria-label="Page navigation example">
  <ul class="pagination">
    <li class="page-item"><a class="page-link" href="#">Previous</a></li>
    <li class="page-item"><a class="page-link" href="#">1</a></li>
    <li class="page-item"><a class="page-link" href="#">2</a></li>
    <li class="page-item"><a class="page-link" href="#">3</a></li>
    <li class="page-item"><a class="page-link" href="#">Next</a></li>
  </ul>
</nav>
      <button>View More Definitions ^ </button>
      <div>
        <hr></hr>
          <h1>The Docs</h1>
          <ul className="list-group">
              <li className="list-group-item disabled" aria-disabled="true">Null</li>
              <li className="list-group-item">Hypothesis</li>
              <li className="list-group-item">Morbi leo risus</li>
              <li className="list-group-item">Porta ac consectetur ac</li>
              <li className="list-group-item">Vestibulum at eros</li>
          </ul>
          <hr></hr>
        <h1>When to Use</h1>
        <button>Add Case </button>

        {props.uses.map(createUse)}
      </div>
      <hr></hr>
      <h1>Walkthroughs + Articles</h1>
      <button>Add Walkthrough</button>
      {props.Walkthrough.map(createWalkthrough)}
      <button>View More Walkthroughs ^ </button>
      <hr></hr>
      <h1>General Q + A</h1>
      <button>Add Question</button>
      {props.QA.map(createQA)}
      <button>View More Q + A ^ </button>
    </div>
  );
}

export default Def_Card;