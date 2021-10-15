package com.app.backend.controller.api.v1;

import com.app.backend.domain.dto.PizzaDto;
import com.app.backend.domain.pizza.Ingredients;
import com.app.backend.domain.pizza.Pizza;
import com.app.backend.domain.pizza.PizzaBuilder;
import com.app.backend.service.PizzaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class PizzaControllerTest {

    MockMvc mockMvc;

    @Mock
    PizzaService pizzaService;

    @InjectMocks
    PizzaController pizzaController;

    private JacksonTester<Pizza> jsonSuperHero;

    @BeforeEach
    void setUp() {
        JacksonTester.initFields(this, new ObjectMapper());
        mockMvc = MockMvcBuilders.standaloneSetup(pizzaController)
                .build();
    }

    @Test
    void createPizza() throws Exception {
        Pizza pizzaUnderTest = new PizzaBuilder()
                .withDough("ITALIAN")
                .withName("under test")
                .withSauce("TOMATO")
                .withSize("LARGE")
                .withIngredient(Ingredients.valueOf("HAM"))
                .withIngredient(Ingredients.valueOf("BACON"))
                .build();

        MockHttpServletResponse response = mockMvc.perform(
                post("http://localhost:8080/api/v1/pizza/create").contentType(MediaType.APPLICATION_JSON).content(
                        jsonSuperHero.write(pizzaUnderTest).getJson()
                )).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void getAllPizzas() throws Exception {
        PizzaDto pizza1 = new PizzaDto();
        PizzaDto pizza2 = new PizzaDto();
        pizza1.setId(1L);
        pizza2.setId(2L);
        List<PizzaDto> pizzaList = List.of(pizza1, pizza2);
        when(pizzaService.findAllPizza()).thenReturn(pizzaList);

        mockMvc.perform(get("/api/v1/pizza/all"))
                .andExpect(status().isOk());

        verify(pizzaService, times(1)).findAllPizza();
    }

    @Test
    void getPizzaById() throws Exception {
        PizzaDto pizza1 = new PizzaDto();
        pizza1.setId(1L);
        when(pizzaService.findPizzaById(anyLong())).thenReturn(pizza1);

        mockMvc.perform(get("/api/v1/pizza/1"))
                .andExpect(status().isOk());

        verify(pizzaService, times(1)).findPizzaById(anyLong());
    }
}