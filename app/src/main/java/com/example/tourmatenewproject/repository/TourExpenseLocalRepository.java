package com.example.tourmatenewproject.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.tourmatenewproject.daos.TourExpenseDao;
import com.example.tourmatenewproject.db.TourEventsDatabase;
import com.example.tourmatenewproject.entities.TourExpenseModel;

import java.util.List;

public class TourExpenseLocalRepository {
    private final TourExpenseDao tourExpenseDao;

    public TourExpenseLocalRepository(Context context) {
        tourExpenseDao = TourEventsDatabase.getDb(context).getExpenseDao();
    }

    public void addExpense(TourExpenseModel tourExpense) {
        TourEventsDatabase.backgroundService.execute(new Runnable() {
            @Override
            public void run() {
                //codes within this block will run on background thread.
                tourExpenseDao.insertExpense(tourExpense);
            }
        });

    }

    public void deleteExpense(TourExpenseModel tourExpense) {

        TourEventsDatabase.backgroundService.execute(new Runnable() {
            @Override
            public void run() {
                //codes within this block will run on background thread.
                tourExpenseDao.deleteExpense(tourExpense);
            }
        });
    }

    public void updateExpense(TourExpenseModel tourExpense) {

        TourEventsDatabase.backgroundService.execute(new Runnable() {
            @Override
            public void run() {
                //codes within this block will run on background thread.
                tourExpenseDao.updateExpense(tourExpense);
            }
        });

    }


    public LiveData<List<TourExpenseModel>> getAllExpenses() {
        return tourExpenseDao.getAllExpenses();
    }

    public LiveData<List<TourExpenseModel>> getUserAllExpenses(int user_id) {
        return tourExpenseDao.getUserAllExpenses(user_id);
    }
}
