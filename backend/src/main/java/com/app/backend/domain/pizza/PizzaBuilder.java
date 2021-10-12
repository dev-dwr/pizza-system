package com.app.backend.domain.pizza;

import java.util.ArrayList;
import java.util.List;

public class PizzaBuilder {
    private String pizzaName;
    private Size size;
    private Sauce sauce;
    private Dough dough;
    private List<Ingredients> ingredientsList = new ArrayList<>();

    public PizzaBuilder withName(String pizzaName){
        this.pizzaName = pizzaName.isBlank() ? "Our pizza" : pizzaName;
        return this;
    }

    public PizzaBuilder withSize(String sizeValue) {
        this.size = Size.valueOf(sizeValue.isBlank() ? "MEDIUM" : sizeValue);
        return this;
    }

    public PizzaBuilder withSauce(String sauceValue) {
        this.sauce = Sauce.valueOf(sauceValue.isBlank() ? "TOMATO_CHEESE" : sauceValue);
        return this;
    }


    public PizzaBuilder withDough(String doughValue) {
        this.dough = Dough.valueOf(doughValue.isBlank() ? "ITALIAN" : doughValue);
        return this;
    }

    public PizzaBuilder withIngredient(Ingredients ingredient) {
        ingredientsList.add(ingredient);
        return this;
    }
    public Pizza build(){
        return new Pizza(pizzaName, dough, sauce, size, List.copyOf(ingredientsList));
    }
}
