package com.example.tourmatenewproject.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tourmatenewproject.entities.UserModel;


@Dao
public interface UserDao {

    @Insert
    void insertUser(UserModel userModel);

    @Update
    void updateUser(UserModel userModel);

    @Delete
    void deleteUser(UserModel userModel);

    @Query("SELECT * from tbl_user_info where  user_email=:email")
    LiveData<UserModel> getUserEmail(String email);

}
