package com.example.bankweb.exception.userProcessException;

public class UsernameOrPasswordInvalidException extends RuntimeException{
    public UsernameOrPasswordInvalidException(String message) {
        super(message);
    }
}
