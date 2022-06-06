package com.example.tourmatenewproject.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.tourmatenewproject.daos.TourEventDao;
import com.example.tourmatenewproject.db.TourEventsDatabase;
import com.example.tourmatenewproject.entities.TourEventModel;

import java.util.List;

public class TourEventsLocalRepository {
    private final TourEventDao tourEventDao;

    public TourEventsLocalRepository(Context context) {
        tourEventDao = TourEventsDatabase.getDb(context).getEventDao();
    }

    public void addEvent(TourEventModel tourEventModel) {
        TourEventsDatabase.backgroundService.execute(new Runnable() {
            @Override
            public void run() {
                //codes within this block will run on background thread.
                tourEventDao.insertEvent(tourEventModel);
            }
        });

    }

    public void deleteEvent(TourEventModel tourEventModel) {

        TourEventsDatabase.backgroundService.execute(new Runnable() {
            @Override
            public void run() {
                //codes within this block will run on background thread.
                tourEventDao.deleteEvent(tourEventModel);
            }
        });
    }

    public void updateEvent(TourEventModel tourEventModel) {

        TourEventsDatabase.backgroundService.execute(new Runnable() {
            @Override
            public void run() {
                //codes within this block will run on background thread.
                tourEventDao.updateEvent(tourEventModel);
            }
        });

    }


    public LiveData<List<TourEventModel>> getAllEvents() {
        return tourEventDao.getAllEvents();
    }
}
