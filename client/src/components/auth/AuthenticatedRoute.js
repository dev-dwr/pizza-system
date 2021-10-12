import {Redirect, Route} from "react-router-dom";

const AuthenticatedRoute = ({ component: Component, ...restOfProps }) => {
    const isAuthenticated = localStorage.getItem("logged_in");
    return (
        <Route
            {...restOfProps}
            render={(props) =>
                isAuthenticated ? <Component {...props} /> : <Redirect to="/login" />
            }
        />
    );
}

export default AuthenticatedRoute;