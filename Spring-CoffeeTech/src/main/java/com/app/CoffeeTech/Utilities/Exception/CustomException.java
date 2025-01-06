package com.app.CoffeeTech.Utilities.Exception;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException{
    private HttpStatus status;

    public CustomException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}