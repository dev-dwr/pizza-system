package com.app.backend.authentication;

import lombok.Data;

@Data
public class RegistrationRequest {
    private final String firstname;
    private final String lastname;
    private final String password;
    private final String email;


}
