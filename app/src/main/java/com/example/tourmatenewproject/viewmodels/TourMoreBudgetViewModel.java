package com.example.tourmatenewproject.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tourmatenewproject.entities.TourMoreBudgetModel;
import com.example.tourmatenewproject.repository.TourMoreBudgetLocalRepository;

import java.util.List;

public class TourMoreBudgetViewModel extends AndroidViewModel {
    private TourMoreBudgetLocalRepository moreBudgetLocalRepository;

    public TourMoreBudgetViewModel(@NonNull Application application) {
        super(application);
        moreBudgetLocalRepository = new TourMoreBudgetLocalRepository(application);
    }

    public void addMoreBudget(TourMoreBudgetModel tourMoreBudget) {
        moreBudgetLocalRepository.addMoreBudget(tourMoreBudget);
    }

    public void deleteMoreBudget(TourMoreBudgetModel tourMoreBudget) {
        moreBudgetLocalRepository.deleteMoreBudge(tourMoreBudget);
    }

    public void updateMoreBudget(TourMoreBudgetModel tourMoreBudget) {
        moreBudgetLocalRepository.updateMoreBudge(tourMoreBudget);
    }

    public LiveData<List<TourMoreBudgetModel>> getAllMoreBudget() {
        return moreBudgetLocalRepository.getAllMoreBudge();
    }
}
