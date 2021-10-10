package com.app.backend.login;

public class LoginRequest {
    private final String password;
    private final String email;

    public LoginRequest(String password, String email) {
        this.password = password;
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
