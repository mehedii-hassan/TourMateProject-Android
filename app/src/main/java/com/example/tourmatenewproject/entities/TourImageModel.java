package com.example.tourmatenewproject.entities;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tbl_tour_image")
public class TourImageModel implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int img_id;
    @ColumnInfo(name = "trip_id")
    private int tripID;
    private String photo_path;

    public TourImageModel(int tripID, String photo_path) {
        this.tripID = tripID;
        this.photo_path = photo_path;
    }

    protected TourImageModel(Parcel in) {
        img_id = in.readInt();
        tripID = in.readInt();
        photo_path = in.readString();
    }

    public static final Creator<TourImageModel> CREATOR = new Creator<TourImageModel>() {
        @Override
        public TourImageModel createFromParcel(Parcel in) {
            return new TourImageModel(in);
        }

        @Override
        public TourImageModel[] newArray(int size) {
            return new TourImageModel[size];
        }
    };

    public int getImg_id() {
        return img_id;
    }

    public void setImg_id(int img_id) {
        this.img_id = img_id;
    }

    public int getTripID() {
        return tripID;
    }

    public void setTripID(int tripID) {
        this.tripID = tripID;
    }

    public String getPhoto_path() {
        return photo_path;
    }

    public void setPhoto_path(String photo_path) {
        this.photo_path = photo_path;
    }

    @Override
    public String toString() {
        return "TourImageModel{" +
                "img_id=" + img_id +
                ", userID=" + tripID +
                ", photo_path='" + photo_path + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(img_id);
        dest.writeInt(tripID);
        dest.writeString(photo_path);
    }
}

