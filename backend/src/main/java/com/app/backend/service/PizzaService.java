package com.app.backend.service;

import com.app.backend.domain.dto.PizzaDto;
import com.app.backend.domain.pizza.Pizza;
import com.app.backend.domain.pizza.PizzaBuilder;
import com.app.backend.exceptions.NotFoundException;
import com.app.backend.mapper.PizzaMapper;
import com.app.backend.repository.PizzaRepository;
import com.app.backend.service.calculation.PriceCalculator;
import com.app.backend.service.calculation.SwitchCalculationStrategy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PizzaService {
    private final PizzaMapper mapper;
    private final PizzaRepository pizzaRepository;

    @Transactional
    public Pizza createPizza(Pizza pizza) {
        PriceCalculator priceCalculator = new PriceCalculator(new SwitchCalculationStrategy());

        Pizza createdPizza = new Pizza(pizza.getName(), pizza.getDough(), pizza.getSauce(),
                pizza.getSize(), pizza.getIngredientsList());

        pizzaRepository.save(createdPizza);

        Pizza neededPizzaForPriceUpdate = pizzaRepository.findPizzaByName(createdPizza.getName())
                .orElseThrow(() -> new NotFoundException());

        int price = priceCalculator.calculatePrice(createdPizza);

        pizzaRepository.updatePizzaPrice(price, neededPizzaForPriceUpdate.getId());


        return neededPizzaForPriceUpdate;

    }

    public List<PizzaDto> findAllPizza() {
        List<PizzaDto> pizzaDtos = pizzaRepository.findAll().stream().map(pizza -> mapper.pizzaToPizzaDto(pizza)).collect(Collectors.toList());
        return pizzaDtos;
    }

    public PizzaDto findPizzaById(Long id) {
        Pizza wantedPizza = pizzaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());

        return mapper.pizzaToPizzaDto(wantedPizza);
    }

    public void deletePizzaById(Long id) {
        pizzaRepository.deleteById(id);
    }
}
