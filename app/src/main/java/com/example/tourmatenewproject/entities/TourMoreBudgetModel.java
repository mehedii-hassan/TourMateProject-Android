package com.example.tourmatenewproject.entities;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "tbl_more_budget")
public class TourMoreBudgetModel {
    @PrimaryKey(autoGenerate = true)
    private int more_budget_id;
    private int more_budget_amount;
    private String taking_from_where;
    private String date;

    public TourMoreBudgetModel(int more_budget_amount, String taking_from_where, String date) {
        this.more_budget_amount = more_budget_amount;
        this.taking_from_where = taking_from_where;
        this.date = date;
    }

    @Ignore
    public TourMoreBudgetModel(int more_budget_id, int more_budget_amount, String taking_from_where, String date) {
        this.more_budget_id = more_budget_id;
        this.more_budget_amount = more_budget_amount;
        this.taking_from_where = taking_from_where;
        this.date = date;
    }


    public int getMore_budget_id() {
        return more_budget_id;
    }

    public void setMore_budget_id(int more_budget_id) {
        this.more_budget_id = more_budget_id;
    }

    public int getMore_budget_amount() {
        return more_budget_amount;
    }

    public void setMore_budget_amount(int more_budget_amount) {
        this.more_budget_amount = more_budget_amount;
    }

    public String getTaking_from_where() {
        return taking_from_where;
    }

    public void setTaking_from_where(String taking_from_where) {
        this.taking_from_where = taking_from_where;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "TourMoreBudgetModel{" +
                "more_budget_id=" + more_budget_id +
                ", more_budget_amount=" + more_budget_amount +
                ", taking_from_where='" + taking_from_where + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
