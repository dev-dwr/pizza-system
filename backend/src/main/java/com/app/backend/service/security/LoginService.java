package com.app.backend.service.security;

import com.app.backend.login.LoginRequest;
import com.app.backend.registration.EmailValidator;
import com.app.backend.repository.security.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
@AllArgsConstructor
public class LoginService {
    private final AppUserRepository repository;
    private final EmailValidator emailValidator;

    public String loginUser(LoginRequest request) {
        String email = request.getEmail();

        boolean isValidEmail = emailValidator.test(email);
        if(!isValidEmail){
            throw new IllegalStateException("email not valid");
        }
        repository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException(String.format("%s was not found in database", email)));

        return "user logged in";
    }



}
