import axios from "axios";
import React, {useState, useEffect} from "react";
import Order from "./Order/Order"
import "./Orders.css"
import {Link} from "react-router-dom";

const Orders = () =>{
    const [data, setData] = useState(null)
    useEffect(() =>{
        axios.get("http://localhost:8080/api/v1/pizza/all").then(res => {
            setData(res.data)
        })
    }, [])
    return(
        <div className="container-fluid">
            <div className="row" id="orders">
                <div className="col-md-12 d-flex justify-content-center right-bck">
                    <div className="registration-right">
                        <h2>Orders</h2>
                        {data && data.map(el =>{
                            console.log(el)
                            return <Order key={el.id} name ={el.name} dough = {el.dough} ingredientsList={el.ingredientsList}
                                          sauce = {el.sauce} id={el.id} price = {el.price} size = {el.size}/>
                        })}
                        <p style={{marginTop:"20px"}}>
                           <Link to ="/">
                               go back
                           </Link>
                        </p>
                    </div>

                </div>

            </div>
        </div>
    )
}

export default Orders;