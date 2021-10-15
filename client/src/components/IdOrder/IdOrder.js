import React, {useState, useEffect} from "react";
import { useParams } from "react-router-dom";
import axios from "axios";


const IdOrder = () => {
    const params = useParams();
    const [data, setData] = useState({})
    useEffect(()=>{
        axios.get(`http://localhost:8080/api/v1/pizza/${params.id}`).then(res =>{
            setData(res.data)
            console.log(res.data)
        })
    }, [params.id])

    return(
    <div>
        <div>{data.name}</div>
        <div>{data.id}</div>
        <div>{data.dough}</div>
        {data.ingredientsList && data.ingredientsList.map(el => {
            return <div>{el}</div>
        })}
        <div>{data.price}</div>
        <div>{data.sauce}</div>
        <div>{data.size}</div>
    </div>
    )
}

export default IdOrder