package com.example.tourmatenewproject.entities;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "tbl_more_budget")
public class TourMoreBudgetModel implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int more_budget_id;
    @ColumnInfo(name = "trip_id")
    private int tripID;
    private int more_budget_amount;
    private String taking_from_where;
    private String date;

    public TourMoreBudgetModel(int tripID, int more_budget_amount, String taking_from_where, String date) {
        this.tripID = tripID;
        this.more_budget_amount = more_budget_amount;
        this.taking_from_where = taking_from_where;
        this.date = date;
    }

    @Ignore
    public TourMoreBudgetModel(int more_budget_id, int tripID, int more_budget_amount, String taking_from_where, String date) {
        this.more_budget_id = more_budget_id;
        this.tripID = tripID;
        this.more_budget_amount = more_budget_amount;
        this.taking_from_where = taking_from_where;
        this.date = date;
    }

    protected TourMoreBudgetModel(Parcel in) {
        more_budget_id = in.readInt();
        tripID = in.readInt();
        more_budget_amount = in.readInt();
        taking_from_where = in.readString();
        date = in.readString();
    }

    public static final Creator<TourMoreBudgetModel> CREATOR = new Creator<TourMoreBudgetModel>() {
        @Override
        public TourMoreBudgetModel createFromParcel(Parcel in) {
            return new TourMoreBudgetModel(in);
        }

        @Override
        public TourMoreBudgetModel[] newArray(int size) {
            return new TourMoreBudgetModel[size];
        }
    };

    public int getMore_budget_id() {
        return more_budget_id;
    }

    public void setMore_budget_id(int more_budget_id) {
        this.more_budget_id = more_budget_id;
    }

    public int getTripID() {
        return tripID;
    }

    public void setTripID(int tripID) {
        this.tripID = tripID;
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
                ", userID=" + tripID +
                ", more_budget_amount=" + more_budget_amount +
                ", taking_from_where='" + taking_from_where + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(more_budget_id);
        dest.writeInt(tripID);
        dest.writeInt(more_budget_amount);
        dest.writeString(taking_from_where);
        dest.writeString(date);
    }
}
