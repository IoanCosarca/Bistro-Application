package com.ntt.bistroapplication.exception;

public class NonexistentProductException extends RuntimeException {
    public NonexistentProductException(String message) {
        super(message);
    }
}
