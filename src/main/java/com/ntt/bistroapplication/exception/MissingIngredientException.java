package com.ntt.bistroapplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MissingIngredientException extends RuntimeException {
    public MissingIngredientException() {
        super();
    }

    public MissingIngredientException(String message) {
        super(message);
    }
}
