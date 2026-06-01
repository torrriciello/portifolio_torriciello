package com.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.domain.user.RegisterUser;
import com.api.domain.user.User;
import com.api.domain.user.UserService;

import jakarta.validation.Valid;

/**
 * Controller responsible for user management operations.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Registers a new user in the system.
     * 
     * @param data Validated DTO containing the registration details (e.g.,
     *             username, password).
     * @return ResponseEntity containing the created User and the HTTP 201 Created
     *         status.
     */
    @PostMapping("register")
    public ResponseEntity<User> register(@RequestBody @Valid RegisterUser data) {
        // Delegates the persistence and any business rules (like password hashing) to
        // the Service layer
        User newUser = userService.save(data);

        // Returns the created entity with the appropriate 201 Created status code
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
}