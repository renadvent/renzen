import React from "react";
import CreateArticleArea from "./CreateArticleArea";

function ArticleEditTab(props) {
  return (
    <div
      className="tab-pane fade"
      id={props.href}
      role="tabpanel"
      aria-labelledby="article-edit-tab"
    >
      <CreateArticleArea community={props.id} />
    </div>
  );
}

export default ArticleEditTab;
