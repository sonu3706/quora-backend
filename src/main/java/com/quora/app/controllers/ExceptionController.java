package com.quora.app.controllers;

import com.quora.app.exceptions.AuthException;
import com.quora.app.exceptions.UserException;
import com.quora.app.models.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserException.UserNotFoundException.class)
    public ResponseEntity<?> handleException(UserException.UserNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AuthException.UserNotFound.class)
    public ResponseEntity<?> handleUserNotFoundException(AuthException.UserNotFound userNotFound, WebRequest webrequest) {
        ExceptionResponse exceptionResponse = createExceptionResponseObject(userNotFound.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AuthException.UserAlreadyExists.class)
    public ResponseEntity<?> handleUserAlreadyExistsException(AuthException.UserAlreadyExists userAlreadyExists, WebRequest webRequest) {
        ExceptionResponse exceptionResponse = createExceptionResponseObject(userAlreadyExists.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);

    }

    @ExceptionHandler(AuthException.PasswordSame.class)
    public ResponseEntity<?> handlePasswordSameException(AuthException.PasswordSame passwordSame, WebRequest webRequest) {
        ExceptionResponse exceptionResponse = createExceptionResponseObject(passwordSame.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
    }

    /* Method to construct ExceptionResponse Object */
    private ExceptionResponse createExceptionResponseObject(String exceptionMessage) {
       return ExceptionResponse.builder().exceptionMessage(exceptionMessage).dateTime(LocalDateTime.now()).build();
    }
}
