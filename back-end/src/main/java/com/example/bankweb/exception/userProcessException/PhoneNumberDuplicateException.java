package com.example.bankweb.exception.userProcessException;

public class PhoneNumberDuplicateException extends RuntimeException{
    public PhoneNumberDuplicateException(String message) {
        super(message);
    }
}
