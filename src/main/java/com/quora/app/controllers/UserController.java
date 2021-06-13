package com.quora.app.controllers;

import com.quora.app.models.User;
import com.quora.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        ResponseEntity<?> responseEntity = null;
        try {
            responseEntity = ResponseEntity.status(201).body(userService.saveUser(user));
        } catch (Exception exception) {
            responseEntity = ResponseEntity.status(409).body(exception.getMessage());
        }
        return responseEntity;
    }
}
