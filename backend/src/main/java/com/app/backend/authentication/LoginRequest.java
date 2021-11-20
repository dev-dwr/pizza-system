package com.app.backend.authentication;

import lombok.Value;

@Value
public class LoginRequest {
    String password;
    String email;
}
