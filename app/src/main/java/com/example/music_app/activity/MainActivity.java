package com.example.music_app.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.WindowManager;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.music_app.fragments.HomeFragment;
import com.example.music_app.fragments.ProfileFragment;
import com.example.music_app.R;
import com.example.music_app.fragments.SearchFragment;
import com.example.music_app.fragments.TracksFragment;
import com.example.music_app.data.api.auth.AuthManager;
import com.example.music_app.data.api.auth.SessionManager;
import com.example.music_app.data.api.auth.response.AuthCheckResponse;
import com.example.music_app.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private AuthManager authManager;
    private ActivityMainBinding binding;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        authManager = new AuthManager(this);
        sessionManager = new SessionManager(this);

        authManager.checkAuth(new AuthManager.AuthCheckCallback() {
            @Override
            public void onAuthSuccess(AuthCheckResponse response) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    getWindow().setDecorFitsSystemWindows(false);
                    getWindow().getInsetsController().hide(WindowInsets.Type.statusBars() | WindowInsets.Type.navigationBars());
                    getWindow().getInsetsController().setSystemBarsBehavior(
                            WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
                } else {
                    getWindow().setFlags(
                            WindowManager.LayoutParams.FLAG_FULLSCREEN,
                            WindowManager.LayoutParams.FLAG_FULLSCREEN
                    );
                }

                setContentView(binding.getRoot());
                resetTabs();
                loadFragment(new HomeFragment());
                binding.btnHome.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.selected_icon_color));

            }

            @Override
            public void onAuthFailed() {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });


        binding.btnHome.setOnClickListener(v -> {
            selectTab(binding.btnHome);
            loadFragment(new HomeFragment());
        });
        binding.btnSearch.setOnClickListener(v -> {
            selectTab(binding.btnSearch);
            loadFragment(new SearchFragment());
        });
        binding.btnMusic.setOnClickListener(v -> {
            selectTab(binding.btnMusic);
            loadFragment(new TracksFragment());
        });
        binding.btnProfile.setOnClickListener(v -> {
            selectTab(binding.btnProfile);
            loadFragment(new ProfileFragment());
        });
    }

    public void selectTab(ImageButton selectedTab) {
        resetTabs();
        selectedTab.setColorFilter(ContextCompat.getColor(this, R.color.selected_icon_color));
    }

    private void resetTabs() {
        binding.btnHome.setColorFilter(ContextCompat.getColor(this, R.color.unselected_icon_color));
        binding.btnSearch.setColorFilter(ContextCompat.getColor(this, R.color.unselected_icon_color));
        binding.btnMusic.setColorFilter(ContextCompat.getColor(this, R.color.unselected_icon_color));
        binding.btnProfile.setColorFilter(ContextCompat.getColor(this, R.color.unselected_icon_color));
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}
