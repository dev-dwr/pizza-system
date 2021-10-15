package com.app.backend.service.calculation;

import com.app.backend.domain.pizza.Size;

public class SwitchCalculationStrategy {

    public PizzaPriceCalculator switchStrategy(Size size) {
        PizzaPriceCalculator pizzaPriceCalculator;
        switch (size) {
            case SMALL:
                pizzaPriceCalculator = new SmallPizzaCalculator();
                break;
            case MEDIUM:
                pizzaPriceCalculator = new MediumPizzaCalculator();
                break;
            case LARGE:
                pizzaPriceCalculator = new LargePizzaCalculator();
                break;
            case MEGA_LARGE:
                pizzaPriceCalculator = new MegaLargePizzaCalculator();
                break;
            default:
                throw new IllegalStateException("Unknown pizza size= " + size);
        }
        return pizzaPriceCalculator;
    }
}
