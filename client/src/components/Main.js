import React, {_, useState} from "react";
import axios from "axios";
import {Link} from "react-router-dom";
import { useHistory } from 'react-router';

const Main = () => {
    const [token, _] = useState({
        token: localStorage.getItem("token")
    })
    const routerHistory = useHistory();
    const onLogout = () =>{
        axios.post("http://localhost:8080/api/v1/logout", token).then(res =>{
            if (res.status === 200) {
                localStorage.removeItem("logged_in");
                localStorage.removeItem("token");

            }
        }).then(() =>{
            routerHistory.push("/login");
        })
    }
    return(
        <div>you are in
            <Link to ="/login">
            <button onClick={onLogout}>Logout</button>
            </Link>
        </div>
    )
}

export default Main;
