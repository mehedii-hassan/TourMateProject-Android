package com.example.tourmatenewproject.ui.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import com.example.tourmatenewproject.R;
import com.example.tourmatenewproject.adapters.TourImageAdapter;
import com.example.tourmatenewproject.callback.MomentsDeleteListener;
import com.example.tourmatenewproject.callback.MomentsEditListener;
import com.example.tourmatenewproject.databinding.ActivityMomentsBinding;
import com.example.tourmatenewproject.entities.TourEventModel;
import com.example.tourmatenewproject.entities.TourImageModel;
import com.example.tourmatenewproject.entities.UserModel;
import com.example.tourmatenewproject.viewmodels.TourImageViewModel;

import java.util.List;

public class MomentsActivity extends AppCompatActivity implements MomentsDeleteListener, MomentsEditListener {
    private ActivityMomentsBinding binding;
    private TourImageViewModel imageViewModel;
    private UserModel user;
    private TourEventModel eventModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageViewModel = new ViewModelProvider(this).get(TourImageViewModel.class);
        binding = ActivityMomentsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //get user from intent -----------------
        //user = getIntent().getParcelableExtra("user");
        eventModel = getIntent().getParcelableExtra("eventModel");


        TourImageAdapter tourImageAdapter = new TourImageAdapter(this);
        binding.rvMoments.setLayoutManager(new LinearLayoutManager(this));
        binding.rvMoments.setAdapter(tourImageAdapter);
        binding.rvMoments.setHasFixedSize(true);
        binding.rvMoments.setItemViewCacheSize(20);

        imageViewModel.getTripAllImages(eventModel.getTrip_id()).observe(this, new Observer<List<TourImageModel>>() {
            @Override
            public void onChanged(List<TourImageModel> imageList) {
                tourImageAdapter.submitNewImageList(imageList);
                Toast.makeText(MomentsActivity.this, "" + imageList.size(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onDeleteMomentsListener(TourImageModel tourImageModel) {

        showAlertDialogForMenu(tourImageModel);
    }

    private void showAlertDialogForMenu(TourImageModel imageModel) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete this image?");
        builder.setMessage("Are you sure to delete this image?");
        builder.setIcon(R.drawable.ic_baseline_delete_forever_24);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                imageViewModel.deleteImage(imageModel);

            }
        });
        builder.setNegativeButton("No", null);
        final AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onEditMomentsListener(TourImageModel tourImageModel) {

    }
}