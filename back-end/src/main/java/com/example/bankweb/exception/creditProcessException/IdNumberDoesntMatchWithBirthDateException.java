package com.example.bankweb.exception.creditProcessException;

public class IdNumberDoesntMatchWithBirthDateException extends RuntimeException{
    public IdNumberDoesntMatchWithBirthDateException(String message) {
        super(message);
    }
}
