import axios from "axios";
import React, {useState} from "react";
import { Redirect} from "react-router-dom";
import { useHistory } from 'react-router';


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
        <div>
            <form onSubmit={handleSubmit}>
                <input id="email" type="email" value={credentials.email} minLength={5} onChange={handleChange}
                       name="email" placeholder="email" required/>
                <input id="password" type="password" placeholder="password" minLength={5}
                       value={credentials.password} onChange={handleChange}
                       name="password" required/>
                <button type="submit">Submit</button>
            </form>

        </div>
    );
}

export default Login;