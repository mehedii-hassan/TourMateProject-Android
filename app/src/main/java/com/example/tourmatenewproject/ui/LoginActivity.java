package com.example.tourmatenewproject.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.tourmatenewproject.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private FirebaseAuth mAuth;
    //private androidx.appcompat.app.AlertDialog.Builder alertDialogBuilder;
    //private androidx.appcompat.app.AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();

        binding.btnUserLogin.setOnClickListener(view -> {
            userLogin();
            //startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        });

        binding.tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });
    }

    private void userLogin() {
        String email = binding.etUserEmail.getText().toString().trim();
        String password = binding.etUserEmailPass.getText().toString().trim();


        //checking the validity of the email
        if (email.isEmpty()) {
            //etSignUpUserEmail.setError("Enter your email address");
            Toast.makeText(this, "Please enter your email address", Toast.LENGTH_SHORT).show();
            binding.etUserEmail.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            //etSignUpUserEmail.setError("Enter a valid email address");
            Toast.makeText(this, "Enter a valid email address", Toast.LENGTH_SHORT).show();
            binding.etUserEmail.requestFocus();
            return;
        }

        //checking the validity of the password
        if (password.isEmpty()) {
            //Toast.makeText(this, "Enter your password", Toast.LENGTH_SHORT).show();
            binding.etUserEmailPass.setError("Enter your password");
            binding.etUserEmailPass.requestFocus();
        }

        if (password.length() < 6) {
            Toast.makeText(this, "wrong password", Toast.LENGTH_SHORT).show();
            //etSignInUserPassword.setError("wrong password");
            binding.etUserEmailPass.requestFocus();

        }


        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
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
        });
    }
}