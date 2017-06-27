package com.farmacia.exception;

import org.springframework.http.HttpStatus;

public class FarmaciaException {
        
    public static final WebException LOGIN_NOT_FOUND = new WebException(HttpStatus.PRECONDITION_FAILED, "exception.Login.notFound");
    
    private FarmaciaException() {
    	
    }
    
}
