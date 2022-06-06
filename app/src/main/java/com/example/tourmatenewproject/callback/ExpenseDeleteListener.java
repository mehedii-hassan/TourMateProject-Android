package com.example.tourmatenewproject.callback;

import com.example.tourmatenewproject.entities.TourExpenseModel;

public interface ExpenseDeleteListener {
    void onDeleteExpense(TourExpenseModel tourExpense);
}
