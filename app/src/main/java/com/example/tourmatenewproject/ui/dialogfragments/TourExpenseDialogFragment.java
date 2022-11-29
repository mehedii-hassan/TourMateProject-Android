package com.example.tourmatenewproject.ui.dialogfragments;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tourmatenewproject.databinding.FragmentAddExpenseDialogBinding;
import com.example.tourmatenewproject.entities.TourEventModel;
import com.example.tourmatenewproject.entities.TourExpenseModel;
import com.example.tourmatenewproject.entities.UserModel;
import com.example.tourmatenewproject.viewmodels.TourEventViewModel;
import com.example.tourmatenewproject.viewmodels.TourExpenseViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class TourExpenseDialogFragment extends DialogFragment {

    private TourExpenseModel tourExpense;
    private FragmentAddExpenseDialogBinding binding;
    private TourExpenseViewModel expenseViewModel;
    private int expenseId = 0;
    private TourEventModel eventModel;


    public TourExpenseDialogFragment(TourEventModel eventModel) {
        if (eventModel != null) {
            this.eventModel = eventModel;
        }
    }

    public TourExpenseDialogFragment(TourExpenseModel tourExpense, TourEventModel eventModel) {
        if (eventModel != null) {
            this.eventModel = eventModel;
        }
        this.tourExpense = tourExpense;
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


            if (expenseAmount.isEmpty() || expenseComment.isEmpty()) {
                Toast.makeText(getActivity(), "Please fill up all the field properly!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (expenseId > 0) {
                //update expense-------------
                final int amount = Integer.parseInt(expenseAmount);
                final TourExpenseModel expense = new TourExpenseModel(expenseId, eventModel.getTrip_id(), amount, expenseComment, newDate);
                expenseViewModel.updateExpense(expense);
                //Log.e("TAG", "eventID : " + eventID);

                dismiss();
                Toast.makeText(view1.getContext(), "Successfully Updated", Toast.LENGTH_SHORT).show();
            }
            expenseViewModel.getTripAllExpenses(eventModel.getTrip_id()).observe(this, new Observer<List<TourExpenseModel>>() {
                @Override
                public void onChanged(List<TourExpenseModel> expenseList) {

                    int totalExpenseSum = 0;
                    int tripBudget = Integer.parseInt(eventModel.getTripBudget());

                    if (expenseList != null) {
                        for (TourExpenseModel model : expenseList) {
                            int temp = model.getAmount();
                            totalExpenseSum = totalExpenseSum + temp;
                        }
                    }

                    int remainingBudget = tripBudget - totalExpenseSum;
                    int temp = totalExpenseSum + Integer.parseInt(expenseAmount);
                    if (temp <= tripBudget) {

                        //insert new expense-----------
                        final int amount = Integer.parseInt(expenseAmount);
                        final TourExpenseModel expense = new TourExpenseModel(eventModel.getTrip_id(), amount, expenseComment, newDate);
                        expenseViewModel.addExpense(expense);
                        dismiss();
                        Toast.makeText(view1.getContext(), "Successfully Inserted", Toast.LENGTH_SHORT).show();

                    } else {
                        dismiss();
                        Toast.makeText(view1.getContext(), "Sorry,Your have left " + remainingBudget + " TK", Toast.LENGTH_LONG).show();
                    }
                }
            });

            //Checking validation-------------------
           /* if (expenseAmount.isEmpty() || expenseComment.isEmpty()) {
                Toast.makeText(getActivity(), "Please fill up all the field properly!", Toast.LENGTH_SHORT).show();
            } else {

                if (expenseId > 0) {
                    //update expense-------------
                    final int amount = Integer.parseInt(expenseAmount);
                    final TourExpenseModel expense = new TourExpenseModel(expenseId, eventModel.getTrip_id(), amount, expenseComment, newDate);
                    expenseViewModel.updateExpense(expense);
                    //Log.e("TAG", "eventID : " + eventID);

                    dismiss();
                    Toast.makeText(view1.getContext(), "Successfully Updated", Toast.LENGTH_SHORT).show();
                } else {
                    //insert new expense-----------
                    final int amount = Integer.parseInt(expenseAmount);
                    final TourExpenseModel expense = new TourExpenseModel(eventModel.getTrip_id(), amount, expenseComment, newDate);
                    expenseViewModel.addExpense(expense);
                    dismiss();
                    Toast.makeText(view1.getContext(), "Successfully Inserted", Toast.LENGTH_SHORT).show();
                }
            }*/

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
