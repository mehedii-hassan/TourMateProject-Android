package com.example.tourmatenewproject.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.tourmatenewproject.R;
import com.example.tourmatenewproject.adapters.TourEventAdapter;
import com.example.tourmatenewproject.callback.EventDeleteListener;
import com.example.tourmatenewproject.callback.EventEditListener;
import com.example.tourmatenewproject.callback.OnEventItemClickListener;
import com.example.tourmatenewproject.databinding.ActivityMainBinding;
import com.example.tourmatenewproject.databinding.CustomAlertDialogBinding;
import com.example.tourmatenewproject.dialogfragments.TourEventDialogFragment;
import com.example.tourmatenewproject.entities.TourEventModel;
import com.example.tourmatenewproject.viewmodels.TourEventViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements EventDeleteListener, EventEditListener, OnEventItemClickListener {


    private ActivityMainBinding binding;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private TourEventViewModel viewModel;
    private androidx.appcompat.app.AlertDialog alertDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(TourEventViewModel.class);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //show toolbar effect---------------------------
        setSupportActionBar(binding.toolBarId);

        //set toggle icon------------------------------
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, binding.drawerLayoutId, R.string.nav_open, R.string.nav_close);
        binding.drawerLayoutId.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding.btnAddEvent.setOnClickListener(view -> {
            TourEventDialogFragment fragmentDialog = new TourEventDialogFragment();
            fragmentDialog.show(getSupportFragmentManager(), "EventDialog");
        });

        final TourEventAdapter tourEventAdapter = new TourEventAdapter(this);
        binding.rvTourEvent.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.rvTourEvent.setAdapter(tourEventAdapter);

        viewModel.getAllTourEvents().observe(this, new Observer<List<TourEventModel>>() {
            @Override
            public void onChanged(List<TourEventModel> eventModelList) {
                tourEventAdapter.submitNewEventList(eventModelList);

                if (eventModelList.size() > 0) {
                    binding.tvNewEvent.setVisibility(View.GONE);
                    binding.rvTourEvent.setVisibility(View.VISIBLE);
                } else {
                    binding.tvNewEvent.setVisibility(View.VISIBLE);
                    binding.rvTourEvent.setVisibility(View.GONE);
                }
            }
        });


        binding.navigationView.setNavigationItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.miHome:
                    startActivity(new Intent(this, MainActivity.class));
                    break;
                case R.id.miNearby:
                    startActivity(new Intent(this, NearbyActivity.class));
                    break;
                case R.id.miWeather:
                    startActivity(new Intent(this, WeatherActivity.class));
                    break;
            }
            return false;
        });


    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        showAlertDialog();
    }

    private void showAlertDialog() {
        CustomAlertDialogBinding binding = CustomAlertDialogBinding.inflate(getLayoutInflater());
        alertDialog = new androidx.appcompat.app.AlertDialog.Builder(this).setView(binding.getRoot()).create();
        alertDialog.show();
        binding.btnYes.setOnClickListener(view1 -> finish());
        binding.btnNo.setOnClickListener(view12 -> alertDialog.cancel());
    }

    @Override
    public void onDeleteEvent(TourEventModel tourEvent) {
        showAlertDialogForMenu(tourEvent);
    }

    private void showAlertDialogForMenu(TourEventModel tourEvent) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete  " + tourEvent.getTripName() + " ?");
        builder.setMessage("Are you sure to delete this Event?");
        builder.setIcon(R.drawable.ic_baseline_delete_forever_24);
        builder.setPositiveButton("Yes", (dialogInterface, i) -> viewModel.deleteEvent(tourEvent));
        builder.setNegativeButton("No", null);
        final AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onEditEvent(TourEventModel tourEvent) {
        //this.tourEvents = tourEvent;
        TourEventDialogFragment dialogFragment = new TourEventDialogFragment(tourEvent);
        dialogFragment.show(getSupportFragmentManager(), "EventDialog");
    }

    @Override
    public void onEventItemClicked(TourEventModel eventModel) {
        Intent intent = new Intent(MainActivity.this, EventDetailsActivity.class);
        intent.putExtra("eventModel", eventModel);
        startActivity(intent);

    }
}