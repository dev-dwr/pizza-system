import React, {_, useState} from "react";
import axios from "axios";
import {Link} from "react-router-dom";
import {useHistory} from 'react-router';
import "./Main.css"

const ingredients = [
    {name: "HAM", id: 0, isChecked: false},
    {name: "BACON", id: 1, isChecked: false},
    {name: "SALAMI", id: 2, isChecked: false},
    {name: "MUSHROOMS", id: 3, isChecked: false},
    {name: "TOMATO", id: 4, isChecked: false},
    {name: "PINEAPPLE", id: 5, isChecked: false},
    {name: "CHILLI", id: 6, isChecked: false}
]

const Main = () => {
    const routerHistory = useHistory();
    const [data, setData] = useState({
        name: "",
        dough: "",
        sauce: "",
        size: "",
        ingredientsList: []
    })


    const [token, _] = useState({
        token: localStorage.getItem("token")
    })

    const onLogout = () => {
        axios.post("http://localhost:8080/api/v1/logout", token).then(res => {
            if (res.status === 200) {
                localStorage.removeItem("logged_in");
                localStorage.removeItem("token");

            }
        }).then(() => {
            routerHistory.push("/login");
        })
    }


    const handleFormSubmit = (e) => {
        e.preventDefault();
        axios.post("http://localhost:8080/api/v1/pizza/create", data).then(res => {
            console.log(res);
        }).then(() => {
            routerHistory.push("/all")
        })
    }
    const handleCheckboxChange = (e) => {


        console.log("id = " + e.target.id)
        console.log("value = " + e.target.value)
        console.log("checked = " + e.target.checked)

        if (e.target.checked) {
            if (!data.ingredientsList.includes(e.target.value)) {
                setData(prevState => ({ingredientsList: [...prevState.ingredientsList, e.target.value]}))
            } else {
                setData(prevState => ({ingredientsList: prevState.ingredientsList.filter(ingredient => ingredient !== e.target.value)}))
            }
        }
        console.log(data)
    }
    const onChangeInputData = e => {
        console.log(e.target.value)
        e.persist();
        setData({
            ...data,
            [e.target.name]: e.target.value,
        })
    }

    return (
        <div className="container">
            <div className="row justify-content-md-center" id="main">
                <aside className="col-sm-4">
                    <div className="card">
                        <article className="card-body">
                            <a onClick={onLogout} href="/login"
                               className="float-right btn btn-outline-primary">Logout</a>
                            <h4 className="card-title mb-4 mt-1">Create Your Pizza</h4>
                            <form onSubmit={handleFormSubmit}>
                                <div className="form-group">
                                    <label>Name of the Pizza</label>
                                    <input id="name" type="text" minLength={3}
                                           onChange={onChangeInputData} value={data.name}
                                           name="name" placeholder="Name" className="form-control" required/>
                                </div>
                                <div htmlFor="dough" className="form-group">
                                    <label>Dough</label>
                                    <select className="form-control" onChange={onChangeInputData} value={data.dough}
                                            id="dough" name="dough">
                                        <option value="empty">--Please choose an option--</option>
                                        <option value="ITALIAN">ITALIAN</option>
                                        <option value="POLISH">POLISH</option>
                                        <option value="AMERICAN">AMERICAN</option>
                                    </select>
                                </div>

                                <div htmlFor="sauce" className="form-group">
                                    <label>Sauce</label>
                                    <select className="form-control" onChange={onChangeInputData} value={data.sauce}
                                            id="sauce" name="sauce">
                                        <option value="">--Please choose an option--</option>
                                        <option value="TOMATO">TOMATO</option>
                                        <option value="CHEESE">CHEESE</option>
                                        <option value="TOMATO_CHEESE">TOMATO_CHEESE</option>
                                        <option value="PAPRIKA">PAPRIKA</option>
                                    </select>
                                </div>

                                <div htmlFor="size" className="form-group">
                                    <label>Size</label>
                                    <select className="form-control" onChange={onChangeInputData} value={data.size}
                                            id="size" name="size">
                                        <option value="">--Please choose an option--</option>
                                        <option value="SMALL">SMALL</option>
                                        <option value="MEDIUM">MEDIUM</option>
                                        <option value="LARGE">LARGE</option>
                                        <option value="MEGA_LARGE">MEGA_LARGE</option>
                                    </select>
                                </div>

                                <div htmlFor="size" className="form-group">
                                    <label>Additional Ingredients</label>
                                    {ingredients && ingredients.map(({name, isChecked}, index) => {
                                        return (
                                            <div key={index} className="form-check">
                                                <input
                                                    className="form-check-input" type="checkbox"
                                                    onChange={handleCheckboxChange}
                                                    value={name}
                                                    name={name}
                                                    id={index}/>
                                                <label className="form-check-label" htmlFor={index}>
                                                    {name}
                                                </label>
                                            </div>
                                        )
                                    })}
                                </div>


                                <div className="form-group">
                                    <button type="submit" className="btn btn-primary btn-block"> Send Order</button>
                                </div>

                                <div className="form-group">
                                    <Link to ="/all">
                                        <p>Show Orders</p>
                                    </Link>

                                </div>
                            </form>
                        </article>
                    </div>
                </aside>
            </div>
        </div>
    )
}

export default Main;
