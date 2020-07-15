import React from 'react';
import ReactDOM from 'react-dom';
import App from './main/js/App';
import './index.css'

import {applyMiddleware, createStore} from "redux";
import reducer from "./main/js/reducer";
import {Provider} from "react-redux"

import thunk from 'redux-thunk'

import * as actionTypes from "../src/main/js/actions"


const store=createStore(reducer,composeEnhancers(applyMiddleware(thunk)))

ReactDOM.render(
// ReactDOM.render(

    <Provider store={store}>
        <App />
    </Provider>

,
  document.getElementById('root')
);