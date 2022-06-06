package com.example.tourmatenewproject.ui;


import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tourmatenewproject.databinding.ActivitySplashScreenBinding;


public class SplashScreen extends AppCompatActivity {

    private ActivitySplashScreenBinding binding;
    private int progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //progressBar.setScaleY(3f);

        Thread thread = new Thread(() -> {
            doWork();
            startActivity(new Intent(SplashScreen.this, LoginActivity.class));
            finish();

        });

        thread.start();

    }

    private void doWork() {

        for (progress = 20; progress <= 100; progress += 20) {
            try {
                Thread.sleep(300);
                binding.progressBar.setProgress(progress);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }


}