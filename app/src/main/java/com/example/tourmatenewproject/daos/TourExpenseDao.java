package com.example.tourmatenewproject.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.tourmatenewproject.entities.TourExpenseModel;
import java.util.List;

@Dao
public interface TourExpenseDao {
    @Insert
    void insertExpense(TourExpenseModel tourExpense);

    @Update
    void updateExpense(TourExpenseModel tourExpense);

    @Delete
    void deleteExpense(TourExpenseModel tourExpense);

    @Query("select * from tbl_tour_expense")
    LiveData<List<TourExpenseModel>> getAllExpenses();


}
