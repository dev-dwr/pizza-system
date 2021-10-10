package com.app.backend.controller.api.v1;

import com.app.backend.login.LoginRequest;
import com.app.backend.registration.RegistrationRequest;
import com.app.backend.service.security.AppUserService;
import com.app.backend.service.security.LoginService;
import com.app.backend.service.security.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("*")
@AllArgsConstructor
@RequestMapping("/api/v1")
public class LoginController {

    private final LoginService loginService;


    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request){
        return loginService.loginUser(request);
    }

    @PostMapping("/logout")
    public void logoutUser(){
        SecurityContextHolder.clearContext();
    }


}

