package com.app.backend.domain.pizza;

import javax.persistence.*;
import java.util.List;

@Entity
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

    private float price = 0;

    public Pizza() {
    }

    public Pizza(String name, Dough dough, Sauce sauce, Size size, List<Ingredients> ingredientsList) {
        this.name = name;
        this.dough = dough;
        this.sauce = sauce;
        this.size = size;
        this.ingredientsList = ingredientsList;
    }


    public String getName() {
        return name;
    }

    public Dough getDough() {
        return dough;
    }

    public Sauce getSauce() {
        return sauce;
    }

    public Size getSize() {
        return size;
    }

    public List<Ingredients> getIngredientsList() {
        return ingredientsList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDough(Dough dough) {
        this.dough = dough;
    }

    public void setSauce(Sauce sauce) {
        this.sauce = sauce;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public void setIngredientsList(List<Ingredients> ingredientsList) {
        this.ingredientsList = ingredientsList;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "name='" + name + '\'' +
                ", dough=" + dough +
                ", sauce=" + sauce +
                ", size=" + size +
                ", ingredientsList=" + ingredientsList +
                '}';
    }
}
