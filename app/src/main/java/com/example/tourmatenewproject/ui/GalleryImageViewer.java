package com.example.tourmatenewproject.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.tourmatenewproject.adapters.GalleryImageViewerAdapter;
import com.example.tourmatenewproject.databinding.ActivityGalleryImageViewerBinding;
import com.example.tourmatenewproject.entities.TourImageModel;
import com.example.tourmatenewproject.viewmodels.TourImageViewModel;

import java.util.List;

public class GalleryImageViewer extends AppCompatActivity {

    private ActivityGalleryImageViewerBinding binding;
    private TourImageViewModel imageViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        imageViewModel = new ViewModelProvider(this)
                .get(TourImageViewModel.class);
        binding = ActivityGalleryImageViewerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // TourImageModel model= getIntent().getParcelableExtra("model");
        int galleryImagePosition = getIntent().getIntExtra("position", 0);
        binding.vpImageViewer.setCurrentItem(galleryImagePosition,true);



        GalleryImageViewerAdapter imageViewerAdapter = new GalleryImageViewerAdapter(galleryImagePosition);
        //binding.vpImageViewer.setLayoutManager(new LinearLayoutManager(this));
        binding.vpImageViewer.setAdapter(imageViewerAdapter);



        imageViewModel.getAllImages().observe(this, new Observer<List<TourImageModel>>() {
            @Override
            public void onChanged(List<TourImageModel> imageList) {

                imageViewerAdapter.submitNewImageList(imageList);
            }
        });


    }
}