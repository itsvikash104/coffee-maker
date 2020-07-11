package com.example.coffeemachine.applications;

import com.example.coffeemachine.models.Beverage;
import com.example.coffeemachine.models.Ingredient;
import com.example.coffeemachine.services.CoffeeMachine;

import java.util.*;

public class CoffeeMachineBasic {

    private static final String GINGER_TEA = "ginger tea";
    private static final String ELAICHI_TEA = "elaichi tea";
    private static final String COFFEE = "coffee";
    private static final String HOT_MILK = "hot milk";
    private static final String HOT_WATER = "hot water";
    private static final String TEA_LEAVES_SYRUP = "tea leaves syrup";
    private static final String GINGER_SYRUP = "ginger syrup";
    private static final String SUGAR_SYRUP = "sugar syrup";
    private static final String ELAICHI_SYRUP = "elaichi syrup";
    private static final String COFFEE_SYRUP = "coffee syrup";

    public static void mainTest() throws Exception{

        List<Beverage> beverages = new ArrayList<>();

        Map<Ingredient, Integer> greenTeaIngredients = new HashMap<>();
        greenTeaIngredients.put(new Ingredient(HOT_WATER), 50);
        greenTeaIngredients.put(new Ingredient(HOT_MILK), 10);
        greenTeaIngredients.put(new Ingredient(TEA_LEAVES_SYRUP), 10);
        greenTeaIngredients.put(new Ingredient(GINGER_SYRUP), 5);
        greenTeaIngredients.put(new Ingredient(SUGAR_SYRUP), 10);
        Beverage greenTea = new Beverage(GINGER_TEA, greenTeaIngredients);
        beverages.add(greenTea);

        Map<Ingredient, Integer> elaichiTeaIngredients = new HashMap<>();
        elaichiTeaIngredients.put(new Ingredient(HOT_WATER), 50);
        elaichiTeaIngredients.put(new Ingredient(HOT_MILK), 10);
        elaichiTeaIngredients.put(new Ingredient(TEA_LEAVES_SYRUP), 10);
        elaichiTeaIngredients.put(new Ingredient(ELAICHI_SYRUP), 5);
        elaichiTeaIngredients.put(new Ingredient(SUGAR_SYRUP), 10);
        Beverage elaichiTea = new Beverage(ELAICHI_TEA, elaichiTeaIngredients);
        beverages.add(elaichiTea);

        Map<Ingredient, Integer> coffeeIngredients = new HashMap<>();
        coffeeIngredients.put(new Ingredient(HOT_WATER), 50);
        coffeeIngredients.put(new Ingredient(HOT_MILK), 10);
        coffeeIngredients.put(new Ingredient(COFFEE_SYRUP), 10);
        coffeeIngredients.put(new Ingredient(SUGAR_SYRUP), 10);
        Beverage coffee = new Beverage(COFFEE, coffeeIngredients);
        beverages.add(coffee);

        Map<Ingredient, Integer> hotMilkIngredients = new HashMap<>();
        hotMilkIngredients.put(new Ingredient(HOT_MILK), 50);
        Beverage hotMilk = new Beverage(HOT_MILK, hotMilkIngredients);
        beverages.add(hotMilk);

        Map<Ingredient, Integer> hotWaterIngredients = new HashMap<>();
        hotWaterIngredients.put(new Ingredient(HOT_WATER), 50);
        Beverage hotWater = new Beverage(HOT_WATER, hotWaterIngredients);
        beverages.add(hotWater);

        Map<Ingredient, Integer> availableIngredientCountMap = new HashMap<>();
        availableIngredientCountMap.put(new Ingredient(HOT_WATER), 500);
        availableIngredientCountMap.put(new Ingredient(HOT_MILK), 500);
        availableIngredientCountMap.put(new Ingredient(TEA_LEAVES_SYRUP), 100);
        availableIngredientCountMap.put(new Ingredient(ELAICHI_SYRUP), 100);
        availableIngredientCountMap.put(new Ingredient(SUGAR_SYRUP), 100);
        availableIngredientCountMap.put(new Ingredient(GINGER_SYRUP), 100);
        availableIngredientCountMap.put(new Ingredient(COFFEE_SYRUP), 100);

        Integer outletCount = 5;

        CoffeeMachine coffeeMachine = new CoffeeMachine(beverages, availableIngredientCountMap, outletCount);

        coffeeMachine.handleOrder(HOT_MILK);
        coffeeMachine.handleOrder(HOT_WATER);
        coffeeMachine.handleOrder(COFFEE);
        coffeeMachine.handleOrder(ELAICHI_TEA);
        coffeeMachine.handleOrder(GINGER_TEA);
        coffeeMachine.handleOrder(COFFEE);
        coffeeMachine.handleOrder(HOT_MILK);
        coffeeMachine.handleOrder(COFFEE);
        coffeeMachine.handleOrder(GINGER_TEA);
        coffeeMachine.handleOrder(HOT_WATER);
        coffeeMachine.handleOrder(ELAICHI_TEA);
        coffeeMachine.handleOrder(GINGER_TEA);
        coffeeMachine.handleOrder(COFFEE);
        coffeeMachine.handleOrder(HOT_MILK);
        coffeeMachine.handleOrder(ELAICHI_TEA);
        coffeeMachine.handleOrder(GINGER_TEA);
        coffeeMachine.handleOrder(COFFEE);
        coffeeMachine.handleOrder(HOT_MILK);
        coffeeMachine.handleOrder(COFFEE);
        coffeeMachine.handleOrder(GINGER_TEA);
        coffeeMachine.handleOrder(HOT_WATER);
        coffeeMachine.handleOrder(COFFEE);
        coffeeMachine.handleOrder(GINGER_TEA);
        coffeeMachine.handleOrder(HOT_WATER);
        coffeeMachine.handleOrder(ELAICHI_TEA);
        coffeeMachine.handleOrder(COFFEE);
        coffeeMachine.handleOrder(HOT_MILK);
        coffeeMachine.handleOrder(COFFEE);
        coffeeMachine.handleOrder(GINGER_TEA);
        coffeeMachine.handleOrder(HOT_WATER);
        coffeeMachine.handleOrder(ELAICHI_TEA);
        coffeeMachine.handleOrder(GINGER_TEA);
        coffeeMachine.handleOrder(COFFEE);
        coffeeMachine.handleOrder(HOT_MILK);

        Thread.sleep(5000);

        System.out.println("=======================AFTER SLEEP=========================");

        Map<Ingredient, Integer> increaseIngredientCountMap = new HashMap<>();
        availableIngredientCountMap.put(new Ingredient(HOT_WATER), 500);
        availableIngredientCountMap.put(new Ingredient(HOT_MILK), 500);
        availableIngredientCountMap.put(new Ingredient(TEA_LEAVES_SYRUP), 100);
        availableIngredientCountMap.put(new Ingredient(ELAICHI_SYRUP), 100);
        availableIngredientCountMap.put(new Ingredient(SUGAR_SYRUP), 100);
        availableIngredientCountMap.put(new Ingredient(GINGER_SYRUP), 100);
        availableIngredientCountMap.put(new Ingredient(COFFEE_SYRUP), 100);

        coffeeMachine.increaseIngredientCounts(increaseIngredientCountMap);

        coffeeMachine.handleOrder(HOT_MILK);
        coffeeMachine.handleOrder(HOT_WATER);
        coffeeMachine.handleOrder(COFFEE);
        coffeeMachine.handleOrder(ELAICHI_TEA);
        coffeeMachine.handleOrder(GINGER_TEA);
        coffeeMachine.handleOrder(COFFEE);
        coffeeMachine.handleOrder(HOT_MILK);
        coffeeMachine.handleOrder(COFFEE);
        coffeeMachine.handleOrder(GINGER_TEA);
        coffeeMachine.handleOrder(HOT_WATER);
        coffeeMachine.handleOrder(ELAICHI_TEA);
        coffeeMachine.handleOrder(GINGER_TEA);
        coffeeMachine.handleOrder(COFFEE);
        coffeeMachine.handleOrder(HOT_MILK);
        coffeeMachine.handleOrder(ELAICHI_TEA);
        coffeeMachine.handleOrder(GINGER_TEA);
        coffeeMachine.handleOrder(COFFEE);
        coffeeMachine.handleOrder(HOT_MILK);
        coffeeMachine.handleOrder(COFFEE);
        coffeeMachine.handleOrder(GINGER_TEA);
        coffeeMachine.handleOrder(HOT_WATER);
        coffeeMachine.handleOrder(COFFEE);
        coffeeMachine.handleOrder(GINGER_TEA);
        coffeeMachine.handleOrder(HOT_WATER);
        coffeeMachine.handleOrder(ELAICHI_TEA);
        coffeeMachine.handleOrder(COFFEE);
        coffeeMachine.handleOrder(HOT_MILK);
        coffeeMachine.handleOrder(COFFEE);
        coffeeMachine.handleOrder(GINGER_TEA);
        coffeeMachine.handleOrder(HOT_WATER);
        coffeeMachine.handleOrder(ELAICHI_TEA);
        coffeeMachine.handleOrder(GINGER_TEA);
        coffeeMachine.handleOrder(COFFEE);
        coffeeMachine.handleOrder(HOT_MILK);

//        Thread.sleep(5000);

        coffeeMachine.turnOff();

    }
}
