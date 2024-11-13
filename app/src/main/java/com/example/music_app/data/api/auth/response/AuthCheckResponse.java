package com.example.music_app.data.api.auth.response;

import com.example.music_app.data.model.User;

public class AuthCheckResponse {
    private boolean isAuthenticated;
    private User user;

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public User getUser() {
        return user;
    }
}
