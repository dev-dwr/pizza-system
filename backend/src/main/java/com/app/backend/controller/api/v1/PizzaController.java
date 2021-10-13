package com.app.backend.controller.api.v1;


import com.app.backend.domain.pizza.Pizza;
import com.app.backend.service.PizzaService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping("api/v1")
public class PizzaController {
    private final PizzaService pizzaService;

    @PostMapping("/create")
    public Pizza createPizza(@RequestBody Pizza pizza){
        return pizzaService.createPizza(pizza);
    }

    @GetMapping("/pizza/all")
    public List<Pizza> getAllPizzas(){
        return pizzaService.findAllPizza();
    }

    @GetMapping("/pizza/{id}")
    public Pizza getPizzaById(@PathVariable Long id){
        return pizzaService.findPizzaById(id);
    }
}
