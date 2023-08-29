package com.ntt.bistroapplication.exception;

import jakarta.validation.constraints.NotNull;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {MissingIngredientException.class, NonexistentProductException.class})
    public ResponseEntity<Object> handleNotFoundException(Exception exception, WebRequest request)
    {
        ResponseBody responseBody = new ResponseBody(HttpStatus.NOT_FOUND, exception.getMessage());
        return handleExceptionInternal(exception, responseBody, new HttpHeaders(),
                responseBody.getStatus(), request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
                                                                  @NotNull HttpHeaders headers,
                                                                  @NotNull HttpStatusCode status,
                                                                  @NotNull WebRequest request)
    {
        var errors = e.getBindingResult().getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        ResponseBody responseBody =
                new ResponseBody(HttpStatus.valueOf(status.value()), "Validation Error", errors);
        return handleExceptionInternal(e, responseBody, headers, responseBody.getStatus(), request);
    }
}
