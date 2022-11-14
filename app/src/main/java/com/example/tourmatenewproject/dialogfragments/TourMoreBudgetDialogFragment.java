package com.example.tourmatenewproject.dialogfragments;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tourmatenewproject.databinding.FragmentTourMoreBudgetDialogBinding;
import com.example.tourmatenewproject.entities.TourEventModel;
import com.example.tourmatenewproject.entities.TourMoreBudgetModel;
import com.example.tourmatenewproject.entities.UserModel;
import com.example.tourmatenewproject.viewmodels.TourMoreBudgetViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;


public class TourMoreBudgetDialogFragment extends DialogFragment {

    private TourMoreBudgetModel moreBudgetModel;
    private FragmentTourMoreBudgetDialogBinding binding;
    private TourMoreBudgetViewModel moreBudgetViewModel;
    private int moreBudgetId = 0;
    private int eventID = 0;
    private UserModel user;
    private TourEventModel eventModel;


    public TourMoreBudgetDialogFragment(TourEventModel eventModel) {
        // Required empty public constructor
        this.eventModel = eventModel;
    }

    public TourMoreBudgetDialogFragment(TourMoreBudgetModel moreBudgetModel) {
        this.moreBudgetModel = moreBudgetModel;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        moreBudgetViewModel = new ViewModelProvider(requireActivity())
                .get(TourMoreBudgetViewModel.class);
        binding = FragmentTourMoreBudgetDialogBinding.inflate(inflater, container, false);

        if (moreBudgetModel != null) {
            eventID = eventModel.getTrip_id();
            moreBudgetId = moreBudgetModel.getMore_budget_id();
            binding.etAmountMB.setText(String.valueOf(moreBudgetModel.getMore_budget_amount()));
            binding.etTakingFrom.setText(moreBudgetModel.getTaking_from_where());
            binding.btnSaveMB.setText("Update");
            binding.tvAddMoreBudget.setText("Update tour more budget");
        }
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding.btnSaveMB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view12) {
                final String amountMB = binding.etAmountMB.getText().toString().trim();
                final String takingFromName = binding.etTakingFrom.getText().toString().trim();

                long date = System.currentTimeMillis();
                Date d = new Date(date);
                SimpleDateFormat df = new SimpleDateFormat("MMM d, yyyy hh.mm aa");
                String newDate = df.format(d);

                //Checking validation--------------------
                if (amountMB.isEmpty() || takingFromName.isEmpty()) {
                    Toast.makeText(getActivity(), "Please fill up all the field properly!", Toast.LENGTH_SHORT).show();
                } else {

                    if (moreBudgetId > 0) {
                        //update more budget-------------
                        final int amount = Integer.parseInt(amountMB);
                        final TourMoreBudgetModel moreBudgetModel = new TourMoreBudgetModel(moreBudgetId, eventID, amount, takingFromName, newDate);
                        moreBudgetViewModel.updateMoreBudget(moreBudgetModel);
                        TourMoreBudgetDialogFragment.this.dismiss();
                        Toast.makeText(view12.getContext(), "Successfully Updated", Toast.LENGTH_SHORT).show();

                    } else {
                        //insert new more budget--------------------
                        final int amount = Integer.parseInt(amountMB);
                        final TourMoreBudgetModel moreBudgetModel = new TourMoreBudgetModel(eventModel.getTrip_id(), amount, takingFromName, newDate);
                        moreBudgetViewModel.addMoreBudget(moreBudgetModel);
                        TourMoreBudgetDialogFragment.this.dismiss();
                        Toast.makeText(view12.getContext(), "Successfully Inserted", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        binding.btnCancelMB.setOnClickListener(view1 -> dismiss());
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