package com.example.tourmatenewproject.adapters;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tourmatenewproject.R;
import com.example.tourmatenewproject.callback.ExpenseDeleteListener;
import com.example.tourmatenewproject.callback.ExpenseEditListener;
import com.example.tourmatenewproject.databinding.ExpenseRowDesignBinding;
import com.example.tourmatenewproject.entities.TourExpenseModel;

import java.util.ArrayList;
import java.util.List;


public class TourExpenseAdapter extends RecyclerView.Adapter<TourExpenseAdapter.TourExpenseViewHolder> {


    private List<TourExpenseModel> expenseList;
    private ExpenseDeleteListener expenseDeleteListener;
    private ExpenseEditListener expenseEditListener;


    public TourExpenseAdapter(Activity activity) {

        expenseList = new ArrayList<>();
        expenseDeleteListener = (ExpenseDeleteListener) activity;
        expenseEditListener = (ExpenseEditListener) activity;
    }


    @NonNull
    @Override
    public TourExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ExpenseRowDesignBinding binding = ExpenseRowDesignBinding
                .inflate(LayoutInflater
                        .from(parent.getContext()), parent, false);
        return new TourExpenseViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TourExpenseViewHolder holder, int position) {
        final TourExpenseModel expenseModel = expenseList.get(position);
        holder.bind(expenseModel);

    }


    @SuppressLint("NotifyDataSetChanged")
    public void submitNewExpenseList(List<TourExpenseModel> expenseList) {
        this.expenseList = expenseList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return expenseList.size();
    }

    public class TourExpenseViewHolder extends RecyclerView.ViewHolder {
        private ExpenseRowDesignBinding binding;

        public TourExpenseViewHolder(ExpenseRowDesignBinding binding) {
            super(binding.getRoot());
            this.binding = binding;


            this.binding.imgBtnRowMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final int position = getAdapterPosition();
                    final TourExpenseModel expenseModel = expenseList.get(position);

                    PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
                    popupMenu.inflate(R.menu.rv_row_item_menu);
                    popupMenu.setForceShowIcon(true);
                    popupMenu.show();

                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.item_delete:
                                    expenseDeleteListener.onDeleteExpense(expenseModel);
                                    return true;
                                case R.id.item_update:
                                    expenseEditListener.onEditExpense(expenseModel);
                                    return true;
                                default:
                                    return true;
                            }

                        }
                    });

                }
            });

        }

        public void bind(TourExpenseModel expenseModel) {
            binding.setExpense(expenseModel);
        }

    }
}
