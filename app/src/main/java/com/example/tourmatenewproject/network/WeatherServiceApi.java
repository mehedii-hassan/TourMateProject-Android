package com.example.tourmatenewproject.network;


import com.example.tourmatenewproject.entities.current.CurrentResponseModel;
import com.example.tourmatenewproject.entities.forecast.ForecastResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface WeatherServiceApi {
    @GET()
    Call<CurrentResponseModel> getCurrentData(@Url String endUrl);

    @GET()
    Call<ForecastResponseModel> getForecastData(@Url String endUrl);

    @GET("weather")
    Call<CurrentResponseModel> getCurrentWeatherData(
            @Query("lat") double lat,
            @Query("lon") double lon,
            @Query("appid") String apikey,
            @Query("units") String units
    );
    @GET("forecast")
    Call<ForecastResponseModel> getForecastWeatherData(
            @Query("lat") double lat,
            @Query("lon") double lon,
            @Query("appid") String apikey,
            @Query("units") String units
    );

    @GET("weather")
    Call<CurrentResponseModel> getCurrentWeatherDataWithCityName(
            @Query("q") String city,
            @Query("appid") String apikey,
            @Query("units") String units
    );

    @GET("forecast")
    Call<ForecastResponseModel> getForecastWeatherDataWithCityName(
            @Query("q") String city,
            @Query("appid") String apikey,
            @Query("units") String units
    );
}
