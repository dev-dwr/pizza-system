package com.app.backend.service.calculation;

import com.app.backend.domain.pizza.Pizza;

import static com.app.backend.util.PriceCalculatorUtil.getMeatIngredientPercentage;

public class MediumPizzaCalculator implements PizzaPriceCalculator{
    @Override
    public int calculate(Pizza pizza) {
        int price;
        if(pizza.getIngredientsList().size() <= 3){
            if(getMeatIngredientPercentage(pizza.getIngredientsList()) < 50){
                price = 25;
            }else{
                price = 27;
            }
        }else{
            if(getMeatIngredientPercentage(pizza.getIngredientsList()) < 40){
                price = 27;
            }else{
                price = 29;
            }
        }
        return price;
    }
}
