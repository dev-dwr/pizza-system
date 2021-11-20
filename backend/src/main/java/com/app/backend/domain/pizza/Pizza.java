package com.app.backend.domain.pizza;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pizza_generator")
    @SequenceGenerator(name = "pizza_generator", sequenceName = "pizza_seq", allocationSize = 1)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Dough dough;
    @Enumerated(EnumType.STRING)
    private Sauce sauce;
    @Enumerated(EnumType.STRING)
    private Size size;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Ingredients.class)
    private List<Ingredients> ingredientsList;

    private int price;

    public Pizza(String name, Dough dough, Sauce sauce, Size size, List<Ingredients> ingredientsList) {
        this.name = name;
        this.dough = dough;
        this.sauce = sauce;
        this.size = size;
        this.ingredientsList = ingredientsList;
    }

    public static PizzaBuilder builder(){
        return new PizzaBuilder();
    }


}
