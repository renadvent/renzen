import React, {useState} from "react";
import { connect } from "react-redux";
import * as store from "./Store_Actions";

//can be opened as a tab as well as in home page
function Profile_Container(props) {



    const [communityName, setCommunityName] = useState("");

  return (
    <div>
        <br/>
      <p>username: {props.data.name}</p>
        <hr/>

      <p>number of communities: {props.data.numberOfCommunities}</p>
      <p>Communities you are a part of: </p>
      <Stream source={props.data.communityStreamComponentCOList} />
      <hr/>
        <p>number of articles: {props.data.numberOfArticles}</p>
      <p>Articles you've written</p>
      <Stream source={props.data.articleHomePageCOList}/>

      <hr/>
      {/*<p>Articles you've written</p>*/}
      {/*<Stream source={props.data.articleStreamComponentCOList} />*/}
      {/*<p>{props.data.url}</p>*/}

      {/*//TODO create community*/}


      <div>
          <input
              value={communityName}
              onChange={(event) => setCommunityName(event.target.value)}
              type="communityName"
              className="form-control"
              name="communityName"
          />
      </div>

      <button className="btn btn-dark" onClick={()=>props.DISPATCH_createCommunity(props.data._id,communityName)}>
        Create Community+
      </button>
      <button className="btn btn-dark" onClick={props.DISPATCH_logOut}>Log Out</button>
    </div>
  );
}
//<Stream source={props.loadedCommunities} dispatch={props.DISPATCH_openCommunity}/>
const mapStateToProps = (state) => {
  return {
    user: state.user,
  };
};

const mapDispatchToProps = (dispatch) => {
  return {
    DISPATCH_logOut: () => dispatch(store.DISPATCH_logOut()),
    DISPATCH_createCommunity: (creatorID,name) => dispatch(store.DISPATCH_createCommunity({creatorID:creatorID,name:name})),
  };
};

function Stream(props) {
  return (
    <ul>
      {props.source !== null
        ? props.source.map((single) => {
            return (
              // <div onClick={()=>props.dispatch(single._links["Tab_Version"].href)}>
              <li>
                <a>+{single.name}</a>
              </li>
            );
          })
        : null}
    </ul>
  );
}

export default connect(mapStateToProps, mapDispatchToProps)(Profile_Container);
