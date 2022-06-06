package com.example.tourmatenewproject.entities;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "tbl_tour_expense")
public class TourExpenseModel implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int tour_expense_id;

    private int trip_id;
    private int amount;
    private String comment;
    private String date;


    public TourExpenseModel(int amount, String comment, String date) {
        this.amount = amount;
        this.comment = comment;
        this.date = date;
    }

    @Ignore
    public TourExpenseModel(int trip_id, int amount, String comment) {
        this.trip_id = trip_id;
        this.amount = amount;
        this.comment = comment;
    }

    @Ignore
    public TourExpenseModel(int tour_expense_id, int amount, String comment, String date) {
        this.tour_expense_id = tour_expense_id;
        this.amount = amount;
        this.comment = comment;
        this.date = date;
    }


    protected TourExpenseModel(Parcel in) {
        tour_expense_id = in.readInt();
        trip_id = in.readInt();
        amount = in.readInt();
        comment = in.readString();
        date = in.readString();
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

    public int getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(int trip_id) {
        this.trip_id = trip_id;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeInt(tour_expense_id);
        parcel.writeInt(trip_id);
        parcel.writeInt(amount);
        parcel.writeString(comment);
        parcel.writeString(date);
    }

}
