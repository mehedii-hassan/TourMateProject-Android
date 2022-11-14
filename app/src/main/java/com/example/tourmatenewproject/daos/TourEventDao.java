package com.example.tourmatenewproject.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.tourmatenewproject.entities.TourEventModel;
import com.example.tourmatenewproject.entities.relations.UserSignUpWithTourEvents;

import java.util.List;

@Dao
public interface TourEventDao {

    @Insert
    void insertEvent(TourEventModel tourEventModel);

    @Update
    void updateEvent(TourEventModel tourEventModel);

    @Delete
    void deleteEvent(TourEventModel tourEventModel);

    @Query("select * from tbl_tour_events")
    LiveData<List<TourEventModel>> getAllEvents();

    @Query("select * from tbl_tour_events where user_id=:user_id")
    LiveData<List<TourEventModel>> getUserAllEvents(int user_id);


}
