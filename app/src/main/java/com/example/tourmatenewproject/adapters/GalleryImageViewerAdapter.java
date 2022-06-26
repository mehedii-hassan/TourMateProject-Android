package com.example.tourmatenewproject.adapters;

import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tourmatenewproject.databinding.VpImageHolderBinding;
import com.example.tourmatenewproject.entities.TourImageModel;

import java.util.ArrayList;
import java.util.List;

public class GalleryImageViewerAdapter extends RecyclerView.Adapter<GalleryImageViewerAdapter.ImageViewerViewHolder> {
    private List<TourImageModel> imageList;
    int imagePosition;



    public GalleryImageViewerAdapter(int imagePosition) {
        imageList = new ArrayList<>();
        this.imagePosition = imagePosition;


    }

    @NonNull
    @Override
    public ImageViewerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        VpImageHolderBinding binding = VpImageHolderBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ImageViewerViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewerViewHolder holder, int position) {
        Log.e("model", "" + imageList.size() + " " + position);

        holder.binding.ivImageViewer.setImageURI(Uri.parse(imageList.get(position).getPhoto_path()));

    }

    public void submitNewImageList(List<TourImageModel> imageList) {
        this.imageList = imageList;
        //Log.e("imageList", "" + imageList.size() + model.getPhoto_path());
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    class ImageViewerViewHolder extends RecyclerView.ViewHolder {
        private VpImageHolderBinding binding;

        public ImageViewerViewHolder(VpImageHolderBinding binding) {

            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
