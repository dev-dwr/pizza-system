package com.app.backend.domain.pizza;

public enum Ingredients {
    HAM(true),
    BACON(true),
    SALAMI(true),
    MUSHROOMS(false),
    TOMATO(false),
    PINEAPPLE(false),
    CHILLI(false),
    ;

    private final boolean meat;

    Ingredients(boolean meat) {
        this.meat = meat;
    }

    public boolean isMeat() {
        return meat;
    }
}
