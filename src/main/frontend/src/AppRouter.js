import React from "react";
import "./index.css";
import App from "./App";
import Login from "./page/Login";
import { BrowserRouter, Routes, Route} from "react-router-dom";
import {Typography, Box} from "@mui/material";
import Signup from "./page/Signup";
import Calendar from "./page/Calendar";
import './components/Sidebar';
import Sidebar from "./components/Sidebar";
import Header from "./components/Header";
import MyStudent from "./page/MyStudent";
import OAuth2RedirectHandler from './service/OAuth2RedirectHandler';
import MyClass from "./page/MyClass";


function Copyright(){
    return(
        <Typography variant="body2" color="textSecondary" align="center">
            {"Copyright © "}
            crystal darimi, {new Date().getFullYear()}
            {"."}
        </Typography>
    );

}
const AppRouter=() =>{

    return(
        <div>
            <BrowserRouter>
                {<Header />}
                <Sidebar>
                <Routes>
                    <Route path ="/login" element = {<Login/>}/>
                    <Route path ="/" element = {<App /> }/>
                    <Route path = "/signup" element = {<Signup/> }/>
                    <Route path="/calendar" element={<Calendar/>}/>
                    <Route path="/mystudents" element={<MyStudent/>}/>
                    <Route path="/oauth2/redirect" element={<OAuth2RedirectHandler />}/>
                    <Route path="/myclass" element={<MyClass/>}/>
                </Routes>
                </Sidebar>
            </BrowserRouter>
            <Box mt ={5}>
                <Copyright />
             </Box>
        </div>
    );
};

export default AppRouter;
