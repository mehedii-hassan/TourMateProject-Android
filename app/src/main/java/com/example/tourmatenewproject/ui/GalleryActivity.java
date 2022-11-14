package com.example.tourmatenewproject.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.tourmatenewproject.adapters.TourGalleryAdapter;
import com.example.tourmatenewproject.callback.OnGalleryImageItemClickListener;
import com.example.tourmatenewproject.databinding.ActivityPhotoGalleryBinding;
import com.example.tourmatenewproject.entities.TourImageModel;
import com.example.tourmatenewproject.entities.UserModel;
import com.example.tourmatenewproject.viewmodels.TourImageViewModel;

import java.util.List;

public class GalleryActivity extends AppCompatActivity implements OnGalleryImageItemClickListener {

    private ActivityPhotoGalleryBinding binding;
    private TourImageViewModel imageViewModel;
    private UserModel user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageViewModel = new ViewModelProvider(this).get(TourImageViewModel.class);
        binding = ActivityPhotoGalleryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //get user from intent------------
        user = getIntent().getParcelableExtra("user");

        TourGalleryAdapter tourGalleryAdapter = new TourGalleryAdapter(this);
        binding.rvGallery.setLayoutManager(new GridLayoutManager(this, 4));
        binding.rvGallery.setAdapter(tourGalleryAdapter);


        imageViewModel.getUserAllImages(user.getUserId()).observe(this, new Observer<List<TourImageModel>>() {
            @Override
            public void onChanged(List<TourImageModel> imageList) {
                tourGalleryAdapter.submitNewImageList(imageList);
            }
        });
    }

    @Override
    public void onImageItemClicked(View v, int position) {
        Intent intent = new Intent(this, GalleryImageViewer.class);
        intent.putExtra("position", position);
        intent.putExtra("user", user);
        startActivity(intent);
    }


   /* @Override
    public void onImageItemClicked(View view, int position) {
        Intent intent = new Intent(this,GalleryImageViewer.class);
        intent.putExtra("position",position);
        startActivity(intent);
    }*/
}