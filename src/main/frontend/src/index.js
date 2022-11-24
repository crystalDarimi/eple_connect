import React from 'react';
import './index.css';
import App from './App.js';
import reportWebVitals from './reportWebVitals';
import AppRouter  from "./AppRouter";
import {createRoot} from "react-dom/client";
import {BrowserRouter} from "react-router-dom";


const container = document.getElementById('root');
const root = createRoot(container);

root.render(<AppRouter>
    <React.StrictMode>
        <App/>
    </React.StrictMode>
</AppRouter>);

/*const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>,
    document.getElementById("root")
);

 */

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
