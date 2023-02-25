package com.example.bankweb.exception.userProcessException;

public class UsernameDuplicateException extends RuntimeException{
    public UsernameDuplicateException(String message) {
        super(message);
    }
}
