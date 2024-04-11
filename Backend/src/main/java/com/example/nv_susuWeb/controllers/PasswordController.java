package com.example.nv_susuWeb.controllers;

import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
public class PasswordController {
    private String validPassword = "123"; //pass 123
    private int remainingLives = 3;

    @PostMapping("/checkPassword")
    public String checkPassword(@RequestBody String password) {
        if (remainingLives == 0) {
            return "Нет доступных попыток. Запросите новый одноразовый пароль.";
        } else if (password.equals(validPassword)) {
            validPassword = null;
            return "Успешный вход!";
        } else {
            remainingLives--;
            return "Неверный пароль. Осталось попыток: " + remainingLives;
        }

    }
    @GetMapping("/generateOneTimePassword")
    public String generateOneTimePassword() {
                    Random random = new Random();
            String oneTimePassword = String.valueOf(random.nextInt(10000)); // Генерируем 4-значный пароль
            remainingLives = 3; // Сбрасываем количество попыток
            return oneTimePassword;
    }
    @PostMapping("/setValidPassword")
    public String setValidPassword(@RequestBody String oneTimePassword) {
        validPassword = oneTimePassword;
        remainingLives = 3; // Сбросить количество попыток
        return "Допустимый пароль обновлен.";
    }
    @PostMapping("/setValidPasswordToNull")
    public void setValidPasswordToNull(@RequestBody String newPassword) {
        validPassword = null;
    }
}
