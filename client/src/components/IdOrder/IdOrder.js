import React, {useState, useEffect} from "react";
import {Link, useParams, useHistory} from "react-router-dom";
import axios from "axios";


const IdOrder = () => {
    const routerHistory = useHistory();
    const params = useParams();
    const [data, setData] = useState({})
    useEffect(() => {
        axios.get(`http://localhost:8080/api/v1/pizza/${params.id}`).then(res => {
            setData(res.data)
            console.log(res.data)
        })
    }, [params.id])


    const deleteOrder = () => {
        axios.delete(`http://localhost:8080/api/v1/pizza/delete/${params.id}`).then(res => {
            console.log(res)
        }).then(()=>{
            routerHistory.push("/all")
        })
    }

    return (
        <div className="container-fluid">
            <div className="row" id="orders">
                <div className="col-md-12 d-flex justify-content-center right-bck">
                    <div className="registration-right">
                        <h2>Order {params.id}</h2>
                        <div className="event-list" id="list">
                            <div className="card flex-row">
                                <div className="card-body">

                                    <h4 className="card-title h5 h4-sm">
                                            <button onClick={deleteOrder}
                                                    className="badge badge-pill badge-danger"
                                                    style={{marginRight: "10px"}}>Delete</button>
                                        <i className="fas fa-caret-right"
                                           aria-hidden="true"></i><span>{data.name}</span><i
                                        className="fas fa-caret-right"
                                        aria-hidden="true"></i></h4>
                                    <span className="badge badge-pill badge-info"
                                          style={{marginRight: "10px"}}>Price: {data.price}</span>
                                    <span className="badge badge-pill badge-primary"
                                          style={{marginRight: "10px"}}>Sauce: {data.sauce}</span>
                                    <span className="badge badge-pill badge-secondary"
                                          style={{marginRight: "10px"}}>Dough: {data.dough}</span>
                                    <span className="badge badge-pill badge-success" style={{
                                        marginRight: "10px",
                                        marginBottom: "10px"
                                    }}>Size: {data.size}</span>
                                    <p className="text-left card-text">Additional Ingredients:</p>
                                    {data.ingredientsList && data.ingredientsList.map(el => {
                                        return (
                                            <div>
                                                <div key={el}>
                                                    * {el}
                                                </div>
                                            </div>
                                        )
                                    })}
                                </div>
                            </div>
                            <p style={{marginTop: "20px"}}>
                                <Link to="/all">
                                    go back
                                </Link>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default IdOrder