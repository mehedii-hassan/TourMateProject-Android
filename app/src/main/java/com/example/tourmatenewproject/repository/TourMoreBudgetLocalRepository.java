package com.example.tourmatenewproject.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.tourmatenewproject.daos.TourMoreBudgetDao;
import com.example.tourmatenewproject.db.TourEventsDatabase;
import com.example.tourmatenewproject.entities.TourMoreBudgetModel;

import java.util.List;

public class TourMoreBudgetLocalRepository {
    private final TourMoreBudgetDao moreBudgetDao;

    public TourMoreBudgetLocalRepository(Context context) {
        moreBudgetDao = TourEventsDatabase.getDb(context).getMoreBudgetDao();
    }

    public void addMoreBudget(TourMoreBudgetModel tourMoreBudget) {
        TourEventsDatabase.backgroundService.execute(new Runnable() {
            @Override
            public void run() {
                //codes within this block will run on background thread.
                moreBudgetDao.insertMoreBudget(tourMoreBudget);
            }
        });

    }

    public void deleteMoreBudge(TourMoreBudgetModel tourMoreBudget) {

        TourEventsDatabase.backgroundService.execute(new Runnable() {
            @Override
            public void run() {
                //codes within this block will run on background thread.
                moreBudgetDao.deleteMoreBudget(tourMoreBudget);
            }
        });
    }

    public void updateMoreBudge(TourMoreBudgetModel tourMoreBudget) {

        TourEventsDatabase.backgroundService.execute(new Runnable() {
            @Override
            public void run() {
                //codes within this block will run on background thread.
                moreBudgetDao.updateMoreBudget(tourMoreBudget);
            }
        });

    }


    public LiveData<List<TourMoreBudgetModel>> getAllMoreBudge() {
        return moreBudgetDao.getAllMoreBudget();
    }
}
