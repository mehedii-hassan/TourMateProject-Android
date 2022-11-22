package com.example.tourmatenewproject.dialogfragments;

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
import androidx.lifecycle.ViewModelProvider;

import com.example.tourmatenewproject.databinding.FragmentEventDialogBinding;
import com.example.tourmatenewproject.entities.TourEventModel;
import com.example.tourmatenewproject.entities.UserModel;
import com.example.tourmatenewproject.viewmodels.TourEventViewModel;
import com.google.android.material.datepicker.MaterialDatePicker;

import java.text.SimpleDateFormat;
import java.text.ParseException;

import java.util.Date;

public class TourEventDialogFragment extends DialogFragment {

    private FragmentEventDialogBinding binding;
    private TourEventViewModel viewModel;
    private TourEventModel tourEvent;
    private int tripId = 0;
    private int tripUserId = 0;
    private UserModel user;

    public TourEventDialogFragment(TourEventModel tourEvent) {
        this.tourEvent = tourEvent;
    }

    public TourEventDialogFragment(UserModel user) {

        this.user = user;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(requireActivity())
                .get(TourEventViewModel.class);
        binding = FragmentEventDialogBinding.inflate(inflater, container, false);

        if (tourEvent != null) {
            tripId = tourEvent.getTrip_id();
            tripUserId = tourEvent.getUserId();
            Log.e("TAG", "tripid : " + tripId);
            binding.etTripName.setText(tourEvent.getTripName());
            binding.etTripDescription.setText(tourEvent.getTripDescription());
            binding.etTripStartLocation.setText(tourEvent.getTripStartLocation());
            binding.etTripDestination.setText(tourEvent.getTripDestination());
            binding.tvTripStartDate.setText(tourEvent.getTripStartDate());
            binding.tvTripEndDate.setText(tourEvent.getTripEndDate());
            binding.etTripBudget.setText(tourEvent.getTripBudget());
            binding.btnCreateEvent.setText("Update an event");
            binding.tvAddAnEvent.setText("Update an Event");
        }
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //super.onViewCreated(view, savedInstanceState);
        binding.tvTripStartDate.setOnClickListener(view12 -> {
            MaterialDatePicker<Long> materialDatePicker = MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Select date")
                    .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                    .build();
            materialDatePicker.show(TourEventDialogFragment.this.getActivity().getSupportFragmentManager(), "Select date");
            materialDatePicker.addOnPositiveButtonClickListener(selection -> binding.tvTripStartDate.setText(materialDatePicker.getHeaderText()));
        });
        binding.tvTripEndDate.setOnClickListener(view1 -> {
            MaterialDatePicker<Long> materialDatePicker = MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Select date")
                    .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                    .build();
            materialDatePicker.show(TourEventDialogFragment.this.getActivity().getSupportFragmentManager(), "Select date");
            materialDatePicker.addOnPositiveButtonClickListener(selection -> binding.tvTripEndDate.setText(materialDatePicker.getHeaderText()));
        });

        binding.btnCreateEvent.setOnClickListener(view13 -> {

            final String tripName = binding.etTripName.getText().toString().trim();
            final String tripDescription = binding.etTripDescription.getText().toString().trim();
            final String tripStartLocation = binding.etTripStartLocation.getText().toString().trim();
            final String tripDestination = binding.etTripDestination.getText().toString().trim();
            final String tripStartDate = binding.tvTripStartDate.getText().toString().trim();
            final String tripEndDate = binding.tvTripEndDate.getText().toString().trim();
            final String tripBudget = binding.etTripBudget.getText().toString().trim();

            //set event create date-------------------------
            final long dateMS = System.currentTimeMillis();
            Date date = new Date(dateMS);
            SimpleDateFormat df = new SimpleDateFormat("MMM d, yyyy");
            String eventCreateDate = df.format(date).trim();

            //calcullate how many days left
            long differenceInDays = findDifference(eventCreateDate, tripStartDate);

            //Checking validation-------------------
            if (tripName.isEmpty() || tripDescription.isEmpty() || tripStartLocation.isEmpty() || tripDestination.isEmpty()
                    || tripStartDate.isEmpty() || tripEndDate.isEmpty() || tripBudget.isEmpty()) {
                Toast.makeText(getActivity(), "Please fill up all the field properly!", Toast.LENGTH_SHORT).show();
            } else {

                if (tripId > 0) {
                    //update event-----------------------
                    final TourEventModel tourEvent = new TourEventModel(tripId, tripUserId, tripName, tripDescription, tripStartLocation,
                            tripDestination, tripStartDate, tripEndDate, tripBudget, eventCreateDate, differenceInDays);
                    viewModel.updateEvent(tourEvent);
                    dismiss();
                    Toast.makeText(getActivity(), "Successfully Updated", Toast.LENGTH_SHORT).show();
                } else {
                    //insert new event--------------------
                    final TourEventModel tourEventModel = new TourEventModel(user.getUserId(), tripName, tripDescription, tripStartLocation,
                            tripDestination, tripStartDate, tripEndDate, tripBudget, eventCreateDate, differenceInDays);
                    viewModel.addEvent(tourEventModel);
                    dismiss();
                    Toast.makeText(getActivity(), "Successfully Created", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public long findDifference(String start_date, String end_date) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy");
        long difference_In_Days = 0;
        try {
            Date d1 = sdf.parse(start_date);
            Date d2 = sdf.parse(end_date);
            long difference_In_Time = d2.getTime() - d1.getTime();
            difference_In_Days = (difference_In_Time / (1000 * 60 * 60 * 24)) % 365;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return difference_In_Days;
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
