import {Redirect, Route} from "react-router-dom";

export default ({ component: C, appProps, ...rest }) =>
    <Route
        {...rest}
        render={props =>
            !appProps.isAuth
                ? <C {...props} {...appProps} />
                : <Redirect to="/" />}
    />;