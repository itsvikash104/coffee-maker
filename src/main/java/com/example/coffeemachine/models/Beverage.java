package com.example.coffeemachine.models;

import java.util.Map;

public class Beverage {
    private final String name;
    private final Map<Ingredient, Integer> neededIngredients;

    public Beverage(String name, Map<Ingredient, Integer> neededIngredients) {
        this.name = name;
        this.neededIngredients = neededIngredients;
    }

    public String getName() {
        return name;
    }

    public Map<Ingredient, Integer> getNeededIngredients() {
        return neededIngredients;
    }
}
