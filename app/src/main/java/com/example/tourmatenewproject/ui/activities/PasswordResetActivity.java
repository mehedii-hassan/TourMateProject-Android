package com.example.tourmatenewproject.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.tourmatenewproject.databinding.ActivityPasswordResetBinding;
import com.example.tourmatenewproject.db.TourEventsDatabase;
import com.example.tourmatenewproject.entities.UserModel;
import com.example.tourmatenewproject.viewmodels.UserViewModel;

public class PasswordResetActivity extends AppCompatActivity {

    private ActivityPasswordResetBinding binding;
    private UserViewModel userViewModel;
    private UserModel user;


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
            return;
        }
        if (password.length() < 6) {
            Toast.makeText(this, "Password length should be at least 6", Toast.LENGTH_SHORT).show();
            //binding.etEmailPassword.setError("minimum length at least 6");
            binding.etPasswordFP.requestFocus();
            return;
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
            return;
        }
        boolean isUserExist = TourEventsDatabase.getDb(this).getSignUpDao().isUserRegistered(email);

        Log.e("TAG", "isUserExist " + isUserExist);
        UserModel user = TourEventsDatabase.getDb(this).getSignUpDao().getUser(email);

        //Check user exists  or not.If exists then successfully reset password otherwise doesn't exist----------------
        if (isUserExist) {
            if (user != null) {
                int userId = user.getUserId();
                String name = user.getUserName();
                final UserModel userModel = new UserModel(userId, name, email, password, conPassword);
                userViewModel.updateUser(userModel);
                Toast.makeText(PasswordResetActivity.this, "Successfully Password Reset ", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(PasswordResetActivity.this, LoginActivity.class));
                finish();
            }

            /*userViewModel.getUserEmail(email).observe(this, new Observer<UserModel>() {
                @Override
                public void onChanged(UserModel user) {
                    //PasswordResetActivity.this.user = user;
                    int userId = user.getUserId();
                    String name = user.getUserName();
                    final UserModel userModel = new UserModel(userId, name, email, password, conPassword);
                    userViewModel.updateUser(userModel);
                    binding.etEmailFP.setText("");
                    binding.etPasswordFP.setText("");
                    binding.etConfirmPassFP.setText("");
                    Toast.makeText(PasswordResetActivity.this, "Successfully Password Reset ", Toast.LENGTH_SHORT).show();

                }
            });*/

        } else {
            Toast.makeText(PasswordResetActivity.this, "User is not Registered", Toast.LENGTH_LONG).show();
        }
    }
}