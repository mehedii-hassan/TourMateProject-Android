package com.example.tourmatenewproject.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tourmatenewproject.entities.TourEventModel;
import com.example.tourmatenewproject.repository.TourEventsLocalRepository;

import java.util.List;

public class TourEventViewModel extends AndroidViewModel {
    private TourEventsLocalRepository localRepository;

    public TourEventViewModel(@NonNull Application application) {
        super(application);
        localRepository = new TourEventsLocalRepository(application);
    }

    public void addEvent(TourEventModel tourEventModel) {
        localRepository.addEvent(tourEventModel);
    }

    public void deleteEvent(TourEventModel tourEventModel) {
        localRepository.deleteEvent(tourEventModel);
    }

    public void updateEvent(TourEventModel tourEventModel) {
        localRepository.updateEvent(tourEventModel);
    }

    public LiveData<List<TourEventModel>> getAllTourEvents() {
        return localRepository.getAllEvents();
    }
}
