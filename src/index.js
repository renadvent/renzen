import React from 'react';
import ReactDOM from 'react-dom';
import App from './main/js/App';
import './index.css'

import {createStore} from "redux";
import reducer from "./main/js/reducer";
import {Provider} from "react-redux"

const store=createStore(reducer)

ReactDOM.render(
// ReactDOM.render(

    <Provider store={store}>
        <App />
    </Provider>

,
  document.getElementById('root')
);