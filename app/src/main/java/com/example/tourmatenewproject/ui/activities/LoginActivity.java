package com.example.tourmatenewproject.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.tourmatenewproject.databinding.ActivityLoginBinding;
import com.example.tourmatenewproject.db.TourEventsDatabase;
import com.example.tourmatenewproject.entities.UserModel;
import com.example.tourmatenewproject.viewmodels.UserViewModel;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private UserViewModel viewModel;
    private UserModel user;
    //private FirebaseAuth mAuth;
    //private androidx.appcompat.app.AlertDialog.Builder alertDialogBuilder;
    //private androidx.appcompat.app.AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.e("TAG", "onCreate");

        viewModel = new ViewModelProvider(this).get(UserViewModel.class);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //mAuth = FirebaseAuth.getInstance();


        binding.tvForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, PasswordResetActivity.class));
                finish();
            }
        });

        binding.btnUserLogin.setOnClickListener(view -> {
            userLogin();
        });

        binding.tvSignUp.setOnClickListener(view -> startActivity(new Intent(LoginActivity.this, UserSignUpActivity.class)));

    }


    private void userLogin() {
        String email = binding.etUserEmail.getText().toString().trim();
        String password = binding.etUserEmailPass.getText().toString().trim();

        //checking the validity of the email
        if (email.isEmpty()) {
            binding.etUserEmail.requestFocus();
            Toast.makeText(this, "Please enter your Email", Toast.LENGTH_LONG).show();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Please enter a valid  Email", Toast.LENGTH_LONG).show();
            binding.etUserEmail.requestFocus();
            return;
        }

        //checking the validity of  password
        if (password.isEmpty()) {
            Toast.makeText(this, "Enter a password", Toast.LENGTH_LONG).show();
            binding.etUserEmail.requestFocus();
            return;
        }
        if (password.length() < 6) {
            Toast.makeText(this, "Password length should be at least 6", Toast.LENGTH_LONG).show();
            //binding.etEmailPassword.setError("minimum length at least 6");
            binding.etUserEmail.requestFocus();
            return;
        }


        boolean isUserExist = TourEventsDatabase.getDb(this).getSignUpDao().isUserRegistered(email);

        //Check user exists or not. If exists then login successfully otherwise doesn't exist-----------------
        if (isUserExist) {

            viewModel.getUserEmail(email).observe(this, new Observer<UserModel>() {
                @Override
                public void onChanged(UserModel user) {

                    if (!user.getUserPassword().equals(password)) {
                        Toast.makeText(LoginActivity.this, "Wrong password", Toast.LENGTH_SHORT).show();

                    } else {
                        LoginActivity.this.user = user;
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("user", user);
                        startActivity(intent);
                        Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_LONG).show();
                        finish();
                    }
                }
            });

        } else {
            Toast.makeText(LoginActivity.this, "User is not Registered", Toast.LENGTH_LONG).show();
        }


       /* mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    binding.etUserEmail.setText("");
                    binding.etUserEmailPass.setText("");
                    finish();

                } else {
                    Toast.makeText(LoginActivity.this, "Login unsuccessful", Toast.LENGTH_SHORT).show();
                }

            }
        });*/
    }
}