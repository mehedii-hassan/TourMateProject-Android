package com.example.tourmatenewproject.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.tourmatenewproject.databinding.ActivityLoginBinding;
import com.example.tourmatenewproject.entities.UserModel;
import com.example.tourmatenewproject.viewmodels.UserViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private UserViewModel viewModel;
    //private FirebaseAuth mAuth;
    //private androidx.appcompat.app.AlertDialog.Builder alertDialogBuilder;
    //private androidx.appcompat.app.AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(this).get(UserViewModel.class);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //mAuth = FirebaseAuth.getInstance();

        binding.tvForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, PasswordResetActivity.class));

            }
        });

        binding.btnUserLogin.setOnClickListener(view -> {
            userLogin();
            //startActivity(new Intent(LoginActivity.this, MainActivity.class));
        });

        binding.tvSignUp.setOnClickListener(view -> startActivity(new Intent(LoginActivity.this, UserSignUpActivity.class)));
    }

    private void userLogin() {
        String email = binding.etUserEmail.getText().toString().trim();
        String password = binding.etUserEmail.getText().toString().trim();

        //checking the validity of the email
        if (email.isEmpty()) {
            //binding.etEmailAddress.setError("Enter an email address");
            binding.etUserEmail.requestFocus();
            Toast.makeText(this, "Please enter your Email", Toast.LENGTH_SHORT).show();
            binding.etUserEmail.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            //binding.etEmailAddress.setError("Enter a valid email address");
            binding.etUserEmail.requestFocus();
            Toast.makeText(this, "Please enter a valid  Email", Toast.LENGTH_SHORT).show();
            binding.etUserEmail.requestFocus();
            return;
        }

        //checking the validity of the password
        if (password.isEmpty()) {
            Toast.makeText(this, "Enter a password", Toast.LENGTH_SHORT).show();
            //binding.etEmailPassword.setError("Enter a password");
            binding.etUserEmail.requestFocus();
            return;
        }
        if (password.length() < 6) {
            Toast.makeText(this, "Password length should be at least 6", Toast.LENGTH_SHORT).show();
            //binding.etEmailPassword.setError("minimum length at least 6");
            binding.etUserEmail.requestFocus();
            return;
        }
        viewModel.getUserEmail(email).observe(this, new Observer<UserModel>() {
            @Override
            public void onChanged(UserModel user) {


                if (user != null) {
                    if (!user.getUserPassword().equals(binding.etUserEmailPass.getText().toString().trim())) {
                        Toast.makeText(LoginActivity.this, "Wrong Password", Toast.LENGTH_LONG).show();
                        return;
                    }
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                    Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid email", Toast.LENGTH_LONG).show();
                }


            }
        });


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