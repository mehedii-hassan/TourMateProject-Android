package com.example.tourmatenewproject.adapters;

import android.app.Activity;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tourmatenewproject.callback.OnGalleryImageItemClickListener;
import com.example.tourmatenewproject.databinding.GalleryRowDesignBinding;
import com.example.tourmatenewproject.entities.TourImageModel;

import java.util.ArrayList;
import java.util.List;

public class TourGalleryAdapter extends RecyclerView.Adapter<TourGalleryAdapter.GalleryViewHolder> {

    private List<TourImageModel> imageList;
    private OnGalleryImageItemClickListener imageItemClickListerner;

    public TourGalleryAdapter(Activity activity) {

        imageList = new ArrayList<>();
        imageItemClickListerner = (OnGalleryImageItemClickListener) activity;
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
        int imagePosition = holder.getAdapterPosition();
        holder.bind(imageModel);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("Position","imagePosition"+imagePosition);
                imageItemClickListerner.onImageItemClicked(view,imagePosition);

            }
        });


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

        public void bind(TourImageModel imageModel) {
            binding.imgViewGallery.setImageURI(Uri.parse(imageModel.getPhoto_path()));

        }
    }

}
