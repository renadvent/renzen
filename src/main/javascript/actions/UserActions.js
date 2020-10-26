import Axios from "axios";
import setJWTToken from "../securityUtils/setJWTToken";
import jwt_decode from "jwt-decode";
import {
  ACTION_logIn,
  ACTION_logOut,
  ACTION_openUser,
  GET_ERRORS,
} from "./StoreDefs";

export function DISPATCH_logOut() {
  $("#home-tab").tab("show");
  localStorage.removeItem("jwtToken");
  setJWTToken();

  return {
    type: ACTION_logOut,
  };
}

export function DISPATCH_openUser(url) {
  //USING getstate
  console.log(url);
  return (dispatch, getState) => {
    Axios.get(url).then((res) => {
      console.log("data");
      console.log(res.data);

      let base = res.data;

      let vars = getVarsFromResponse(base);

      //check if already open
      getState().reducer.tabs.open.find((x) => {
        return x.id === res.data._id;
      })
        ? $("#tabA" + res.data._id).tab("show")
        : dispatch({
            type: ACTION_openUser,
            data: res.data,

            articles: vars.articles,
            profiles: vars.profiles,
            communities: vars.communities,
            //payload: res.data,
          });
    });
  };
}
//
// export function DISPATCH_logInR(payload) {
//   return async (dispatch, getState) => {
//     Axios.post("/login", {
//       password: payload.password,
//       username: payload.username,
//     })
//       .then((res) => {
//         //TODO get auth token etc
//         const { token } = res.data;
//         localStorage.setItem("jwtToken", token);
//         setJWTToken(token);
//         const decoded = jwt_decode(token);
//         console.log("decoded");
//         console.log(decoded);
//
//         return decoded;
//       })
//       .then((decoded) => {
//         Axios.get("/getProfileTabComponentCO/" + decoded.id).then(
//           (secondRes) => {
//             console.log("data");
//             console.log(secondRes.data);
//
//             let base = secondRes.data;
//
//             let vars = getVarsFromResponse(base);
//
//             dispatch({
//               type: ACTION_logIn,
//               payload: secondRes.data,
//               articles: vars.articles,
//               communities: vars.communities,
//               bookmarks: vars.bookmarks,
//             });
//           }
//         );
//       });
//   };
// }

export function DISPATCH_logIn(payload) {
  return async (dispatch, getState) => {
    try {
      let loginRes = await Axios.post("/login", {
        password: payload.password,
        username: payload.username,
      });

      const { token } = loginRes.data;
      localStorage.setItem("jwtToken", token);
      setJWTToken(token);
      const decoded = jwt_decode(token);

      let profileRes = await Axios.get(
        "/getProfileTabComponentCO/" + decoded.id
      );

      let data = profileRes.data;

      let vars = getVarsFromResponse(data);

      dispatch({
        type: ACTION_logIn,
        payload: data,
        articles: vars.articles,
        communities: vars.communities,
        bookmarks: vars.bookmarks,
      });
    } catch (error) {
      dispatch({
        type: GET_ERRORS,
        error: error,
        payload: error.response.data,
      });
    }
  };
}

export function DISPATCH_register(payload) {
  //TODO also needs to dispatch login after register to get token
  //NEED global Axios

  return async (dispatch) => {
    try {
      await Axios.post("/register", {
        password: payload.password,
        username: payload.username,
        confirmPassword: payload.confirmPassword,
        email: payload.email,
      });

      return dispatch(
        DISPATCH_logIn({
          username: payload.username,
          password: payload.password,
        })
      );
    } catch (error) {
      // error.response.statusText
      dispatch({
        type: GET_ERRORS,
        error: error,
        payload: error.response.data,
      });
    }

    // Axios.post("/register", {
    //   password: payload.password,
    //   username: payload.username,
    //   confirmPassword: payload.confirmPassword,
    //   email: payload.email,
    // }).then(() => {
    //   return dispatch(
    //     DISPATCH_logIn({
    //       username: payload.username,
    //       password: payload.password,
    //     })
    //   );
    // });
  };
}

export function getVarsFromResponse(base) {
  let articles = [];
  let profiles = [];
  let communities = [];

  let bookmarks = [];

  //articleBookmarksCM._embedded.articleInfoComponentCoes

  try {
    bookmarks = base.articleBookmarksCM._embedded.articleInfoComponentCoes;
    if (bookmarks === undefined) bookmarks = [];
  } catch {
    bookmarks = [];
  }

  try {
    articles = base.articleInfoComponentCOS._embedded.articleInfoComponentCoes;
    if (articles === undefined) articles = [];
  } catch {
    articles = [];
  }

  try {
    profiles = base.profileInfoComponentCOS._embedded;
    if (profiles === undefined) profiles = [];
  } catch {
    profiles = [];
  }

  try {
    communities =
      base.communityInfoComponentCOS._embedded.communityInfoComponentCoes;
    if (communities === undefined) communities = [];
  } catch {
    communities = [];
  }

  return {
    articles: articles,
    communities: communities,
    profiles: profiles,
    bookmarks: bookmarks,
  };
}
