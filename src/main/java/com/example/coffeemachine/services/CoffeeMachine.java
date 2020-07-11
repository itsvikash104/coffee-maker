package com.example.coffeemachine.services;

import com.example.coffeemachine.exceptions.CoffeeMachineException;
import com.example.coffeemachine.exceptions.InsufficientIngredientException;
import com.example.coffeemachine.models.Beverage;
import com.example.coffeemachine.models.Ingredient;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CoffeeMachine {
    private final List<Beverage> beverages;
    private final Map<Ingredient, Integer> availableIngredientCountMap;
    private final ExecutorService executor;
    private static final Integer BEVERAGE_MAKING_TIME_IN_MS = 2000;


    public CoffeeMachine(List<Beverage> beverages, Map<Ingredient, Integer> availableIngredientCountMap, Integer outletCount) {
        this.beverages = beverages;
        this.availableIngredientCountMap = availableIngredientCountMap;
        this.executor = Executors.newFixedThreadPool(outletCount, runnable -> {
            Thread thread = new Thread(runnable);
            thread.setUncaughtExceptionHandler((thread1, throwable) -> System.out.println(throwable.getMessage()));
            return thread;
        });

    }

    public void handleOrder(String beverageName) {
        try {
            executor.execute(() -> makeOrder(beverageName));
        } catch (CoffeeMachineException e) {
            System.out.println(Thread.currentThread().getName() + e.getMessage());
        }
    }

    private void makeOrder(String beverageName) {
        Beverage orderedBeverage = findBeverageByName(beverageName);
        try {
            synchronized (availableIngredientCountMap) {
                if(hasEnoughIngredients(orderedBeverage.getNeededIngredients())) {
                    decreaseIngredients(orderedBeverage.getNeededIngredients());
//                    Thread.sleep(BEVERAGE_MAKING_TIME_IN_MS);
                    System.out.println(String.format("%s is prepared", beverageName));
                }
            }
        } catch (InsufficientIngredientException e) {
            throw new CoffeeMachineException(String.format("%s cannot be prepared because %s", beverageName, e.getMessage()));
//        } catch (InterruptedException e) {
//            throw new CoffeeMachineException(String.format("%s beverage couldn't be prepared due to unknown reason", beverageName));
        }


    }

    private Beverage findBeverageByName(final String beverageName) {
        Optional<Beverage> orderedBeverageOpt = beverages.stream().filter(beverage -> beverage.getName().equals(beverageName)).findFirst();
        if(orderedBeverageOpt.isPresent()) {
            return orderedBeverageOpt.get();
        }
        throw new CoffeeMachineException(String.format("Invalid beverage %s ordered!", beverageName));
    }

    private boolean hasEnoughIngredients(final Map<Ingredient, Integer> requiredIngredientCountMap) {
        for(Map.Entry<Ingredient, Integer> requiredCountMapEntry : requiredIngredientCountMap.entrySet()) {
            Ingredient requiredIngredient = requiredCountMapEntry.getKey();
            if(availableIngredientCountMap.containsKey(requiredIngredient)) {
                Integer availableIngredientCount = availableIngredientCountMap.get(requiredIngredient);
                if(availableIngredientCount < requiredCountMapEntry.getValue()) {
                    throw new InsufficientIngredientException(String.format("%s is not sufficient", requiredIngredient.getName()));
                }
            }
            else {
                throw new InsufficientIngredientException(String.format("%s is not available", requiredIngredient.getName()));
            }
        }
        return true;
    }

    private void decreaseIngredients(final Map<Ingredient, Integer> requiredIngredientCountMap) {
        for(Map.Entry<Ingredient, Integer> requiredCountMapEntry : requiredIngredientCountMap.entrySet()) {
            availableIngredientCountMap.replace(requiredCountMapEntry.getKey(), availableIngredientCountMap.get(requiredCountMapEntry.getKey()) - requiredCountMapEntry.getValue());
        }
    }

    public void increaseIngredientCounts(final Map<Ingredient, Integer> additionalIngredientCountMap) {
        synchronized (availableIngredientCountMap) {
            additionalIngredientCountMap.forEach(((ingredient, increaseCount) -> {
                if(availableIngredientCountMap.containsKey(ingredient)) {
                    availableIngredientCountMap.replace(ingredient, availableIngredientCountMap.get(ingredient) + increaseCount);
                }
                else {
                    availableIngredientCountMap.put(ingredient, increaseCount);
                }
            }));
        }
    }

    public void turnOff() {
        if(!executor.isTerminated()) {
            try {
                executor.shutdown();
                System.out.flush();
                executor.awaitTermination(10, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
