package com.example.bankweb.exception.userProcessException;

public class UserPasswordInvalidException extends RuntimeException{

    public UserPasswordInvalidException(String msg){
        super(msg);
    }
}
