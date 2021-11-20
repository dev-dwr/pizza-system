package com.app.backend.bootstrap;

import com.app.backend.domain.pizza.Ingredients;
import com.app.backend.domain.pizza.Pizza;
import com.app.backend.domain.pizza.PizzaBuilder;
import com.app.backend.repository.PizzaRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PizzaBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private final PizzaRepository pizzaRepository;

    public PizzaBootstrap(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        //it is better to use flyway migrations?
        loadPizza();
    }

    private void loadPizza(){
        var pizza1 = Pizza.builder()
                .withDough("ITALIAN")
                .withName("first pizza")
                .withSauce("TOMATO")
                .withSize("LARGE")
                .withIngredients(List.of(Ingredients.valueOf("HAM"), Ingredients.valueOf("BACON")))
                .build();
        pizzaRepository.save(pizza1);
        var pizza2 = Pizza.builder()
                .withDough("ITALIAN")
                .withName("second pizza")
                .withSauce("TOMATO")
                .withSize("LARGE")
                .withIngredients(List.of(Ingredients.valueOf("HAM"), Ingredients.valueOf("BACON")))
                .build();
        pizzaRepository.save(pizza2);
        var pizza3 = Pizza.builder()
                .withDough("ITALIAN")
                .withName("third pizza")
                .withSauce("TOMATO")
                .withSize("LARGE")
                .withIngredients(List.of(Ingredients.valueOf("HAM"), Ingredients.valueOf("BACON")))
                .build();
        pizzaRepository.save(pizza3);


    }
}
