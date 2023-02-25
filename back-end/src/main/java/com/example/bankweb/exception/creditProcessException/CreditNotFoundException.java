package com.example.bankweb.exception.creditProcessException;

public class CreditNotFoundException extends RuntimeException{
    public CreditNotFoundException(String message) {
        super(message);
    }
}
