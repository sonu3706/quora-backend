package com.quora.app.controllers;

import com.quora.app.models.UserAuth;
import com.quora.app.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Authentication Controller to Register user and login user
 */
@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> createUserAccount(@RequestBody UserAuth userAuth) {
        ResponseEntity<?> responseEntity = null;
        try {
            responseEntity = ResponseEntity.ok(authService.createUserAccount(userAuth));
        } catch (Exception exception) {
            responseEntity = ResponseEntity.status(401).body(exception.getMessage());
        }
        return responseEntity;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> loginUser(@RequestBody UserAuth userAuth) {
        ResponseEntity<?> responseEntity = null;
        try {
            responseEntity = ResponseEntity.ok(authService.loginUser(userAuth));
        } catch (Exception exception) {
            responseEntity = ResponseEntity.status(401).body(exception.getMessage());
        }
        return responseEntity;
    }
}
