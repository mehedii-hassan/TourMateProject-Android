package com.example.tourmatenewproject.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

import com.example.tourmatenewproject.R;
import com.example.tourmatenewproject.adapters.TourGalleryAdapter;
import com.example.tourmatenewproject.adapters.TourImageAdapter;
import com.example.tourmatenewproject.callback.OnGalleryImageItemClickListerner;
import com.example.tourmatenewproject.databinding.ActivityPhotoGalleryBinding;
import com.example.tourmatenewproject.entities.TourImageModel;
import com.example.tourmatenewproject.viewmodels.TourImageViewModel;

import java.util.List;

public class GalleryActivity extends AppCompatActivity implements OnGalleryImageItemClickListerner {

    private ActivityPhotoGalleryBinding binding;
    private TourImageViewModel imageViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageViewModel = new ViewModelProvider(this).get(TourImageViewModel.class);
        binding = ActivityPhotoGalleryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        TourGalleryAdapter tourGalleryAdapter = new TourGalleryAdapter(this);
        binding.rvGallery.setLayoutManager(new GridLayoutManager(this, 4));
        binding.rvGallery.setAdapter(tourGalleryAdapter);


        imageViewModel.getAllImages().observe(this, new Observer<List<TourImageModel>>() {
            @Override
            public void onChanged(List<TourImageModel> imageList) {
                tourGalleryAdapter.submitNewImageList(imageList);
            }
        });
    }


    @Override
    public void onImageItemClicked(int position) {
        Intent intent = new Intent(this,GalleryImageViewer.class);
        intent.putExtra("position",position);
        startActivity(intent);
    }
}