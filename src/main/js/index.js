import React from 'react';
import ReactDOM from 'react-dom';
import './index.css'
import App2 from "./FrontEndRewrite/App2";
import {applyMiddleware, createStore,compose} from "redux";
import reducer from "./FrontEndRewrite/Store_Reducers";
import {Provider,} from "react-redux"
import thunk from 'redux-thunk'


const composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;
const store=createStore(reducer,composeEnhancers(applyMiddleware(thunk)))

ReactDOM.render(
    <Provider store={store}>
        {/*<App />*/}
        <App2/>
    </Provider>
,
  document.getElementById('root')
);