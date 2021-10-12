package com.app.backend.login;

public class LoginResponse {
    private boolean isLoggedIn;
    private String token;

    public LoginResponse(boolean isLoggedIn, String token) {
        this.isLoggedIn = isLoggedIn;
        this.token = token;
    }

    public LoginResponse() {
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public String getToken() {
        return token;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
