package com.example.tourmatenewproject.utils;

import com.example.tourmatenewproject.R;
import com.example.tourmatenewproject.entities.PlaceModel;

import java.util.ArrayList;
import java.util.Arrays;

public class Constants {
    public static final String WEATHER_API_KEY = "8737784350ca4e46cdf3c7bd3f612588";
    public static final String ICON_PREFIX = "https://openweathermap.org/img/wn/";
    public static final String ICON_SUFFIX = "@2x.png";

    public static ArrayList<PlaceModel> placesNameList = new ArrayList<>(
            Arrays.asList(
                    new PlaceModel(1, R.drawable.ic_restaurant, "Restaurant", "restaurant"),
                    new PlaceModel(2, R.drawable.ic_atm, "ATM", "atm"),
                    new PlaceModel(3, R.drawable.ic_gas_station, "Gas", "gas_station"),
                    new PlaceModel(4, R.drawable.ic_shopping_cart, "Groceries", "supermarket"),
                    new PlaceModel(5, R.drawable.ic_hotel, "Hotels", "hotel"),
                    new PlaceModel(6, R.drawable.ic_pharmacy, "Pharmacies", "pharmacy"),
                    new PlaceModel(7, R.drawable.ic_hospital, "Hospitals & Clinics", "hospital"),
                    new PlaceModel(8, R.drawable.ic_car_wash, "Car Wash", "car_wash"),
                    new PlaceModel(9, R.drawable.ic_saloon, "Beauty Salons", "beauty_salon")

            )
    );
}
