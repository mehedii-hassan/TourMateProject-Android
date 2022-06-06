package com.example.tourmatenewproject.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.Toast;

import com.example.tourmatenewproject.R;
import com.example.tourmatenewproject.adapters.TourExpenseAdapter;
import com.example.tourmatenewproject.adapters.TourImageAdapter;
import com.example.tourmatenewproject.databinding.ActivityMomentsBinding;
import com.example.tourmatenewproject.entities.TourImageModel;
import com.example.tourmatenewproject.viewmodels.TourImageViewModel;

import java.util.List;

public class MomentsActivity extends AppCompatActivity {
    private ActivityMomentsBinding binding;
    private TourImageViewModel imageViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageViewModel = new ViewModelProvider(this).get(TourImageViewModel.class);
        binding = ActivityMomentsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        TourImageAdapter tourImageAdapter = new TourImageAdapter();
        binding.rvMoments.setLayoutManager(new LinearLayoutManager(this));
        binding.rvMoments.setAdapter(tourImageAdapter);
        binding.rvMoments.setHasFixedSize(true);
        binding.rvMoments.setItemViewCacheSize(20);

        imageViewModel.getAllImages().observe(this, new Observer<List<TourImageModel>>() {
            @Override
            public void onChanged(List<TourImageModel> imageList) {
                tourImageAdapter.submitNewImageList(imageList);
                Toast.makeText(MomentsActivity.this, "" + imageList.size(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}