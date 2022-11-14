package com.example.tourmatenewproject.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.tourmatenewproject.daos.UserDao;
import com.example.tourmatenewproject.db.TourEventsDatabase;
import com.example.tourmatenewproject.entities.UserModel;

public class UserLocalRepository {
    private final UserDao userDao;

    public UserLocalRepository(Context context) {
        userDao = TourEventsDatabase.getDb(context).getSignUpDao();
    }

    public void addUser(UserModel user) {
        TourEventsDatabase.backgroundService.execute(new Runnable() {
            @Override
            public void run() {
                //codes within this block will run on background thread.
                userDao.insertUser(user);
            }
        });
    }

    public void updateUser(UserModel user) {
        TourEventsDatabase.backgroundService.execute(new Runnable() {
            @Override
            public void run() {
                //codes within this block will run on background thread.
                userDao.updateUser(user);
            }
        });
    }

    public void deleteUser(UserModel user) {
        TourEventsDatabase.backgroundService.execute(new Runnable() {
            @Override
            public void run() {
                //codes within this block will run on background thread.
                userDao.deleteUser(user);
            }
        });
    }

    public LiveData<UserModel> getUserEmail(String email) {
        return userDao.getUserEmail(email);
    }


}
