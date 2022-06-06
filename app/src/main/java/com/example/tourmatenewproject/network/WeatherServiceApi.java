package com.example.tourmatenewproject.network;


import com.example.tourmatenewproject.entities.current.CurrentResponseModel;
import com.example.tourmatenewproject.entities.forecast.ForecastResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface WeatherServiceApi {
    @GET()
    Call<CurrentResponseModel> getCurrentData(@Url String endUrl);

    @GET()
    Call<ForecastResponseModel> getForecastData(@Url String endUrl);
}
