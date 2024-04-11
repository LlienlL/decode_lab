package com.example.nv_susuWeb.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @GetMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestParam String password) {
        // Здесь реализуйте проверку пароля, например, сравнив пароль с базой данных
        if ("correctPassword".equals(password)) {
            return ResponseEntity.ok("молодец");
        } else {
            return ResponseEntity.badRequest().body("Неверный пароль");
        }
    }
}