package com.example.tourmatenewproject.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;

import com.example.tourmatenewproject.adapters.ExpandableListAdapter;
import com.example.tourmatenewproject.databinding.ActivityEventDetailsBinding;
import com.example.tourmatenewproject.dialogfragments.TourExpenseDialogFragment;
import com.example.tourmatenewproject.dialogfragments.TourMoreBudgetDialogFragment;
import com.example.tourmatenewproject.entities.TourEventModel;
import com.example.tourmatenewproject.entities.TourExpenseModel;
import com.example.tourmatenewproject.entities.UserModel;
import com.example.tourmatenewproject.expandable_list_view.ExpandableListDataItems;
import com.example.tourmatenewproject.viewmodels.TourExpenseViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EventDetailsActivity extends AppCompatActivity {

    private ActivityEventDetailsBinding binding;
    private TourEventModel eventModel;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;
    private UserModel user;
    private TourExpenseViewModel viewModel;
    private List<TourExpenseModel> expenseModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(TourExpenseViewModel.class);
        binding = ActivityEventDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //get user and event model from intent ------------------
        eventModel = getIntent().getParcelableExtra("eventModel");
        user = getIntent().getParcelableExtra("user");
        Log.e("TAG", "email " + user.getUserEmail());
        bind(eventModel);

        listDataChild = ExpandableListDataItems.getData();
        listDataHeader = new ArrayList<>(listDataChild.keySet());

        ExpandableListAdapter expandableListAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        binding.expandableLV.setAdapter(expandableListAdapter);


        binding.expandableLV.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {

                String selected = (String) expandableListAdapter.getChild(i, i1);

                if (selected.equalsIgnoreCase("Add new expense")) {

                    TourExpenseDialogFragment fragment = new TourExpenseDialogFragment(user);
                    fragment.show(getSupportFragmentManager(), "Tour Expense");

                } else if (selected.equalsIgnoreCase("View all expenses")) {

                    Intent intent = new Intent(EventDetailsActivity.this, ExpenseListActivity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);

                } else if (selected.equalsIgnoreCase("Add more budget")) {

                    TourMoreBudgetDialogFragment fragment = new TourMoreBudgetDialogFragment(user);
                    fragment.show(getSupportFragmentManager(), "Tour More Budget");

                } else if (selected.equalsIgnoreCase("View more budget list")) {

                    Intent intent = new Intent(EventDetailsActivity.this, MoreBudgetActivity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                } else if (selected.equalsIgnoreCase("Take a photo")) {
                    Intent intent = new Intent(EventDetailsActivity.this, CameraActivity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                } else if (selected.equalsIgnoreCase("View gallery")) {

                    Intent intent = new Intent(EventDetailsActivity.this, GalleryActivity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                } else if (selected.equalsIgnoreCase("View all moments")) {
                    Intent intent = new Intent(EventDetailsActivity.this, MomentsActivity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                }
                return true;
            }
        });

        viewModel.getUserAllExpenses(user.getUserId()).observe(this, new Observer<List<TourExpenseModel>>() {
            @Override
            public void onChanged(List<TourExpenseModel> expenseList) {
                if (expenseList != null) {
                    int totalExpenseSum = 0;
                    for (TourExpenseModel model : expenseList) {

                        int temp = model.getAmount();
                        totalExpenseSum = totalExpenseSum + temp;
                    }

                    int tripBudget = Integer.parseInt(eventModel.getTripBudget());
                    int calculatePercent = (totalExpenseSum * 100) / tripBudget;
                    binding.tvPercent.setText(String.valueOf(calculatePercent));
                   /* if (calculatePercent > 79) {
                        binding.progressBarId.setProgressTintList(ColorStateList.valueOf(Color.RED));
                    }*/
                    binding.progressBarId.setProgress(calculatePercent);
                    Log.e("TAG", "totalsum: " + calculatePercent);

                }
            }
        });
    }

    public void bind(TourEventModel eventModel) {
        binding.setEventModel(eventModel);
    }


}

