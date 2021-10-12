import './App.css';
import React from "react";
import {BrowserRouter, Route, Switch} from "react-router-dom";
import Main from "../components/Main";
import Login from "../components/auth/Login";
import Registration from "../components/auth/Registration";
import AuthenticatedRoute from "../components/auth/AuthenticatedRoute";
import NotFound from "../components/NotFound";


function App() {
    return (
        <BrowserRouter>
            <Switch>
                <Route exact path="/login" component={Login}/>
                <Route exact path="/registration" component={Registration}/>
                <AuthenticatedRoute exact path="/" component={Main}/>
                <Route component={NotFound}/>
            </Switch>
        </BrowserRouter>
    );
}

export default App;
