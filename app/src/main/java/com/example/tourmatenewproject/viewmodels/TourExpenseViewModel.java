package com.example.tourmatenewproject.viewmodels;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.tourmatenewproject.entities.TourExpenseModel;
import com.example.tourmatenewproject.repository.TourExpenseLocalRepository;
import java.util.List;

public class TourExpenseViewModel extends AndroidViewModel {
    private TourExpenseLocalRepository localRepository;

    public TourExpenseViewModel(@NonNull Application application) {
        super(application);
        localRepository=new TourExpenseLocalRepository(application);
    }

    public void addExpense(TourExpenseModel tourExpense) {
        localRepository.addExpense(tourExpense);
    }

    public void deleteExpense(TourExpenseModel tourExpense) {
        localRepository.deleteExpense(tourExpense);
    }

    public void updateExpense(TourExpenseModel tourExpense) {
        localRepository.updateExpense(tourExpense);
    }

    public LiveData<List<TourExpenseModel>> getTripAllExpenses(int trip_id) {
        return localRepository.getTripAllExpenses(trip_id);
    }
}
