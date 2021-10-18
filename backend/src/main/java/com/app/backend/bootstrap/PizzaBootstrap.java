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
                .withIngredient(Ingredients.valueOf("TOMATO"))
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

        var pizza3 = new PizzaBuilder()
                .withDough("POLISH")
                .withName("third pizza")
                .withSauce("CHEESE")
                .withSize("SMALL")
                .withIngredient(Ingredients.valueOf("HAM"))
                .withIngredient(Ingredients.valueOf("MUSHROOMS"))
                .withIngredient(Ingredients.valueOf("BACON"))
                .build();
        pizzaRepository.save(pizza3);

        var pizza4 = new PizzaBuilder()
                .withDough("POLISH")
                .withName("fourth pizza")
                .withSauce("CHEESE")
                .withSize("SMALL")
                .withIngredient(Ingredients.valueOf("HAM"))
                .build();
        pizzaRepository.save(pizza4);

        var pizza5 = new PizzaBuilder()
                .withDough("POLISH")
                .withName("best pizza")
                .withSauce("CHEESE")
                .withSize("SMALL")
                .withIngredient(Ingredients.valueOf("HAM"))
                .withIngredient(Ingredients.valueOf("MUSHROOMS"))
                .withIngredient(Ingredients.valueOf("BACON"))
                .build();
        pizzaRepository.save(pizza5);

        var pizza6 = new PizzaBuilder()
                .withDough("POLISH")
                .withName("meat pizza")
                .withSauce("CHEESE")
                .withSize("SMALL")
                .withIngredient(Ingredients.valueOf("HAM"))
                .withIngredient(Ingredients.valueOf("MUSHROOMS"))
                .withIngredient(Ingredients.valueOf("BACON"))
                .build();
        pizzaRepository.save(pizza6);

        var pizza7 = new PizzaBuilder()
                .withDough("POLISH")
                .withName("pizzaaaaa")
                .withSauce("CHEESE")
                .withSize("SMALL")
                .withIngredient(Ingredients.valueOf("HAM"))
                .withIngredient(Ingredients.valueOf("MUSHROOMS"))
                .withIngredient(Ingredients.valueOf("BACON"))
                .build();
        pizzaRepository.save(pizza7);

        var pizza8 = new PizzaBuilder()
                .withDough("POLISH")
                .withName("mock data")
                .withSauce("CHEESE")
                .withSize("SMALL")
                .withIngredient(Ingredients.valueOf("HAM"))
                .withIngredient(Ingredients.valueOf("MUSHROOMS"))
                .withIngredient(Ingredients.valueOf("BACON"))
                .build();
        pizzaRepository.save(pizza8);

        var pizza9 = new PizzaBuilder()
                .withDough("POLISH")
                .withName("test")
                .withSauce("CHEESE")
                .withSize("SMALL")
                .withIngredient(Ingredients.valueOf("HAM"))
                .withIngredient(Ingredients.valueOf("MUSHROOMS"))
                .withIngredient(Ingredients.valueOf("BACON"))
                .build();
        pizzaRepository.save(pizza9);

        var pizza10 = new PizzaBuilder()
                .withDough("POLISH")
                .withName("test1223")
                .withSauce("CHEESE")
                .withSize("SMALL")
                .withIngredient(Ingredients.valueOf("HAM"))
                .withIngredient(Ingredients.valueOf("MUSHROOMS"))
                .withIngredient(Ingredients.valueOf("BACON"))
                .build();
        pizzaRepository.save(pizza10);

        var pizza11 = new PizzaBuilder()
                .withDough("POLISH")
                .withName("mock data 234")
                .withSauce("CHEESE")
                .withSize("SMALL")
                .withIngredient(Ingredients.valueOf("HAM"))
                .withIngredient(Ingredients.valueOf("MUSHROOMS"))
                .withIngredient(Ingredients.valueOf("BACON"))
                .build();
        pizzaRepository.save(pizza11);
    }
}
