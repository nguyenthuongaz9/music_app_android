package com.example.music_app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.music_app.data.api.RetrofitInstance;
import com.example.music_app.data.api.auth.dto.AuthRegisterPayloadDto;
import com.example.music_app.data.api.auth.response.AuthResponse;
import com.example.music_app.databinding.ActivityRegisterBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.loginTextView.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

        binding.loginAccountTextView.setOnClickListener(v -> {
            String email = binding.emailEditText.getText().toString();
            String name = binding.nameEditText.getText().toString();
            String password = binding.passwordEditText.getText().toString();
            register(email, name, password);
        });
    }

    private void register(String email, String name, String password) {
        AuthRegisterPayloadDto payload = new AuthRegisterPayloadDto(email, name, password);
        RetrofitInstance.getApi().register(payload).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(RegisterActivity.this, "Registration successful!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegisterActivity.this, "Registration failed!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
