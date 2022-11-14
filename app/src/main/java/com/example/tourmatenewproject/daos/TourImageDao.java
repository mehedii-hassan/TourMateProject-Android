package com.example.tourmatenewproject.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tourmatenewproject.entities.TourImageModel;

import java.util.List;

@Dao
public interface TourImageDao {
    @Insert
    void insertImage(TourImageModel tourImageModel);

    @Update
    void updateImage(TourImageModel tourImageModel);

    @Delete
    void deleteImage(TourImageModel tourImageModel);

    @Query("select * from tbl_tour_image where user_id=:user_id")
    LiveData<List<TourImageModel>> getUserAllImages(int user_id);
}
