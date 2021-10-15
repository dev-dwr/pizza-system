const Order = ({name, dough, sauce, id, price, size, ingredientsList}) =>{
    return(
        <li>
            <div>{name}</div>
            <div>{dough}</div>
            <div>{sauce}</div>
            <div>{price}</div>
            <div>{size}</div>
            <div>{id}</div>
            <div>{ingredientsList}</div>
        </li>
    )
}

export default Order;