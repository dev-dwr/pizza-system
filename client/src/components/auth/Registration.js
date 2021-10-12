import axios from "axios";
import React, {useState} from "react";
import {Redirect} from "react-router-dom";
import { useHistory } from 'react-router';

const Registration = () => {
    const routerHistory = useHistory();
    const [credentials, setCredentials] = useState({
        firstname: "",
        lastname: "",
        email: "",
        password: ""
    })

    const handleSubmit = (e) => {
        e.preventDefault();
        axios.post("http://localhost:8080/api/v1/registration", credentials).then(res => {
            if (res.status === 200) {
                console.log("response", res);
            }
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
            <form onSubmit={handleSubmit}>
                <input id="firstname" type="text" minLength={5} value={credentials.firstname}
                       onChange={handleChange} name="firstname" placeholder="firstname" required/>
                <input id="lastname" type="text" value={credentials.lastname} minLength={5}
                       onChange={handleChange} placeholder="lastname" name="lastname" required/>
                <input id="email" type="email" value={credentials.email} minLength={5} onChange={handleChange}
                       name="email" placeholder="email" required/>
                <input id="password" type="password" placeholder="password" minLength={5} value={credentials.password}
                       onChange={handleChange}
                       name="password" required/>
                <button type="submit">Submit</button>
            </form>
        </div>
    );
}
export default Registration;