package com.example.bankweb.exception.creditProcessException;

public class TokenIsInvalidException extends RuntimeException{
    public TokenIsInvalidException(String message) {
        super(message);
    }
}
