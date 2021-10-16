import {Link} from "react-router-dom";

const Order = ({name, dough, sauce, id, price, size, ingredientsList}) => {
    return (
        <div className="event-list" id="list">
            <div className="card flex-row">
                <Link to={`pizza/${id}`}>
                <div className="card-body">
                    <h4 className="card-title h5 h4-sm">
                        <i className="fas fa-caret-right"
                           aria-hidden="true"></i><span>Order named:{" "}{name}</span><i
                        className="fas fa-caret-right"
                        aria-hidden="true"></i></h4>

                </div>
                </Link>
            </div>
        </div>

    )
}

export default Order;