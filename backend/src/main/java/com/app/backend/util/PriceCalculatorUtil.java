package com.app.backend.util;

import com.app.backend.domain.pizza.Ingredients;

import java.util.List;

public class PriceCalculatorUtil {
    public static double getMeatIngredientPercentage(List<Ingredients> ingredients){
        long count = ingredients.stream()
                .filter(ingredient -> ingredient.isMeat())
                .count();
        return ((double) count / (double) ingredients.size()) * 100;
    }
}
