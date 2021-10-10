package com.app.backend.controller.api.v1;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class PizzaController {

    @GetMapping
    public String test(){
        return "tested";
    }
}
