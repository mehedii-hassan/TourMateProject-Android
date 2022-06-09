package com.example.tourmatenewproject.entities;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "tbl_tour_image")
public class TourImageModel implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int img_id;
    private String photo_path;
    @Ignore
    private int trip_id;


    public TourImageModel(String photo_path) {
        this.photo_path = photo_path;
    }


    @Ignore
    public TourImageModel(String photo_path, int img_id) {
        this.photo_path = photo_path;
        this.img_id = img_id;
    }

    @Ignore
    public TourImageModel(String photo_path, int img_id, int trip_id) {
        this.photo_path = photo_path;
        this.img_id = img_id;
        this.trip_id = trip_id;
    }

    protected TourImageModel(Parcel in) {
        img_id = in.readInt();
        photo_path = in.readString();
        trip_id = in.readInt();
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

    public String getPhoto_path() {
        return photo_path;
    }

    public void setPhoto_path(String photo_path) {
        this.photo_path = photo_path;
    }

    public int getImg_id() {
        return img_id;
    }

    public void setImg_id(int img_id) {
        this.img_id = img_id;
    }

    public int getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(int trip_id) {
        this.trip_id = trip_id;
    }

    @Override
    public String toString() {
        return "TourImageModel{" +
                "photo_path='" + photo_path + '\'' +
                ", img_id=" + img_id +
                ", trip_id=" + trip_id +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(img_id);
        parcel.writeString(photo_path);
        parcel.writeInt(trip_id);
    }
}
