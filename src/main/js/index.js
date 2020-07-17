import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import './index.css'

import {applyMiddleware, createStore,compose} from "redux";
import reducer from "./Store/reducer";
import {Provider,} from "react-redux"

import thunk from 'redux-thunk'

import * as actionTypes from "./Store/actions"

const composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;

const store=createStore(reducer,composeEnhancers(applyMiddleware(thunk)))
// const store=createStore(reducer,compose(applyMiddleware(thunk)))

ReactDOM.render(
    <Provider store={store}>
        <App />
    </Provider>
,
  document.getElementById('root')
);