package com.example.tourmatenewproject.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tourmatenewproject.entities.UserModel;
import com.example.tourmatenewproject.repository.UserLocalRepository;

public class UserViewModel extends AndroidViewModel {

    private UserLocalRepository userLocalRepository;


    public UserViewModel(@NonNull Application application) {
        super(application);
        userLocalRepository = new UserLocalRepository(application);
    }

    public void insertUser(UserModel user) {
        userLocalRepository.addUser(user);
    }

    public void updateUser(UserModel user) {
        userLocalRepository.updateUser(user);
    }

    public void deleteUser(UserModel user) {
        userLocalRepository.deleteUser(user);
    }

    public LiveData<UserModel> getUserEmail(String email) {
        return userLocalRepository.getUserEmail(email);
    }

    /*public LiveData<Boolean> emailExistsOnNot(String email) {
        return userLocalRepository.emailExistsOnNot(email);
    }*/

}
