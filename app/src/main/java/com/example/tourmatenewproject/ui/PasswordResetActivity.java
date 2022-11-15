package com.example.tourmatenewproject.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.tourmatenewproject.R;
import com.example.tourmatenewproject.databinding.ActivityPasswordResetBinding;
import com.example.tourmatenewproject.entities.UserModel;
import com.example.tourmatenewproject.viewmodels.UserViewModel;

public class PasswordResetActivity extends AppCompatActivity {

    private ActivityPasswordResetBinding binding;
    private UserViewModel userViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userViewModel = new ViewModelProvider(this)
                .get(UserViewModel.class);
        binding = ActivityPasswordResetBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passwordReset();
            }
        });

        binding.tvLoginFP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PasswordResetActivity.this, LoginActivity.class));
                finish();
            }
        });
    }

    private void passwordReset() {
        String email = binding.etEmailFP.getText().toString().trim();
        String password = binding.etPasswordFP.getText().toString().trim();
        String conPassword = binding.etConfirmPassFP.getText().toString().trim();

        //checking the validity of the email
        if (email.isEmpty()) {
            //binding.etEmailAddress.setError("Enter an email address");
            binding.etEmailFP.requestFocus();
            Toast.makeText(this, "Please enter your Email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            //binding.etEmailAddress.setError("Enter a valid email address");
            binding.etEmailFP.requestFocus();
            Toast.makeText(this, "Please enter a valid  Email", Toast.LENGTH_SHORT).show();
            return;
        }

        //checking the validity of the password
        if (password.isEmpty()) {
            Toast.makeText(this, "Enter a password", Toast.LENGTH_SHORT).show();
            //binding.etEmailPassword.setError("Enter a password");
            binding.etPasswordFP.requestFocus();
        }
        if (password.length() < 6) {
            Toast.makeText(this, "Password length should be at least 6", Toast.LENGTH_SHORT).show();
            //binding.etEmailPassword.setError("minimum length at least 6");
            binding.etPasswordFP.requestFocus();
        }
        //checking the validity of Confirm password
        if (conPassword.isEmpty()) {
            //etConfirmPassword.setError("Enter confirm password");
            Toast.makeText(getApplicationContext(), "Enter confirm password ", Toast.LENGTH_SHORT).show();
            binding.etConfirmPassFP.requestFocus();
            return;
        }
        if (!conPassword.equalsIgnoreCase(password)) {
            //etConfirmPassword.setError("Password not matched");
            Toast.makeText(getApplicationContext(), "Password and confirm password not matched ", Toast.LENGTH_SHORT).show();
            binding.etConfirmPassFP.requestFocus();
        }

        //Check user registered or not----------------
        userViewModel.getUserEmail(email).observe(this, new Observer<UserModel>() {
            @Override
            public void onChanged(UserModel user) {
                if (user != null) {
                    int userId = user.getUserId();
                    String name = user.getUserName();
                    final UserModel userModel = new UserModel(userId, name, email, password, conPassword);
                    userViewModel.updateUser(userModel);
                    /*binding.etEmailFP.setText("");
                    binding.etPasswordFP.setText("");
                    binding.etConfirmPassFP.setText("");*/
                    Toast.makeText(PasswordResetActivity.this, "Successfully Password Reset ", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(PasswordResetActivity.this, "Email is not Registered", Toast.LENGTH_LONG).show();
                }

            }
        });


    }
}