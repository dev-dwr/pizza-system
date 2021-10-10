import './App.css';
import React,{useState, useEffect} from "react";
import {BrowserRouter, Route, Switch } from "react-router-dom";
import Main from "../components/Main";
import Login from "../components/auth/Login";
import Registration from "../components/auth/Registration";
import AuthenticatedRoute from "../components/auth/AuthenticatedRoute";
import NotFound from "../components/NotFound";
import UnauthenticatedRoute from "../components/auth/UnauthenticatedRoute";

function App() {
    const[isAuth, setIsAuth] = useState(false);

    useEffect(() => {
        onLoad();
    }, []);

    async function onLoad() {
        try {
            setIsAuth(false);
        } catch (e) {
            alert(e);
        }
    }
    return (
        <BrowserRouter>
            <Switch>
                <AuthenticatedRoute exact path="/" component={Main} appProps={{isAuth}}/>
                <UnauthenticatedRoute path="/login" component={Login} appProps={{isAuth}}/>
                <UnauthenticatedRoute path="/registration" component={Registration} appProps={{isAuth}}/>
                <Route component={NotFound}/>
            </Switch>
        </BrowserRouter>
    );
}

export default App;
