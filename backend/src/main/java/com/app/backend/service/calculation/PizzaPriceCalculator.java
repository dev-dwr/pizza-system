package com.app.backend.service.calculation;

import com.app.backend.domain.pizza.Pizza;

public interface PizzaPriceCalculator {
    int calculate(Pizza pizza);
}
