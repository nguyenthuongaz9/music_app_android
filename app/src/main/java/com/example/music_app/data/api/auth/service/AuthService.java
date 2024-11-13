package com.example.music_app.data.api.auth.service;
import com.example.music_app.data.api.auth.response.AuthCheckResponse;
import com.example.music_app.data.api.auth.dto.AuthLoginPayloadDto;
import com.example.music_app.data.api.auth.dto.AuthRegisterPayloadDto;
import com.example.music_app.data.api.auth.response.AuthResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface AuthService {
    @POST("auth/register")
    Call<AuthResponse> register(@Body AuthRegisterPayloadDto payload);
    @POST("auth/login")
    Call<AuthResponse> login(@Body AuthLoginPayloadDto payload);
    @POST("auth/check-auth")
    Call<AuthCheckResponse> checkAuth(@Header("Authorization") String token);
}