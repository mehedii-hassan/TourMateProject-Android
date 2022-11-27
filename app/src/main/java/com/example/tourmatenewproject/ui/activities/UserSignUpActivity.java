package com.example.tourmatenewproject.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tourmatenewproject.R;
import com.example.tourmatenewproject.databinding.ActivitySignUpBinding;
import com.example.tourmatenewproject.db.TourEventsDatabase;
import com.example.tourmatenewproject.entities.UserModel;
import com.example.tourmatenewproject.viewmodels.UserViewModel;


public class UserSignUpActivity extends AppCompatActivity {


    //private FirebaseAuth mAuth;
    private androidx.appcompat.app.AlertDialog alertDialog;
    private ActivitySignUpBinding binding;
    private UserViewModel userViewModel;
    private UserModel user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userViewModel = new ViewModelProvider(this)
                .get(UserViewModel.class);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // mAuth = FirebaseAuth.getInstance();

        binding.btnSignUp.setOnClickListener(view -> userSignUp());

        binding.tvLoginSU.setOnClickListener(view -> {
            startActivity(new Intent(UserSignUpActivity.this, LoginActivity.class));
            finish();
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
        String userName = binding.etUserName.getText().toString().trim().toLowerCase();
        String email = binding.etEmailAddress.getText().toString().trim();
        String password = binding.etEmailPassword.getText().toString().trim();
        String passConfirm = binding.etEmailConfirmPass.getText().toString().trim();


        //Check user name is empty or not ---------------------------------
        if (userName.isEmpty()) {
            Toast.makeText(this, "Please enter your Name", Toast.LENGTH_SHORT).show();
            binding.etUserName.requestFocus();
            return;
        }
        //checking the validity of the email
        if (email.isEmpty()) {
            //binding.etEmailAddress.setError("Enter an email address");
            binding.etEmailAddress.requestFocus();
            Toast.makeText(this, "Please enter your Email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            //binding.etEmailAddress.setError("Enter a valid email address");
            binding.etEmailAddress.requestFocus();
            Toast.makeText(this, "Please enter a valid  Email", Toast.LENGTH_SHORT).show();
            return;
        }

        //checking the validity of the password
        if (password.isEmpty()) {
            Toast.makeText(this, "Enter a password", Toast.LENGTH_SHORT).show();
            //binding.etEmailPassword.setError("Enter a password");
            binding.etEmailPassword.requestFocus();
            return;
        }
        if (password.length() < 6) {
            Toast.makeText(this, "Password length should be at least 6", Toast.LENGTH_SHORT).show();
            //binding.etEmailPassword.setError("minimum length at least 6");
            binding.etEmailPassword.requestFocus();
            return;
        }
        //checking the validity of Confirm password
        if (passConfirm.isEmpty()) {
            //etConfirmPassword.setError("Enter confirm password");
            Toast.makeText(getApplicationContext(), "Enter confirm password ", Toast.LENGTH_SHORT).show();
            binding.etEmailConfirmPass.requestFocus();
            return;
        }
        if (!passConfirm.equalsIgnoreCase(password)) {
            //etConfirmPassword.setError("Password not matched");
            Toast.makeText(getApplicationContext(), "Password not matched ", Toast.LENGTH_SHORT).show();
            binding.etEmailConfirmPass.requestFocus();
            return;
        }

        boolean isUserExist = TourEventsDatabase.getDb(this).getSignUpDao().isUserExists(email, password);

        //Check already registered or  not . If not then will be registered-----------
        if (isUserExist) {
            Toast.makeText(UserSignUpActivity.this, "User is already registered try another", Toast.LENGTH_LONG).show();
        } else {
                final UserModel newUser = new UserModel(userName, email, password, passConfirm);
                userViewModel.insertUser(newUser);
                binding.etUserName.setText("");
                binding.etEmailAddress.setText("");
                binding.etEmailPassword.setText("");
                binding.etEmailConfirmPass.setText("");
                Toast.makeText(UserSignUpActivity.this, "Successfully registered", Toast.LENGTH_LONG).show();
            }



            /*userViewModel.getUserEmail(email).observe(this, new Observer<UserModel>() {
                @Override
                public void onChanged(UserModel user) {
                    // UserSignUpActivity.this.user = user;
                    final UserModel newUser = new UserModel(userName, email, password, passConfirm);
                    userViewModel.insertUser(newUser);
                    binding.etUserName.setText("");
                    binding.etEmailAddress.setText("");
                    binding.etEmailPassword.setText("");
                    binding.etEmailConfirmPass.setText("");
                    Toast.makeText(UserSignUpActivity.this, "Successfully registered", Toast.LENGTH_LONG).show();
                }
            });*/

        }



        /*mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
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
        });*/
    }
