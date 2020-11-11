import React from "react";
import ReactDOM from "react-dom";
import "./CSS/index.css";
import App from "./App";
import { applyMiddleware, createStore, compose } from "redux";
//import reducer from "./reducers/Store_Reducers";
import reducer from "./reducers/rootReducer";
import { Provider } from "react-redux";
import thunk from "redux-thunk";
import setJWTToken from "./securityUtils/setJWTToken";

const composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;
//const store = createStore(reducer, composeEnhancers(applyMiddleware(thunk)));
const store = createStore(reducer, composeEnhancers(applyMiddleware(thunk)));

//TODO logout if token expired

//const jwtToken = localStorage.jwtToken;

// if (jwtToken) {
//     setJWTToken(jwtToken);
//     const decoded_jwtToken = jwt_decode(jwtToken);
//     store.dispatch({
//         type: SET_CURRENT_USER,
//         payload: decoded_jwtToken
//     });
//
//     const currentTime = Date.now() / 1000;
//     if (decoded_jwtToken.exp < currentTime) {
//         store.dispatch(logout());
//         window.location.href = "/";
//     }
// }

console.log(store.getState());

ReactDOM.render(
  <Provider store={store}>
    <App />
  </Provider>,
  document.getElementById("root")
);
