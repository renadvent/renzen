import React, {useState} from "react"
import DiscussionArea from "../Discussion/DiscussionArea";

function ArticleReplySection(props) {
    const [showRep, setShowRep] = useState(false);
    return (
        <div>
            <button
                onClick={(e) => {
                    setShowRep((prevState) => !prevState);
                }}
                type="submit"
                className="btn btn-dark"
            >
                Show Discussion
            </button>
            <div className={showRep ? "d-block" : "d-none"}>
                {/* <DiscussionArea title={"Section Discussion"}
                                    page={"/api/pages/5efd2911d231b04eecfcd282"}/> : null} */}
                <DiscussionArea
                    title={"Section Discussion"}
                    page={"/api/pages/5efd2911d231b04eecfcd282"}
                />
            </div>
        </div>
    );
}

export default ArticleReplySection