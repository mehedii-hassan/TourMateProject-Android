package com.example.tourmatenewproject.entities.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.tourmatenewproject.entities.TourEventModel;
import com.example.tourmatenewproject.entities.UserModel;

import java.util.List;

public class UserSignUpWithTourEvents {

    @Embedded
    public UserModel userModel;
    @Relation(
            parentColumn = "email_id",
            entityColumn = "trip_id"
    )
    public List<TourEventModel> eventList;

}
