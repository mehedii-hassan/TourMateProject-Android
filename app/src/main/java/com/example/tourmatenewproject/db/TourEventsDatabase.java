package com.example.tourmatenewproject.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.tourmatenewproject.daos.TourEventDao;
import com.example.tourmatenewproject.daos.TourExpenseDao;
import com.example.tourmatenewproject.daos.TourImageDao;
import com.example.tourmatenewproject.daos.TourMoreBudgetDao;
import com.example.tourmatenewproject.entities.TourEventModel;
import com.example.tourmatenewproject.entities.TourExpenseModel;
import com.example.tourmatenewproject.entities.TourImageModel;
import com.example.tourmatenewproject.entities.TourMoreBudgetModel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {TourEventModel.class, TourExpenseModel.class, TourMoreBudgetModel.class, TourImageModel.class}, version = 1)
public abstract class TourEventsDatabase extends RoomDatabase {

    private static TourEventsDatabase db;
    public static ExecutorService backgroundService= Executors.newFixedThreadPool(4);
    public abstract TourEventDao getEventDao();
    public abstract TourExpenseDao getExpenseDao();
    public abstract TourMoreBudgetDao getMoreBudgetDao();
    public abstract TourImageDao getTourImageDao();

    public static TourEventsDatabase getDb(Context context) {
        if (db == null) {
            db = Room.databaseBuilder(context.getApplicationContext(), TourEventsDatabase.class, "TourEventsDb")
                    .build();
        }
        return db;
    }

}
