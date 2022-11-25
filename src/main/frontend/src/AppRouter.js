import React from "react";
import "./index.css";
import App from "./App";
import Login from "./page/Login";
import { BrowserRouter, Routes, Route} from "react-router-dom";
import {Typography, Box} from "@mui/material";
import Signup from "./page/Signup";

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
                <Routes>
                    <Route path ="/login" element = {<Login/>}/>
                    <Route path ="/" element = {<App /> }/>
                    <Route path = "/signup" element = {<Signup/> }/>
                </Routes>
            </BrowserRouter>
            <Box mt ={5}>
                <Copyright />
             </Box>
        </div>
    );
};

export default AppRouter;
