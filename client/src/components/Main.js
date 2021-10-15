import React, {_, useState} from "react";
import axios from "axios";
import {Link} from "react-router-dom";
import {useHistory} from 'react-router';


const ingredients = [
    {name: "HAM", id: 0,isChecked:false},
    {name: "BACON", id: 1, isChecked:false},
    {name: "SALAMI", id: 2, isChecked:false},
    {name: "MUSHROOMS", id: 3, isChecked:false},
    {name: "TOMATO", id: 4, isChecked:false},
    {name: "PINEAPPLE", id: 5, isChecked:false},
    {name: "CHILLI", id: 6, isChecked:false}
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
        })
    }
    const handleCheckboxChange = (e) =>{


        console.log("id = " + e.target.id)
        console.log("value = " + e.target.value  )
        console.log("checked = " + e.target.checked)

        if(e.target.checked){
            if(!data.ingredientsList.includes(e.target.value)){
                setData(prevState => ({ingredientsList: [...prevState.ingredientsList, e.target.value]}))
            }else{
                setData(prevState => ({ingredientsList: prevState.ingredientsList.filter(ingredient => ingredient !== e.target.value)}))
            }
        }
        console.log(data)
    }
    const onChangeInputData =  e => {
        console.log(e.target.value)
        e.persist();
        setData( {
            ...data,
            [e.target.name]: e.target.value,
        })
    }

    return (
        <div>
            <Link to="/login">
                <button onClick={onLogout}>Logout</button>
            </Link>
            <form onSubmit={handleFormSubmit}>
                <input id="name" type="text" value={data.name} minLength={3} onChange={onChangeInputData}
                       name="name" placeholder="pizza name" required/>

                <label>Choose a dough:</label>
                <select onChange={onChangeInputData} value={data.dough} name="dough">
                    <option value="empty">--Please choose an option--</option>
                    <option value="ITALIAN">ITALIAN</option>
                    <option value="POLISH">POLISH</option>
                    <option value="AMERICAN">AMERICAN</option>
                </select>

                <label>Choose a sauce:</label>
                <select onChange={onChangeInputData} value={data.sauce} name="sauce">
                    <option value="">--Please choose an option--</option>
                    <option value="TOMATO">TOMATO</option>
                    <option value="CHEESE">CHEESE</option>
                    <option value="TOMATO_CHEESE">TOMATO_CHEESE</option>
                    <option value="PAPRIKA">PAPRIKA</option>
                </select>

                <label>Choose a size:</label>
                <select onChange={onChangeInputData} value={data.size} name="size">
                    <option value="">--Please choose an option--</option>
                    <option value="SMALL">SMALL</option>
                    <option value="MEDIUM">MEDIUM</option>
                    <option value="LARGE">LARGE</option>
                    <option value="MEGA_LARGE">MEGA_LARGE</option>
                </select>

                {ingredients && ingredients.map(({ name, isChecked }, index) => {
                  return(<li key={index}>
                        <div className="toppings-list-item">
                            <div className="left-section">
                                <input
                                    type="checkbox"
                                    id={index}
                                    name={name}
                                    value={name}
                                    onChange={handleCheckboxChange}
                                />
                                <label htmlFor={index}>{name}</label>
                            </div>
                        </div>
                    </li>
                  )
                })}

                <button type="submit">Send Order</button>
            </form>
        </div>
    )
}

export default Main;
