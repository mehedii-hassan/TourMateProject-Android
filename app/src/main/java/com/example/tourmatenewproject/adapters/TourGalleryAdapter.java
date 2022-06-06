package com.example.tourmatenewproject.adapters;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tourmatenewproject.databinding.GalleryRowDesignBinding;
import com.example.tourmatenewproject.entities.TourImageModel;

import java.util.ArrayList;
import java.util.List;

public class TourGalleryAdapter extends RecyclerView.Adapter<TourGalleryAdapter.GalleryViewHolder> {

    List<TourImageModel> imageList;

    public TourGalleryAdapter() {
        imageList = new ArrayList<>();
    }

    @NonNull
    @Override
    public GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        GalleryRowDesignBinding binding = GalleryRowDesignBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new GalleryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryViewHolder holder, int position) {
        final TourImageModel imageModel = imageList.get(position);
        //holder.bind(imageModel);
        holder.binding.imgViewGallery.setImageURI(Uri.parse(imageModel.getPhoto_path()));


    }

    public void submitNewImageList(List<TourImageModel> imageList) {

        this.imageList = imageList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    class GalleryViewHolder extends RecyclerView.ViewHolder {
        GalleryRowDesignBinding binding;

        public GalleryViewHolder(GalleryRowDesignBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
