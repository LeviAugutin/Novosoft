package com.novosoft.assessment.controller;


import com.novosoft.assessment.entitys.Users;

import com.novosoft.assessment.services.UserDetailsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/novo/users")
public class UserController {

    @Autowired
    private UserDetailsServices userDetailsServices;


    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody Users users)
    {
        userDetailsServices.registerUser(users);
        return ResponseEntity.ok("User created successfully");
    }
}
