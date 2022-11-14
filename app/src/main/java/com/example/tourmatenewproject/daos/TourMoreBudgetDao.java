package com.example.tourmatenewproject.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tourmatenewproject.entities.TourMoreBudgetModel;

import java.util.List;


@Dao
public interface TourMoreBudgetDao {
    @Insert
    void insertMoreBudget(TourMoreBudgetModel tourMoreBudget);

    @Update
    void updateMoreBudget(TourMoreBudgetModel tourMoreBudget);

    @Delete
    void deleteMoreBudget(TourMoreBudgetModel tourMoreBudget);

    @Query("select * from tbl_more_budget")
    LiveData<List<TourMoreBudgetModel>> getAllMoreBudget();

    @Query("select * from tbl_more_budget where trip_id=:trip_id")
    LiveData<List<TourMoreBudgetModel>> getTripAllMoreBudget(int trip_id);

}
