import axios from "axios";
import React, {useState} from "react";
import {Redirect} from "react-router-dom";
import { useHistory } from 'react-router';
import Spinner from "../Spinner/Spinner"
const Registration = () => {
    const routerHistory = useHistory();
    const [isLoading, setLoading] = useState(false)
    const [credentials, setCredentials] = useState({
        firstname: "",
        lastname: "",
        email: "",
        password: ""
    })

    const handleSubmit = (e) => {
        e.preventDefault();
        setLoading(true);
        axios.post("http://localhost:8080/api/v1/registration", credentials).then(res => {
            if (res.status === 200) {
                console.log("response", res);
            }
            setLoading(false)
        }).then(() => {
            routerHistory.push("/login")
        })
    }

    const handleChange = (e) => {
        e.persist()
        setCredentials(credentials => ({
            ...credentials,
            [e.target.name]: e.target.value
        }))
    }
    if(localStorage.getItem("logged_in")){
        return <Redirect to = "/login"/>
    }
    return (
        <div>
        {
            isLoading ? <Spinner/> : (
                <div className="container">
                    <div className="row justify-content-md-center">
                        <aside className="col-sm-4">
                            <div className="card">
                                <article className="card-body">
                                    <a href="/login" className="float-right btn btn-outline-primary">Log in</a>
                                    <h4 className="card-title mb-4 mt-1">Registration</h4>
                                    <form onSubmit={handleSubmit}>
                                        <div className="form-group">
                                            <label>Your firstname</label>
                                            <input id="firstname" type="text" minLength={5} value={credentials.firstname}
                                                   onChange={handleChange} name="firstname" placeholder="firstname" required className="form-control"/>
                                        </div>
                                        <div className="form-group">
                                            <label>Your lastname</label>
                                            <input id="lastname" type="text" className="form-control" value={credentials.lastname} minLength={5}
                                                   onChange={handleChange} placeholder="lastname" name="lastname" required/>
                                        </div>
                                        <div className="form-group">
                                            <label>Your email</label>
                                            <input id="email" type="email" value={credentials.email} minLength={5} onChange={handleChange}
                                                   name="email" placeholder="email" className="form-control" required/>
                                        </div>
                                        <div className="form-group">
                                            <label>Your password</label>
                                            <input id="password" type="password" placeholder="*******" minLength={5} value={credentials.password}
                                                   onChange={handleChange}
                                                   name="password" required className="form-control"/>
                                        </div>

                                        <div className="form-group">
                                            <button type="submit" className="btn btn-primary btn-block"> Sign Up</button>
                                        </div>

                                    </form>
                                </article>
                            </div>
                        </aside>
                    </div>
                </div>
            ) }
        </div>
    );
}
export default Registration;