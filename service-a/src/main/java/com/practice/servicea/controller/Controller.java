package com.practice.servicea.controller;

import com.practice.servicea.InvalidEmailException;
import com.practice.servicea.User;
import com.practice.servicea.UserCreationDto;
import com.practice.servicea.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class Controller {
    private final UserService service;

    @GetMapping()
    public String get() {
        return "Requested";
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody UserCreationDto user) {
        try {
            service.register(user);
        } catch (InvalidEmailException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
