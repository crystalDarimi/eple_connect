import React, {Component} from "react";
import "./index.css";
import App from "./App";
import Login from "./page/Login";
import { BrowserRouter, Routes, Route} from "react-router-dom";
import {Typography, Box} from "@mui/material";
import Signup from "./page/Signup";
import Calendar from "./page/Calendar";
import Sidebar from "./components/Sidebar";
import Header from "./components/Header";
import AppHeader from './components/AppHeader';
import OAuth2RedirectHandler from './service/OAuth2RedirectHandler';
import { getCurrentUser } from './util/APIUtils';
import { ACCESS_TOKEN } from './constants';
import Profile from './page/Profile';
import LoadingIndicator from './service/LoadingIndicator';
import MyStudent from "./page/MyStudent";




function Copyright(){
    return(
        <Typography variant="body2" color="textSecondary" align="center">
            {"Copyright © "}
            crystal darimi, {new Date().getFullYear()}
            {"."}
        </Typography>
    );
}

class AppRouter extends Component{
    constructor(props){
        super(props);
        this.state = {
          authenticated: false,
          currentUser: null,
          loading: true
        }

        this.loadCurrentlyLoggedInUser = this.loadCurrentlyLoggedInUser.bind(this);
        this.handleLogout = this.handleLogout.bind(this);
      }

      loadCurrentlyLoggedInUser() {
        getCurrentUser()
        .then(response => {
          this.setState({
            currentUser: response,
            authenticated: true,
            loading: false
          });
        }).catch(error => {
          this.setState({
            loading: false
          });
        });
      }

      handleLogout() {
        localStorage.removeItem(ACCESS_TOKEN);
        this.setState({
          authenticated: false,
          currentUser: null
        });
      }

      componentDidMount() {
        this.loadCurrentlyLoggedInUser();
      }

      render() {
        if(this.state.loading) {
          return <LoadingIndicator />
        }

    return (
    <div>
        <BrowserRouter>

        <AppHeader authenticated={this.state.authenticated} onLogout={this.handleLogout} />
        <Sidebar>
            <Routes>
                <Route path="/" element={<App />}/>
                <Route path="/calendar" element={<Calendar />}/>
                <Route path="/mystudents" element={<MyStudent />}/>
               <Route path="/signup" element={<Signup />}/>

               //                <Route path="/Login" element={<Login />}/>
               <Route path="/profile" element={<Profile  authenticated={this.state.authenticated} currentUser={this.state.currentUser} />}/>
               <Route path="/oauth2/redirect" element={<OAuth2RedirectHandler />}/>
               <Route path="/login" element={(props) => <Login authenticated={this.state.authenticated} {...props} />}/>
            </Routes>
        </Sidebar>
        </BrowserRouter>
        <Box mt ={5}>
              <Copyright />
        </Box>
        </div>
    );
   }
}
export default AppRouter;
