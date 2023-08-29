package com.ntt.bistroapplication.exception;

import org.springframework.http.HttpStatus;

import java.util.List;

public class ResponseBody {
    private HttpStatus status;
    private String message;
    private List<String> errors;

    public ResponseBody(HttpStatus status, String message)
    {
        this.status = status;
        this.message = message;
    }

    public ResponseBody(HttpStatus status, String message, List<String> errors)
    {
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
