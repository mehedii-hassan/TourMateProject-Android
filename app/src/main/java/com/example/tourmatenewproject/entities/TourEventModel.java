package com.example.tourmatenewproject.entities;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "tbl_tour_events")
public class TourEventModel implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    int trip_id;
    @ColumnInfo(name = "event_name")
    private String tripName;
    @ColumnInfo(name = "event_description")
    private String tripDescription;
    @ColumnInfo(name = "event_start_location")
    private String tripStartLocation;
    @ColumnInfo(name = "event_destination")
    private String tripDestination;
    @ColumnInfo(name = "event_start_date")
    private String tripStartDate;
    @ColumnInfo(name = "event_end_date")
    private String tripEndDate;
    @ColumnInfo(name = "event_budget")
    private String tripBudget;
    private String event_create_date;
    private long how_many_days_left;


/*private String createDate;
    private String startDate;
    private String howDaysLeft;*/

    public TourEventModel(String tripName, String tripDescription, String tripStartLocation, String tripDestination, String tripStartDate, String tripEndDate, String tripBudget, String event_create_date, long how_many_days_left) {
        this.tripName = tripName;
        this.tripDescription = tripDescription;
        this.tripStartLocation = tripStartLocation;
        this.tripDestination = tripDestination;
        this.tripStartDate = tripStartDate;
        this.tripEndDate = tripEndDate;
        this.tripBudget = tripBudget;
        this.event_create_date = event_create_date;
        this.how_many_days_left = how_many_days_left;
    }

    @Ignore
    public TourEventModel(int trip_id, String tripName, String tripDescription, String tripStartLocation, String tripDestination, String tripStartDate, String tripEndDate, String tripBudget, String event_create_date, long how_many_days_left) {
        this.trip_id = trip_id;
        this.tripName = tripName;
        this.tripDescription = tripDescription;
        this.tripStartLocation = tripStartLocation;
        this.tripDestination = tripDestination;
        this.tripStartDate = tripStartDate;
        this.tripEndDate = tripEndDate;
        this.tripBudget = tripBudget;
        this.event_create_date = event_create_date;
        this.how_many_days_left = how_many_days_left;
    }


    protected TourEventModel(Parcel in) {
        trip_id = in.readInt();
        tripName = in.readString();
        tripDescription = in.readString();
        tripStartLocation = in.readString();
        tripDestination = in.readString();
        tripStartDate = in.readString();
        tripEndDate = in.readString();
        tripBudget = in.readString();
        event_create_date = in.readString();
        how_many_days_left = in.readLong();
    }

    public static final Creator<TourEventModel> CREATOR = new Creator<TourEventModel>() {
        @Override
        public TourEventModel createFromParcel(Parcel in) {
            return new TourEventModel(in);
        }

        @Override
        public TourEventModel[] newArray(int size) {
            return new TourEventModel[size];
        }
    };

    public int getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(int trip_id) {
        this.trip_id = trip_id;
    }

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public String getTripDescription() {
        return tripDescription;
    }

    public void setTripDescription(String tripDescription) {
        this.tripDescription = tripDescription;
    }

    public String getTripStartLocation() {
        return tripStartLocation;
    }

    public void setTripStartLocation(String tripStartLocation) {
        this.tripStartLocation = tripStartLocation;
    }

    public String getTripDestination() {
        return tripDestination;
    }

    public void setTripDestination(String tripDestination) {
        this.tripDestination = tripDestination;
    }

    public String getTripStartDate() {
        return tripStartDate;
    }

    public void setTripStartDate(String tripStartDate) {
        this.tripStartDate = tripStartDate;
    }

    public String getTripEndDate() {
        return tripEndDate;
    }

    public void setTripEndDate(String tripEndDate) {
        this.tripEndDate = tripEndDate;
    }

    public String getTripBudget() {
        return tripBudget;
    }

    public void setTripBudget(String tripBudget) {
        this.tripBudget = tripBudget;
    }

    public String getEvent_create_date() {
        return event_create_date;
    }

    public void setEvent_create_date(String event_create_date) {
        this.event_create_date = event_create_date;
    }

    public long getHow_many_days_left() {
        return how_many_days_left;
    }

    public void setHow_many_days_left(long how_many_days_left) {
        this.how_many_days_left = how_many_days_left;
    }

    @Override
    public String toString() {
        return "TourEventModel{" +
                "trip_id=" + trip_id +
                ", tripName='" + tripName + '\'' +
                ", tripDescription='" + tripDescription + '\'' +
                ", tripStartLocation='" + tripStartLocation + '\'' +
                ", tripDestination='" + tripDestination + '\'' +
                ", tripStartDate='" + tripStartDate + '\'' +
                ", tripEndDate='" + tripEndDate + '\'' +
                ", tripBudget='" + tripBudget + '\'' +
                ", event_create_date='" + event_create_date + '\'' +
                ", how_many_days_left=" + how_many_days_left +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeInt(trip_id);
        parcel.writeString(tripName);
        parcel.writeString(tripDescription);
        parcel.writeString(tripStartLocation);
        parcel.writeString(tripDestination);
        parcel.writeString(tripStartDate);
        parcel.writeString(tripEndDate);
        parcel.writeString(tripBudget);
        parcel.writeString(event_create_date);
        parcel.writeLong(how_many_days_left);
    }


}
