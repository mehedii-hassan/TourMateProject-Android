package com.example.tourmatenewproject.dialogfragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tourmatenewproject.databinding.FragmentAddExpenseDialogBinding;
import com.example.tourmatenewproject.entities.TourExpenseModel;
import com.example.tourmatenewproject.viewmodels.TourExpenseViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;


public class TourExpenseDialogFragment extends DialogFragment {

    private TourExpenseModel tourExpense;
    private FragmentAddExpenseDialogBinding binding;
    private TourExpenseViewModel expenseViewModel;
    private int expenseId = 0;

    public TourExpenseDialogFragment(TourExpenseModel tourExpense) {
        this.tourExpense = tourExpense;
    }

    public TourExpenseDialogFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        expenseViewModel = new ViewModelProvider(requireActivity())
                .get(TourExpenseViewModel.class);
        binding = FragmentAddExpenseDialogBinding.inflate(inflater, container, false);

        if (tourExpense != null) {
            expenseId = tourExpense.getTour_expense_id();
            binding.etComment.setText(tourExpense.getComment());
            binding.etAmount.setText(String.valueOf(tourExpense.getAmount()));
            binding.btnSave.setText("Update");
            binding.tvAddNewExpense.setText("Update an Expense");
        }
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        binding.btnSave.setOnClickListener(view1 -> {

            final String expenseAmount = binding.etAmount.getText().toString().trim();
            final String expenseComment = binding.etComment.getText().toString().trim();

            long date = System.currentTimeMillis();
            Date d = new Date(date);
            SimpleDateFormat df = new SimpleDateFormat("MMM d, yyyy hh.mm aa");
            String newDate = df.format(d);

            //Checking validation-------------------
            if (expenseAmount.isEmpty() || expenseComment.isEmpty()) {
                Toast.makeText(getActivity(), "Please fill up all the field properly!", Toast.LENGTH_SHORT).show();
            } else {

                if (expenseId > 0) {
                    //update expense-------------
                    final int amount = Integer.parseInt(expenseAmount);
                    final TourExpenseModel expense = new TourExpenseModel(expenseId, amount, expenseComment, newDate);
                    expenseViewModel.updateExpense(expense);
                    dismiss();
                    Toast.makeText(view1.getContext(), "Successfully Updated", Toast.LENGTH_SHORT).show();
                } else {
                    //insert new expense-----------
                    final int amount = Integer.parseInt(expenseAmount);
                    final TourExpenseModel expense = new TourExpenseModel(amount, expenseComment, newDate);
                    expenseViewModel.addExpense(expense);
                    dismiss();
                    Toast.makeText(view1.getContext(), "Successfully Inserted", Toast.LENGTH_SHORT).show();
                }
            }

        });
        binding.btnCancel.setOnClickListener(view12 -> dismiss());
    }


    //custom dialogFragment width set maximize----------------------
    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setLayout(width, height);
        }
    }
}
