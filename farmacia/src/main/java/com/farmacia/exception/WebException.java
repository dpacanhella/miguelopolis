package com.farmacia.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class WebException extends RuntimeException{
    private static final long serialVersionUID = 3406636182783807331L;

    private final HttpStatus status;
    private final String message;
    
    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
