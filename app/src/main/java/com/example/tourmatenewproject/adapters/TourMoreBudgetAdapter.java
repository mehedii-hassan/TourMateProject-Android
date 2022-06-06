package com.example.tourmatenewproject.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tourmatenewproject.R;
import com.example.tourmatenewproject.callback.MoreBudgetDeleteListener;
import com.example.tourmatenewproject.callback.MoreBudgetEditListener;
import com.example.tourmatenewproject.databinding.MoreBudgetRowDesignBinding;
import com.example.tourmatenewproject.entities.TourMoreBudgetModel;

import java.util.ArrayList;
import java.util.List;

public class TourMoreBudgetAdapter extends RecyclerView.Adapter<TourMoreBudgetAdapter.MoreBudgetViewHolder> {

    private List<TourMoreBudgetModel> moreBudgetList;
    private MoreBudgetDeleteListener moreBudgetDeleteListener;
    private MoreBudgetEditListener moreBudgetEditListener;


    public TourMoreBudgetAdapter(Activity activity) {
        moreBudgetList = new ArrayList<>();
        moreBudgetDeleteListener = (MoreBudgetDeleteListener) activity;
        moreBudgetEditListener = (MoreBudgetEditListener) activity;
    }

    @NonNull
    @Override
    public MoreBudgetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MoreBudgetRowDesignBinding binding = MoreBudgetRowDesignBinding
                .inflate(LayoutInflater
                        .from(parent.getContext()), parent, false);
        return new MoreBudgetViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MoreBudgetViewHolder holder, int position) {

        final TourMoreBudgetModel moreBudgetModel = moreBudgetList.get(position);
        holder.bind(moreBudgetModel);
    }

    public void submitNewExpenseList(List<TourMoreBudgetModel> moreBudgetList) {
        this.moreBudgetList = moreBudgetList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return moreBudgetList.size();
    }

    class MoreBudgetViewHolder extends RecyclerView.ViewHolder {
        private MoreBudgetRowDesignBinding binding;

        public MoreBudgetViewHolder(MoreBudgetRowDesignBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            this.binding.fabRowMenuMB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final int position = getAdapterPosition();
                    final TourMoreBudgetModel moreBudgetModel = moreBudgetList.get(position);

                    PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
                    popupMenu.inflate(R.menu.rv_row_item_menu);
                    popupMenu.setForceShowIcon(true);
                    popupMenu.show();

                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.item_delete:
                                    moreBudgetDeleteListener.onDeleteMoreBudget(moreBudgetModel);
                                    return true;
                                case R.id.item_update:
                                    moreBudgetEditListener.onEditMoreBudget(moreBudgetModel);
                                    return true;
                                default:
                                    return true;
                            }

                        }
                    });
                }

            });
        }

        public void bind(TourMoreBudgetModel moreBudgetModel) {
            binding.setMoreBudgetModel(moreBudgetModel);
        }
    }
}
