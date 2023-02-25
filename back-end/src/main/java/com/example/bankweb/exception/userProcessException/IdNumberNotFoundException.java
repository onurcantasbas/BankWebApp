package com.example.bankweb.exception.userProcessException;

public class IdNumberNotFoundException extends RuntimeException{
    public IdNumberNotFoundException(String message) {
        super(message);
    }
}
