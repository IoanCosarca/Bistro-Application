package com.ntt.bistroapplication.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {MissingIngredientException.class, NonexistentProductException.class})
    public ResponseEntity<Object> handleNotFoundException(Exception exception, WebRequest request)
    {
        StringBuilder bodyOfResponse = new StringBuilder();
        bodyOfResponse.append(HttpStatus.NOT_FOUND);
        bodyOfResponse.append('\n');
        bodyOfResponse.append(exception.getMessage());
        return handleExceptionInternal(exception, bodyOfResponse, new HttpHeaders(),
                HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = InvalidInputException.class)
    public ResponseEntity<Object> handleInvalidInputException(Exception exception,
                                                              WebRequest request)
    {
        StringBuilder bodyOfResponse = new StringBuilder();
        bodyOfResponse.append(HttpStatus.BAD_REQUEST);
        bodyOfResponse.append('\n');
        bodyOfResponse.append(exception.getMessage());
        return handleExceptionInternal(exception, bodyOfResponse, new HttpHeaders(),
                HttpStatus.BAD_REQUEST, request);
    }
}
