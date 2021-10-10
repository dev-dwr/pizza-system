import axios from "axios";
import React, {useState} from "react";

const Login = () =>{
    const [credentials, setCredentials] = useState({
        email: "",
        password: ""
    })

    const handleSubmit = (e) => {
        e.preventDefault();
        axios.post("http://localhost:8080/api/v1/registration", credentials).then(res => {
            if (res.status === 200) {
                console.log("response", res);
            }
        })

    }
    const handleChange = (e) => {
        e.persist()
        setCredentials(credentials => ({
            ...credentials,
            [e.target.name]: e.target.value
        }))
    }

    return (
        <div>
            <form onSubmit={handleSubmit}>
                <input id="email" type="email" value={credentials.email} minLength={8} onChange={handleChange}
                       name="email"  placeholder="email" required/>
                <input id="password" type="password" placeholder="password" minLength={8} value={credentials.password} onChange={handleChange}
                       name="password" required/>
                <button type="submit">Submit</button>
            </form>
        </div>
    );
}

export default Login;