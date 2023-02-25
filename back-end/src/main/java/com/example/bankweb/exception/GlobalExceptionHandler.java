package com.example.bankweb.exception;

import com.example.bankweb.exception.creditProcessException.*;
import com.example.bankweb.exception.userProcessException.*;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import java.util.Date;


@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOG = Logger.getLogger(String.valueOf(GlobalExceptionHandler.class));

    @ExceptionHandler(IdNumberNotFoundException.class)
    public ResponseEntity<ErrorObject> entityNotFoundException(IdNumberNotFoundException ex, WebRequest request) {
        ErrorObject message = new ErrorObject(
                HttpStatus.UNAUTHORIZED.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
        LOG.error(ex.getMessage());
        return new ResponseEntity<ErrorObject>(message, HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(IdNumberDuplicateException.class)
    public ResponseEntity<ErrorObject> IdNumberDuplicateException(IdNumberDuplicateException ex, WebRequest request) {
        ErrorObject message = new ErrorObject(
                HttpStatus.CONFLICT.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
        LOG.error(ex.getMessage());
        return new ResponseEntity<ErrorObject>(message, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(IdNumberDoesntMatchWithBirthDateException.class)
    public ResponseEntity<ErrorObject> IdNumberDoesntMatchWithBirthDateException(IdNumberDoesntMatchWithBirthDateException ex, WebRequest request) {
        ErrorObject message = new ErrorObject(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
        LOG.error(ex.getMessage());
        return new ResponseEntity<ErrorObject>(message, HttpStatus.BAD_REQUEST);
    }//ThereIsNoCreditApplicationException


    @ExceptionHandler(CreditNotFoundException.class)
    public ResponseEntity<ErrorObject> CreditNotFoundException(CreditNotFoundException ex, WebRequest request) {
        ErrorObject message = new ErrorObject(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
        LOG.error(ex.getMessage());
        return new ResponseEntity<ErrorObject>(message, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(PhoneNumberInvalidException.class)
    public ResponseEntity<ErrorObject> PhoneNumberInvalidException(PhoneNumberInvalidException ex, WebRequest request) {
        ErrorObject message = new ErrorObject(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
        LOG.error(ex.getMessage());
        return new ResponseEntity<ErrorObject>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserPasswordInvalidException.class)
    public ResponseEntity<ErrorObject> userPasswordFalseException(UserPasswordInvalidException ex, WebRequest request) {
        ErrorObject message = new ErrorObject(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
        LOG.error(ex.getMessage());
        return new ResponseEntity<ErrorObject>(message, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(UsernameIdNumberNotMatchException.class)
    public ResponseEntity<ErrorObject> UsernameIdNumberNotMatchException(UsernameIdNumberNotMatchException ex, WebRequest request) {
        ErrorObject message = new ErrorObject(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
        LOG.error(ex.getMessage());
        return new ResponseEntity<ErrorObject>(message, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(PhoneNumberDuplicateException.class)
    public ResponseEntity<ErrorObject> PhoneNumberDuplicateException(PhoneNumberDuplicateException ex, WebRequest request) {
        ErrorObject message = new ErrorObject(
                HttpStatus.CONFLICT.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
        LOG.error(ex.getMessage());
        return new ResponseEntity<ErrorObject>(message, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(UsernameDuplicateException.class)
    public ResponseEntity<ErrorObject> UsernameDuplicateException(UsernameDuplicateException ex, WebRequest request) {
        ErrorObject message = new ErrorObject(
                HttpStatus.CONFLICT.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
        LOG.error(ex.getMessage());
        return new ResponseEntity<ErrorObject>(message, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UsernameOrPasswordInvalidException.class)
    public ResponseEntity<ErrorObject> UsernameOrPasswordInvalidException(UsernameOrPasswordInvalidException ex, WebRequest request) {
        ErrorObject message = new ErrorObject(
                HttpStatus.UNAUTHORIZED.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
        LOG.error(ex.getMessage());
        return new ResponseEntity<ErrorObject>(message, HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(TokenIsInvalidException.class)
    public ResponseEntity<ErrorObject> TokenIsInvalidException(TokenIsInvalidException ex, WebRequest request) {
        ErrorObject message = new ErrorObject(
                HttpStatus.UNAUTHORIZED.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
        LOG.error(ex.getMessage());
        return new ResponseEntity<ErrorObject>(message, HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorObject> globalExceptionHandler(Exception ex, WebRequest request) {
        ErrorObject message = new ErrorObject(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
        LOG.error(ex.getMessage());
        return new ResponseEntity<ErrorObject>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
