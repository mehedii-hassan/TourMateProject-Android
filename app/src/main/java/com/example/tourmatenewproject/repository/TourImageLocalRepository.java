package com.example.tourmatenewproject.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.tourmatenewproject.daos.TourImageDao;
import com.example.tourmatenewproject.db.TourEventsDatabase;
import com.example.tourmatenewproject.entities.TourImageModel;

import java.util.List;

public class TourImageLocalRepository {
    private final TourImageDao tourImageDao;

    public TourImageLocalRepository(Context context) {
        tourImageDao = TourEventsDatabase.getDb(context).getTourImageDao();
    }

    public void addImage(TourImageModel tourImage) {
        TourEventsDatabase.backgroundService.execute(new Runnable() {
            @Override
            public void run() {
                tourImageDao.insertImage(tourImage);
            }
        });
    }

    public void deleteImage(TourImageModel tourImage) {
        TourEventsDatabase.backgroundService.execute(new Runnable() {
            @Override
            public void run() {
                tourImageDao.deleteImage(tourImage);
            }
        });
    }

    public void updateImage(TourImageModel tourImage) {
        TourEventsDatabase.backgroundService.execute(new Runnable() {
            @Override
            public void run() {
                tourImageDao.updateImage(tourImage);
            }
        });
    }

    public LiveData<List<TourImageModel>> getUserAllImages(int userId) {
        return tourImageDao.getUserAllImages(userId);
    }
}
