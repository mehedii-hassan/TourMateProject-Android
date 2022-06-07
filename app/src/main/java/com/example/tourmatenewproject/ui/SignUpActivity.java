package com.example.tourmatenewproject.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tourmatenewproject.R;
import com.example.tourmatenewproject.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;


public class SignUpActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private androidx.appcompat.app.AlertDialog alertDialog;
    private ActivitySignUpBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();

        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userSignUp();

            }
        });

        binding.tvLoginSU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        showAlertDialog();

    }

    private void showAlertDialog() {
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.custom_alert_dialog, null);

        alertDialog = new androidx.appcompat.app.AlertDialog.Builder(this).setView(view).create();
        alertDialog.show();

        Button buttonYes = view.findViewById(R.id.btnYes);
        Button buttonNo = view.findViewById(R.id.btnNo);

        buttonYes.setOnClickListener(view1 -> finish());
        buttonNo.setOnClickListener(view12 -> alertDialog.cancel());

    }


    private void userSignUp() {
        //String userName = etSignUpUserName.getText().toString().trim().toLowerCase();
        String email = binding.etEmailAddress.getText().toString().trim();
        String password = binding.etEmailPassword.getText().toString().trim();
        String passConfirm = binding.etEmailConfirmPass.getText().toString().trim();


        //checking the validity of the email
        if (email.isEmpty()) {
            binding.etEmailAddress.setError("Enter an email address");
            binding.etEmailAddress.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.etEmailAddress.setError("Enter a valid email address");
            binding.etEmailAddress.requestFocus();
            return;
        }

        //checking the validity of the password
        if (password.isEmpty()) {
            binding.etEmailPassword.setError("Enter a password");
            binding.etEmailPassword.requestFocus();
        }
        if (password.length() < 6) {
            binding.etEmailPassword.setError("minimum length at least 6");
            binding.etEmailPassword.requestFocus();

        }
        //checking the validity of Confirm password
        if (passConfirm.isEmpty()) {
            //etConfirmPassword.setError("Enter confirm password");
            Toast.makeText(getApplicationContext(), "Enter confirm password ", Toast.LENGTH_SHORT).show();
            binding.etEmailConfirmPass.requestFocus();
        } else if (!passConfirm.equalsIgnoreCase(password)) {
            //etConfirmPassword.setError("Password not matched");
            Toast.makeText(getApplicationContext(), "Password not matched ", Toast.LENGTH_SHORT).show();
            binding.etEmailConfirmPass.requestFocus();

        }

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Registration successfully completed ", Toast.LENGTH_SHORT).show();
                    binding.etEmailAddress.setText("");
                    binding.etEmailPassword.setText("");
                    binding.etEmailConfirmPass.setText("");
                } else {
                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(getApplicationContext(), "User is already registered", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(), "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }
}