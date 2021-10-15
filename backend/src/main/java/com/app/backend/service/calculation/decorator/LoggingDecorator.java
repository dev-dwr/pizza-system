package com.app.backend.service.calculation.decorator;

import com.app.backend.domain.pizza.Pizza;
import com.app.backend.service.calculation.PizzaPriceCalculator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggingDecorator implements PizzaPriceCalculator {
    final PizzaPriceCalculator decorated;

    public LoggingDecorator(PizzaPriceCalculator decorated) {
        this.decorated = decorated;
    }

    @Override
    public int calculate(Pizza pizza) {
        log.info("This message is sent from LoggingDecorator");
        return decorated.calculate(pizza);
    }
}
