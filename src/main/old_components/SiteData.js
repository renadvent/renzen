import React from "react";
import WebsiteTab from "./WebsiteTab"
import MainTab from "./MainTab";


function SiteData() {
    return (
        <div id="mid" className="col-6">

            <ul className="nav nav-tabs">
                <WebsiteTab name="Study Guide" linkTo="#ALLC" active="active"/>
                {/*<WebsiteTab name="Wikipedia" linkTo="#wiki"/>*/}
                {/*<WebsiteTab name="CodeSandbox.io" linkTo="#codesandbox"/>*/}
                {/*<WebsiteTab name="Bootstrap Docs" linkTo="#boot"/>*/}
            </ul>

            <div className="tab-content" id="myTabContent">

                {/*<WebsiteTabContent id="wiki" site="https://wikipedia.org"/>*/}
                {/*<WebsiteTabContent id="codesandbox" site="https://codesandbox.io/dashboard/recent"/>*/}
                {/*<WebsiteTabContent id="boot" site="https://getbootstrap.com/docs/4.5/components/alerts/"/>*/}
                <MainTab/>


            </div>
        </div>
    )
}

export default SiteData