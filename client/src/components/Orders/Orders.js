import axios from "axios";
import React, {useState, useEffect} from "react";
import Order from "./Order/Order"

const Orders = () =>{
    const [data, setData] = useState(null)
    useEffect(() =>{
        axios.get("http://localhost:8080/api/v1/pizza/all").then(res => {
            setData(res.data)
        })
    }, [])
    return(
        <div>
            {
                data && data.map(el =>{
                    console.log(el)
                   return <Order name ={el.name} dough = {el.dough} ingredientsList={el.ingredientsList}
                                 sauce = {el.sauce} id={el.id} price = {el.price} size = {el.size}/>
                })
            }
        </div>
    )
}

export default Orders;