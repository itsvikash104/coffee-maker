package com.example.coffeemachine.services;

import com.example.coffeemachine.models.Beverage;
import com.example.coffeemachine.models.Ingredient;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoffeeMachineBuilderFromJSON {

    Integer parseJson(List<Beverage> beverages, Map<Ingredient, Integer> availableIngredientCount, String fileName) throws FileNotFoundException {
        URL jsonUrl = this.getClass().getClassLoader().getResource(fileName);
        FileReader jsonFileReader = new FileReader(jsonUrl.getPath());
        Gson gson = new Gson();

        JsonObject jsonObject = gson.fromJson(jsonFileReader, JsonObject.class);

        JsonObject machine = jsonObject.getAsJsonObject("machine");
        Integer outletCount = machine.getAsJsonObject("outlets").getAsJsonPrimitive("count_n").getAsInt();

        JsonObject totalItemsQuantity = machine.getAsJsonObject("total_items_quantity");
        for(Map.Entry<String, JsonElement> ingredientData : totalItemsQuantity.entrySet()) {
            String ingredientName = ingredientData.getKey();
            Integer ingredientCount = ingredientData.getValue().getAsInt();
            availableIngredientCount.put(new Ingredient(ingredientName), ingredientCount);
        }

        JsonObject beveragesInJson = machine.getAsJsonObject("beverages");
        for(Map.Entry<String, JsonElement> beverageData : beveragesInJson.entrySet()) {
            String beverageName = beverageData.getKey();
            JsonObject beverageIngredientData = beverageData.getValue().getAsJsonObject();
            Map<Ingredient, Integer> beverageIngredientCount = new HashMap<>();
            for(Map.Entry<String, JsonElement> ingredientData : beverageIngredientData.entrySet()) {
                String ingredientName = ingredientData.getKey();
                Integer ingredientCount = ingredientData.getValue().getAsInt();
                beverageIngredientCount.put(new Ingredient(ingredientName), ingredientCount);
            }
            beverages.add(new Beverage(beverageName, beverageIngredientCount));
        }

        return outletCount;

    }

    public static CoffeeMachine fileName(String filename) throws FileNotFoundException {
        CoffeeMachineBuilderFromJSON coffeeMachineBuilderFromJSON = new CoffeeMachineBuilderFromJSON();

        List<Beverage> beverages = new ArrayList<>();
        Map<Ingredient, Integer> availableIngredientCount = new HashMap<>();

        Integer outLetCount = coffeeMachineBuilderFromJSON.parseJson(beverages, availableIngredientCount, filename);

        return new CoffeeMachine(beverages, availableIngredientCount, outLetCount);
    }
}
