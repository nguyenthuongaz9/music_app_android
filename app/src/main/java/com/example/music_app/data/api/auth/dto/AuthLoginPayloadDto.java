package com.example.music_app.data.api.auth.dto;

public class AuthLoginPayloadDto {

    private  String email;
    private String password;
    public AuthLoginPayloadDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
