package com.example.tourmatenewproject.adapters;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tourmatenewproject.databinding.ImageRowDesignBinding;
import com.example.tourmatenewproject.entities.TourImageModel;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class TourImageAdapter extends RecyclerView.Adapter<TourImageAdapter.ImageViewHolder> {

    private List<TourImageModel> imageList;

    public TourImageAdapter() {
        imageList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ImageRowDesignBinding binding = ImageRowDesignBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new ImageViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        final TourImageModel imageModel = imageList.get(position);
        //holder.bind(imageModel);
        holder.binding.imgViewIRD.setImageURI(Uri.parse(imageModel.getPhoto_path()));
    }

    public void submitNewImageList(List<TourImageModel> imageList) {

        this.imageList = imageList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {
        private ImageRowDesignBinding binding;

        public ImageViewHolder(ImageRowDesignBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

        public void bind(TourImageModel imageModel) {
            binding.imgViewIRD.setImageURI(Uri.parse(imageModel.getPhoto_path()));
        }
    }
}
