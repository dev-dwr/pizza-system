package com.app.backend.domain.dto;

import com.app.backend.domain.pizza.Dough;
import com.app.backend.domain.pizza.Ingredients;
import com.app.backend.domain.pizza.Sauce;
import com.app.backend.domain.pizza.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class PizzaDto {
    private Long id;
    private String name;
    private Dough dough;
    private Sauce sauce;
    private Size size;
    private int price = 0;
    private List<Ingredients> ingredientsList;
}
