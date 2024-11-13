package com.example.music_app.data.api.auth;

import android.content.Context;
import android.widget.Toast;

import com.example.music_app.data.api.RetrofitInstance;
import com.example.music_app.data.api.auth.response.AuthCheckResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthManager {

    private SessionManager sessionManager;
    private Context context;

    public AuthManager(Context context) {
        this.context = context;
        this.sessionManager = new SessionManager(context);
    }

    public void checkAuth(final AuthCheckCallback callback) {
        String token = sessionManager.getToken();
        if (token == null) {
            callback.onAuthFailed();
            return;
        }

        RetrofitInstance.getApi().checkAuth("Bearer " + token).enqueue(new Callback<AuthCheckResponse>() {
            @Override
            public void onResponse(Call<AuthCheckResponse> call, Response<AuthCheckResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().isAuthenticated()) {
                    callback.onAuthSuccess(response.body());
                } else {
                    callback.onAuthFailed();
                }
            }

            @Override
            public void onFailure(Call<AuthCheckResponse> call, Throwable t) {
                Toast.makeText(context, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                callback.onAuthFailed();
            }
        });
    }


    public interface AuthCheckCallback {
        void onAuthSuccess(AuthCheckResponse response);
        void onAuthFailed();
    }
}
