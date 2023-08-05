package com.ntt.bistroapplication.service;

public class MissingIngredientException extends RuntimeException {
    public MissingIngredientException(String message) {
        super(message);
    }
}
