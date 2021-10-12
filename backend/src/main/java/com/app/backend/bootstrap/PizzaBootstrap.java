package com.app.backend.bootstrap;

import com.app.backend.domain.pizza.Ingredients;
import com.app.backend.domain.pizza.Pizza;
import com.app.backend.domain.pizza.PizzaBuilder;
import com.app.backend.repository.PizzaRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class PizzaBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private final PizzaRepository pizzaRepository;

    public PizzaBootstrap(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadPizza();
    }

    private void loadPizza(){
        var pizza1 = new PizzaBuilder()
                .withDough("ITALIAN")
                .withName("first pizza")
                .withSauce("TOMATO")
                .withSize("LARGE")
                .withIngredient(Ingredients.valueOf("HAM"))
                .withIngredient(Ingredients.valueOf("BACON"))
                .build();
        pizzaRepository.save(pizza1);

        var pizza2 = new PizzaBuilder()
                .withDough("POLISH")
                .withName("second pizza")
                .withSauce("CHEESE")
                .withSize("SMALL")
                .withIngredient(Ingredients.valueOf("HAM"))
                .withIngredient(Ingredients.valueOf("BACON"))
                .build();
        pizzaRepository.save(pizza2);
    }
}
