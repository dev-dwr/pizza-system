package com.app.backend.service;

import com.app.backend.domain.pizza.Pizza;
import com.app.backend.exceptions.NotFoundException;
import com.app.backend.repository.PizzaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PizzaService {

    private final PizzaRepository pizzaRepository;

    public Pizza createPizza(Pizza pizza) {
        Pizza createdPizza = new Pizza(pizza.getName(), pizza.getDough(), pizza.getSauce(),
                pizza.getSize(), pizza.getIngredientsList());

        pizzaRepository.save(createdPizza);

        return new Pizza(pizza.getName(), pizza.getDough(), pizza.getSauce(),
                pizza.getSize(), pizza.getIngredientsList());

    }

    public List<Pizza> findAllPizza() {
        return pizzaRepository.findAll();
    }

    public Pizza findPizzaById(Long id) {
        return pizzaRepository.findById(id).orElseThrow(() -> new NotFoundException());
    }

}
