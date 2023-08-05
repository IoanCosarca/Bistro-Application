package com.ntt.bistroapplication;

import com.ntt.bistroapplication.service.MissingIngredientException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class BistroApplication {
    public static void main(String[] args) {
        try {
            SpringApplication.run(BistroApplication.class, args);
        }
        catch (MissingIngredientException e) {
            System.out.println(e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
        }
    }
}
