package com.example.tourmatenewproject.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tourmatenewproject.entities.TourImageModel;
import com.example.tourmatenewproject.repository.TourImageLocalRepository;

import java.util.List;

public class TourImageViewModel extends AndroidViewModel {
    private TourImageLocalRepository localRepository;


    public TourImageViewModel(@NonNull Application application) {
        super(application);
        localRepository=new TourImageLocalRepository(application);
    }

    public void addImage(TourImageModel tourImage) {
        localRepository.addImage(tourImage);
    }
     public void deleteImage(TourImageModel tourImage) {
        localRepository.deleteImage(tourImage);
    }
     public void updateImage(TourImageModel tourImage) {
        localRepository.updateImage(tourImage);
    }

    public LiveData<List<TourImageModel>> getAllImages(){
        return localRepository.getAllImages();
    }

}
