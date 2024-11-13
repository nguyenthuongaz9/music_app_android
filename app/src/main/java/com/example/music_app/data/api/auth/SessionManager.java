package com.example.music_app.data.api.auth;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static final String PREF_NAME = "user_session";
    private static final String IS_LOGGED_IN = "is_logged_in";
    private static final String TOKEN_KEY = "auth_token";

    public SessionManager(Context context){
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveLoginStatus(String token){
        editor.putBoolean(IS_LOGGED_IN, true);
        editor.putString(TOKEN_KEY, token);
        editor.apply();
    }

    public String getToken(){
        return sharedPreferences.getString(TOKEN_KEY, null);
    }

    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(IS_LOGGED_IN, false);
    }



    public void logout() {
        editor.clear();
        editor.apply();
    }

    public void clearSession() {
        editor.clear().apply();
    }

}
