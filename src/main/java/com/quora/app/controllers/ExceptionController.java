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
    public ResponseEntity<?> handleAuthException(AuthException.UserNotFound userNotFound, WebRequest webrequest) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setExceptionMessage(userNotFound.getMessage());
        exceptionResponse.setDateTime(LocalDateTime.now());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }
}
