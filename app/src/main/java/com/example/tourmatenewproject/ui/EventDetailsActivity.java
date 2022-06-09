package com.example.tourmatenewproject.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import com.example.tourmatenewproject.adapters.ExpandableListAdapter;
import com.example.tourmatenewproject.databinding.ActivityEventDetailsBinding;
import com.example.tourmatenewproject.dialogfragments.TourExpenseDialogFragment;
import com.example.tourmatenewproject.dialogfragments.TourMoreBudgetDialogFragment;
import com.example.tourmatenewproject.entities.TourEventModel;
import com.example.tourmatenewproject.expandable_list_view.ExpandableListDataItems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EventDetailsActivity extends AppCompatActivity {

    private ActivityEventDetailsBinding binding;
    private TourEventModel eventModel;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEventDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        eventModel = getIntent().getParcelableExtra("eventModel");
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

                    TourExpenseDialogFragment fragment = new TourExpenseDialogFragment();
                    fragment.show(getSupportFragmentManager(), "Tour Expense");

                } else if (selected.equalsIgnoreCase("View all expenses")) {

                    startActivity(new Intent(EventDetailsActivity.this, ExpenseListActivity.class));

                } else if (selected.equalsIgnoreCase("Add more budget")) {

                    TourMoreBudgetDialogFragment fragment = new TourMoreBudgetDialogFragment();
                    fragment.show(getSupportFragmentManager(), "Tour More Budget");

                } else if (selected.equalsIgnoreCase("View more budget list")) {

                    startActivity(new Intent(EventDetailsActivity.this, MoreBudgetActivity.class));

                } else if (selected.equalsIgnoreCase("Take a photo")) {
                    startActivity(new Intent(EventDetailsActivity.this, CameraActivity.class));

                } else if (selected.equalsIgnoreCase("View gallery")) {

                    startActivity(new Intent(EventDetailsActivity.this, GalleryActivity.class));

                } else if (selected.equalsIgnoreCase("View all moments")) {
                    startActivity(new Intent(EventDetailsActivity.this, MomentsActivity.class));

                }
                return true;
            }
        });


    }

    public void bind(TourEventModel eventModel) {
        binding.setEventModel(eventModel);
    }
}

