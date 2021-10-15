package com.app.backend.mapper;

import com.app.backend.domain.dto.PizzaDto;
import com.app.backend.domain.pizza.Pizza;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface PizzaMapper {
    PizzaMapper INSTANCE = Mappers.getMapper(PizzaMapper.class);

    PizzaDto pizzaToPizzaDto(Pizza pizza);
}
