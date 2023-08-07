package com.ntt.bistroapplication.service;

public class NonexistentProductException extends RuntimeException {
    public NonexistentProductException(String message) {
        super(message);
    }
}
