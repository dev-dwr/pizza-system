package com.app.backend.service.calculation;

import com.app.backend.domain.pizza.Pizza;
import com.app.backend.domain.pizza.Size;
import com.app.backend.service.calculation.decorator.LoggingDecorator;

public class PriceCalculator {
    private final SwitchCalculationStrategy switchCalculationStrategy;

    public PriceCalculator(SwitchCalculationStrategy switchCalculationStrategy) {
        this.switchCalculationStrategy = switchCalculationStrategy;
    }

    public int calculatePrice(Pizza pizza) {
        Size size = pizza.getSize();

        PizzaPriceCalculator pizzaPriceCalculator = obtainDecorator(size);

        return pizzaPriceCalculator.calculate(pizza);
    }


    private PizzaPriceCalculator obtainDecorator(Size size) {
        return new LoggingDecorator(switchCalculationStrategy.switchStrategy(size));
    }
}
