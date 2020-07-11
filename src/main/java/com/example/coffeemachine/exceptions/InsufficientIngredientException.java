package com.example.coffeemachine.exceptions;

public class InsufficientIngredientException extends CoffeeMachineException {
    public InsufficientIngredientException(String message) {
        super(message);
    }
}
