package com.example.youtube.models;
public class LoginRequest {

    public String userName;

    public String password;

    public LoginRequest(String phoneNumber, String pin) {
        this.userName = phoneNumber;
        this.password = pin;
    }
}


