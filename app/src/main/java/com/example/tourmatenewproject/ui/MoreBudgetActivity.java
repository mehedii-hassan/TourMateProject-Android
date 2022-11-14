package com.example.tourmatenewproject.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.example.tourmatenewproject.R;
import com.example.tourmatenewproject.adapters.TourMoreBudgetAdapter;
import com.example.tourmatenewproject.callback.MoreBudgetDeleteListener;
import com.example.tourmatenewproject.callback.MoreBudgetEditListener;
import com.example.tourmatenewproject.databinding.ActivityMoreBudgetBinding;
import com.example.tourmatenewproject.dialogfragments.TourExpenseDialogFragment;
import com.example.tourmatenewproject.dialogfragments.TourMoreBudgetDialogFragment;
import com.example.tourmatenewproject.entities.TourEventModel;
import com.example.tourmatenewproject.entities.TourExpenseModel;
import com.example.tourmatenewproject.entities.TourMoreBudgetModel;
import com.example.tourmatenewproject.entities.UserModel;
import com.example.tourmatenewproject.viewmodels.TourMoreBudgetViewModel;

import java.util.List;

public class MoreBudgetActivity extends AppCompatActivity implements MoreBudgetDeleteListener, MoreBudgetEditListener {
    private ActivityMoreBudgetBinding binding;
    private TourMoreBudgetViewModel moreBudgetViewModel;
    private UserModel user;
    private TourEventModel eventModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        moreBudgetViewModel = new ViewModelProvider(this)
                .get(TourMoreBudgetViewModel.class);
        binding = ActivityMoreBudgetBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //get user from intent -----------------
        //user = getIntent().getParcelableExtra("user");
        eventModel = getIntent().getParcelableExtra("eventModel");

        TourMoreBudgetAdapter tourMoreBudgetAdapter = new TourMoreBudgetAdapter(this);
        binding.recyclerViewMB.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerViewMB.setAdapter(tourMoreBudgetAdapter);

        moreBudgetViewModel.getTripAllMoreBudget(eventModel.getTrip_id()).observe(this, new Observer<List<TourMoreBudgetModel>>() {
            @Override
            public void onChanged(List<TourMoreBudgetModel> moreBudgetList) {
                tourMoreBudgetAdapter.submitNewExpenseList(moreBudgetList);

                if (moreBudgetList.size() > 0) {
                    binding.tvEmptyMB.setVisibility(View.GONE);
                    binding.recyclerViewMB.setVisibility(View.VISIBLE);
                } else {
                    binding.tvEmptyMB.setVisibility(View.VISIBLE);
                    binding.recyclerViewMB.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void onDeleteMoreBudget(TourMoreBudgetModel moreBudgetModel) {
        showAlertDialogForMenu(moreBudgetModel);
    }

    private void showAlertDialogForMenu(TourMoreBudgetModel moreBudgetModel) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete  this more budget?");
        builder.setMessage("Are you sure to delete this more budget?");
        builder.setIcon(R.drawable.ic_baseline_delete_forever_24);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                moreBudgetViewModel.deleteMoreBudget(moreBudgetModel);

            }
        });
        builder.setNegativeButton("No", null);
        final AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onEditMoreBudget(TourMoreBudgetModel moreBudgetModel) {

        TourMoreBudgetDialogFragment dialogFragment = new TourMoreBudgetDialogFragment(moreBudgetModel);
        dialogFragment.show(getSupportFragmentManager(), "ExpenseDialog");

    }
}