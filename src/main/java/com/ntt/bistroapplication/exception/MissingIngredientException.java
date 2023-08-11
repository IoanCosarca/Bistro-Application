package com.ntt.bistroapplication.exception;

public class MissingIngredientException extends RuntimeException {
    public MissingIngredientException(String message) {
        super(message);
    }
}
