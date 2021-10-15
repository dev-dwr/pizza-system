package com.app.backend.repository;

import com.app.backend.domain.pizza.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
@DataJpaTest
class PizzaRepositoryTest {
    @Autowired
    private PizzaRepository pizzaRepository;

    @BeforeEach
    void initUseCase() {

        Pizza pizza1 = new Pizza();
        pizza1.setDough(Dough.AMERICAN);
        pizza1.setId(1L);
        pizza1.setName("under test");
        pizza1.setSauce(Sauce.CHEESE);
        pizza1.setSize(Size.LARGE);
        pizza1.setIngredientsList(Arrays.asList(Ingredients.valueOf("HAM"), Ingredients.valueOf("BACON")));


        Pizza pizza2 = new Pizza();
        pizza1.setDough(Dough.AMERICAN);
        pizza1.setId(2L);
        pizza1.setName("test");
        pizza1.setSauce(Sauce.CHEESE);
        pizza1.setSize(Size.LARGE);
        pizza1.setIngredientsList(Arrays.asList(Ingredients.valueOf("HAM"), Ingredients.valueOf("BACON")));

        List<Pizza> pizzas = Arrays.asList(
               pizza1,
                pizza2
        );
        pizzaRepository.saveAll(pizzas);
    }

    @Test
    void findPizzaByName() {
        String name = "test";
        Optional<Pizza> pizzaUnderTest = pizzaRepository.findPizzaByName(name);
        assertThat(pizzaUnderTest.get().getName()).isEqualTo("test");
    }

    @Test
    void updatePizzaPrice() {
        int price = 1;
        Long id = 1L;
        int updatePrice = pizzaRepository.updatePizzaPrice(price, id);

        assertThat(updatePrice).isEqualTo(price);
    }
}