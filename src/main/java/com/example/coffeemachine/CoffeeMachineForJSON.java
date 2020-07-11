package com.example.coffeemachine;

import com.example.coffeemachine.services.CoffeeMachine;
import com.example.coffeemachine.services.CoffeeMachineBuilderFromJSON;

import java.io.FileNotFoundException;

public class CoffeeMachineForJSON {

    private static void runAndDestroyCoffeeMachine() throws FileNotFoundException {
        CoffeeMachine coffeeMachine = CoffeeMachineBuilderFromJSON.fileName("coffee-machine-test.json");

        final String HOT_TEA = "hot_tea";
        final String HOT_COFFEE = "hot_coffee";
        final String GREEN_TEA = "green_tea";
        final String BLACK_TEA = "black_tea";

        System.out.println("\n=========================================\n");

        coffeeMachine.handleOrder(HOT_TEA);
        coffeeMachine.handleOrder(HOT_COFFEE);
        coffeeMachine.handleOrder(BLACK_TEA);
        coffeeMachine.handleOrder(GREEN_TEA);



        coffeeMachine.turnOff();
    }

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {

        runAndDestroyCoffeeMachine();
        Thread.sleep(1000);
        runAndDestroyCoffeeMachine();
        Thread.sleep(1000);
        runAndDestroyCoffeeMachine();
        Thread.sleep(1000);
        runAndDestroyCoffeeMachine();
        Thread.sleep(1000);
        runAndDestroyCoffeeMachine();

    }
}
