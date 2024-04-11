package com.example.decode_lab.SQL;

public class User {
    private int id;
    private String username;
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/decode_lab_base";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "123";

    private String password;
    private int role;

    public User(int id, String username, String password, int role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public int getRole() {
        return role;
    }

    // Геттеры и сеттеры
}