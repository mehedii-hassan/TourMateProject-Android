package com.example.tourmatenewproject.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.tourmatenewproject.R;
import com.example.tourmatenewproject.adapters.TourExpenseAdapter;
import com.example.tourmatenewproject.callback.ExpenseDeleteListener;
import com.example.tourmatenewproject.callback.ExpenseEditListener;
import com.example.tourmatenewproject.databinding.ActivityExpenseListBinding;
import com.example.tourmatenewproject.dialogfragments.TourEventDialogFragment;
import com.example.tourmatenewproject.dialogfragments.TourExpenseDialogFragment;
import com.example.tourmatenewproject.entities.TourEventModel;
import com.example.tourmatenewproject.entities.TourExpenseModel;
import com.example.tourmatenewproject.entities.UserModel;
import com.example.tourmatenewproject.viewmodels.TourExpenseViewModel;

import java.util.List;

public class ExpenseListActivity extends AppCompatActivity implements ExpenseEditListener, ExpenseDeleteListener {

    private ActivityExpenseListBinding binding;
    private TourExpenseViewModel expenseViewModel;
    private UserModel user;
    private TourEventModel tourEventModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        expenseViewModel = new ViewModelProvider(this)
                .get(TourExpenseViewModel.class);

        binding = ActivityExpenseListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //get user from intent--------------
        //user = getIntent().getParcelableExtra("user");
        tourEventModel = getIntent().getParcelableExtra("eventModel");

        binding.fabEL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TourExpenseAdapter tourExpenseAdapter = new TourExpenseAdapter(this);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(tourExpenseAdapter);

        expenseViewModel.getTripAllExpenses(tourEventModel.getTrip_id()).observe(this, new Observer<List<TourExpenseModel>>() {
            @Override
            public void onChanged(List<TourExpenseModel> expenseList) {
                tourExpenseAdapter.submitNewExpenseList(expenseList);

                if (expenseList.size() > 0) {
                    binding.tvEmpty.setVisibility(View.GONE);
                    binding.recyclerView.setVisibility(View.VISIBLE);
                } else {
                    binding.tvEmpty.setVisibility(View.VISIBLE);
                    binding.recyclerView.setVisibility(View.GONE);
                }
            }
        });


    }

    @Override
    public void onDeleteExpense(TourExpenseModel tourExpense) {
        showAlertDialogForMenu(tourExpense);
    }

    private void showAlertDialogForMenu(TourExpenseModel tourExpense) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete  " + tourExpense.getComment() + " ?");
        builder.setMessage("Are you sure to delete this Expense?");
        builder.setIcon(R.drawable.ic_baseline_delete_forever_24);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                expenseViewModel.deleteExpense(tourExpense);

            }
        });
        builder.setNegativeButton("No", null);
        final AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onEditExpense(TourExpenseModel tourExpense) {
        TourExpenseDialogFragment dialogFragment = new TourExpenseDialogFragment(tourExpense);
        dialogFragment.show(getSupportFragmentManager(), "ExpenseDialog");

    }
}