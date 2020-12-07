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
const store = createStore(reducer, composeEnhancers(applyMiddleware(thunk)));

ReactDOM.render(
  <Provider store={store}>
    <App />
  </Provider>,
  document.getElementById("root")
);
