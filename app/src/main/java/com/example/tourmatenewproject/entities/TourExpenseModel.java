package com.example.tourmatenewproject.entities;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "tbl_tour_expense")
public class TourExpenseModel implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int tour_expense_id;
    @ColumnInfo(name = "trip_id")
    private int tripId;
    private int amount;
    private String comment;
    private String date;
    private int totalExpense;

    public TourExpenseModel(int tripId, int amount, String comment, String date) {
        this.tripId = tripId;
        this.amount = amount;
        this.comment = comment;
        this.date = date;
    }

    @Ignore
    public TourExpenseModel(int tour_expense_id, int tripId, int amount, String comment, String date) {
        this.tour_expense_id = tour_expense_id;
        this.tripId = tripId;
        this.amount = amount;
        this.comment = comment;
        this.date = date;
    }

    protected TourExpenseModel(Parcel in) {
        tour_expense_id = in.readInt();
        tripId = in.readInt();
        amount = in.readInt();
        comment = in.readString();
        date = in.readString();
        totalExpense = in.readInt();
    }

    public static final Creator<TourExpenseModel> CREATOR = new Creator<TourExpenseModel>() {
        @Override
        public TourExpenseModel createFromParcel(Parcel in) {
            return new TourExpenseModel(in);
        }

        @Override
        public TourExpenseModel[] newArray(int size) {
            return new TourExpenseModel[size];
        }
    };

    public int getTour_expense_id() {
        return tour_expense_id;
    }

    public void setTour_expense_id(int tour_expense_id) {
        this.tour_expense_id = tour_expense_id;
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(int totalExpense) {
        this.totalExpense = totalExpense;
    }

    @Override
    public String toString() {
        return "TourExpenseModel{" +
                "tour_expense_id=" + tour_expense_id +
                ", userId=" + tripId +
                ", amount=" + amount +
                ", comment='" + comment + '\'' +
                ", date='" + date + '\'' +
                ", totalExpense=" + totalExpense +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(tour_expense_id);
        dest.writeInt(tripId);
        dest.writeInt(amount);
        dest.writeString(comment);
        dest.writeString(date);
        dest.writeInt(totalExpense);
    }
}
