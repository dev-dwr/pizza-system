package com.app.backend.controller.api.v1;

import com.app.backend.domain.security.AppUser;
import com.app.backend.domain.security.TokenRequest;
import com.app.backend.login.LoginRequest;
import com.app.backend.login.LoginResponse;
import com.app.backend.service.security.LoginService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("*")
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class LoginController {

    private final LoginService loginService;


    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse login(@RequestBody LoginRequest request){
        return loginService.loginUser(request);
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    public String logoutUser(@RequestBody TokenRequest token){
        loginService.logoutUser(token);
        return "logged out";
    }

//    @PostMapping("/checkIfUserIsLoggedIn")
//    public String checkIfUserIsLoggedIn(TokenRequest token){
//        boolean isUserLoggedIn = loginService.checkIfUserIsLoggedIn(token);
//        if(isUserLoggedIn){
//            return "user is logged in";
//        }
//        return "user is not logged in";
//    }

}
