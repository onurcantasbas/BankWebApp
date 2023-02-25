package com.example.bankweb.exception.userProcessException;

public class IdNumberDuplicateException extends RuntimeException{
    public IdNumberDuplicateException(String message) {
        super(message);
    }
}
