import axios from "axios";
import React, {useState} from "react";
import {Link, Redirect} from "react-router-dom";
import { useHistory } from 'react-router';
import "./Login.css";

const Login = () => {
    const routerHistory = useHistory();
    const [credentials, setCredentials] = useState({
        email: "",
        password: ""
    })

    const handleSubmit = (e) => {
        e.preventDefault();
        axios.post("http://localhost:8080/api/v1/login", credentials).then(res => {
            if (res.status === 200) {
                console.log("response", res);
                localStorage.setItem("logged_in", res.data.loggedIn)
                localStorage.setItem("token", res.data.token)

            }
        }).then(() =>{
            routerHistory.push("/");
        })
    }


    const handleChange = (e) => {
        e.persist()
        setCredentials(credentials => ({
            ...credentials,
            [e.target.name]: e.target.value
        }))
    }
    if (localStorage.getItem("logged_in")) {
        return <Redirect push to="/"/>
    }
    return (

        <div className="container">
            <div className="row justify-content-md-center">
                <aside className="col-sm-4">
                    <div className="card">
                        <article className="card-body">
                            <a href="/registration" className="float-right btn btn-outline-primary">Registration</a>
                            <h4 className="card-title mb-4 mt-1">Sign In</h4>
                            <form  onSubmit={handleSubmit}>
                                <div className="form-group">
                                    <label>Your email</label>
                                    <input id="email" type="email" value={credentials.email} minLength={5} onChange={handleChange}
                                           name="email" placeholder="Email" className="form-control" required/>
                                </div>
                                <div className="form-group">
                                    <label>Your password</label>
                                    <input id="password" type="password" placeholder="******" minLength={5}
                                           value={credentials.password} onChange={handleChange}
                                           name="password" required className="form-control"/>
                                </div>

                                <div className="form-group">
                                    <button type="submit" className="btn btn-primary btn-block"> Login</button>
                                </div>

                            </form>
                        </article>
                    </div>
                </aside>
            </div>
        </div>

    );
}

export default Login;