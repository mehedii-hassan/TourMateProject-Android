package com.example.tourmatenewproject.viewmodels;

import android.location.Location;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tourmatenewproject.entities.current.CurrentResponseModel;
import com.example.tourmatenewproject.entities.forecast.ForecastResponseModel;
import com.example.tourmatenewproject.network.WeatherService;
import com.example.tourmatenewproject.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherViewModel extends ViewModel {
    private final String TAG = WeatherViewModel.class.getSimpleName();
    private Location location;
    private MutableLiveData<CurrentResponseModel> currentLiveData = new MutableLiveData<>();
    private MutableLiveData<ForecastResponseModel> forecastLiveData = new MutableLiveData<>();

    public LiveData<CurrentResponseModel> getCurrentLiveDate() {
        return currentLiveData;

    }

    public LiveData<ForecastResponseModel> getForecastLiveDate() {
        return forecastLiveData;

    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void loadData() {
        loadCurrentData();
        loadForecastData();
    }

    private void loadForecastData() {

        //final String endUrl = String.format("forecast?q=dhaka&appid=8737784350ca4e46cdf3c7bd3f612588");
        final String endUrl = String.format("forecast?lat=%f&lon=%f&units=metric&appid=%s",
                location.getLatitude(), location.getLongitude(),
                Constants.WEATHER_API_KEY);
        WeatherService.getService().getForecastData(endUrl)
                .enqueue(new Callback<ForecastResponseModel>() {
                    @Override
                    public void onResponse(Call<ForecastResponseModel> call, Response<ForecastResponseModel> response) {
                        if (response.code() == 200) {
                            forecastLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<ForecastResponseModel> call, Throwable t) {
                        // Log.e("forecast fail", "forecast load failed");
                        //Log.e("tag1", "onFailure: " + t.getLocalizedMessage());
                        Log.e("ForcastLoad", "onFailure: " + t.getLocalizedMessage());
                    }
                });
    }

    private void loadCurrentData() {
        final String endUrl = String.format("weather?lat=%f&lon=%f&units=metric&appid=%s",
                location.getLatitude(), location.getLongitude(),
                Constants.WEATHER_API_KEY);
        WeatherService.getService().getCurrentData(endUrl)
                .enqueue(new Callback<CurrentResponseModel>() {
                    @Override
                    public void onResponse(Call<CurrentResponseModel> call, Response<CurrentResponseModel> response) {

                        if (response.code() == 200) {
                            currentLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<CurrentResponseModel> call, Throwable t) {

                        Log.e(TAG, "onFailure: " + t.getLocalizedMessage());
                    }
                });
    }
}
