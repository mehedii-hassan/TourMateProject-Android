package com.example.tourmatenewproject.entities;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tbl_user_info")
public class UserModel implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int userId;
    @ColumnInfo(name = "user_name")
    private String userName;
    @ColumnInfo(name = "user_email")
    private String userEmail;
    private String userPassword;
    private String userConfirmPass;

    public UserModel(String userName, String userEmail, String userPassword, String userConfirmPass) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userConfirmPass = userConfirmPass;
    }

    protected UserModel(Parcel in) {
        userId = in.readInt();
        userName = in.readString();
        userEmail = in.readString();
        userPassword = in.readString();
        userConfirmPass = in.readString();
    }

    public static final Creator<UserModel> CREATOR = new Creator<UserModel>() {
        @Override
        public UserModel createFromParcel(Parcel in) {
            return new UserModel(in);
        }

        @Override
        public UserModel[] newArray(int size) {
            return new UserModel[size];
        }
    };

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserConfirmPass() {
        return userConfirmPass;
    }

    public void setUserConfirmPass(String userConfirmPass) {
        this.userConfirmPass = userConfirmPass;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userConfirmPass='" + userConfirmPass + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(userId);
        dest.writeString(userName);
        dest.writeString(userEmail);
        dest.writeString(userPassword);
        dest.writeString(userConfirmPass);
    }
}
