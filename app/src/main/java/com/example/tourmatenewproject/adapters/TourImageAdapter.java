package com.example.tourmatenewproject.adapters;

import android.app.Activity;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tourmatenewproject.R;
import com.example.tourmatenewproject.callback.MomentsDeleteListener;
import com.example.tourmatenewproject.callback.MomentsEditListener;
import com.example.tourmatenewproject.databinding.ImageRowDesignBinding;
import com.example.tourmatenewproject.entities.TourExpenseModel;
import com.example.tourmatenewproject.entities.TourImageModel;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class TourImageAdapter extends RecyclerView.Adapter<TourImageAdapter.ImageViewHolder> {

    private List<TourImageModel> imageList;
    private MomentsDeleteListener momentsDeleteListener;
    private MomentsEditListener momentsEditListener;

    public TourImageAdapter(Activity activity) {
        imageList = new ArrayList<>();
        momentsDeleteListener = (MomentsDeleteListener) activity;
        momentsEditListener = (MomentsEditListener) activity;
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

            this.binding.fabImageRow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final int position = getAdapterPosition();
                    final TourImageModel imageModel = imageList.get(position);

                    PopupMenu popupMenu = new PopupMenu(v.getContext(), v);
                    popupMenu.inflate(R.menu.rv_row_item_menu);
                    popupMenu.setForceShowIcon(true);
                    popupMenu.show();

                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.item_delete:
                                    momentsDeleteListener.onDeleteMomentsListener(imageModel);
                                    return true;
                                case R.id.item_update:
                                    momentsEditListener.onEditMomentsListener(imageModel);
                                    return true;
                                default:
                                    return true;
                            }
                        }
                    });
                }
            });
        }


        public void bind(TourImageModel imageModel) {
            binding.imgViewIRD.setImageURI(Uri.parse(imageModel.getPhoto_path()));
        }
    }
}
