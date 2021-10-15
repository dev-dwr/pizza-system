package com.app.backend.service;

import com.app.backend.domain.dto.PizzaDto;
import com.app.backend.domain.pizza.Pizza;
import com.app.backend.mapper.PizzaMapper;
import com.app.backend.repository.PizzaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class PizzaServiceTest {

    @InjectMocks
    PizzaService pizzaService;

    @Mock
    PizzaRepository pizzaRepository;

    @Mock
    PizzaMapper pizzaMapper;

    @Test
    void findAllPizza() {
        Pizza pizza1 = new Pizza();
        Pizza pizza2 = new Pizza();
        pizza1.setId(1L);
        pizza2.setId(2L);
        List<Pizza> pizzaList = List.of(pizza1, pizza2);

        when(pizzaRepository.findAll()).thenReturn(pizzaList);

        List<PizzaDto> pizzaTestList = pizzaService.findAllPizza();

        assertThat(pizzaTestList.size()).isEqualTo(2);

        Mockito.verify(pizzaRepository, times(1)).findAll();


    }

    @Test
    void findPizzaById() {
        Pizza pizza1 = new Pizza();
        pizza1.setId(1L);
        Optional<Pizza> pizzaOptional = Optional.of(pizza1);

        when(pizzaRepository.findById(anyLong())).thenReturn(pizzaOptional);

        pizzaService.findPizzaById(anyLong());

        verify(pizzaRepository, times(1)).findById(anyLong());
        verify(pizzaRepository, never()).findAll();

    }
}