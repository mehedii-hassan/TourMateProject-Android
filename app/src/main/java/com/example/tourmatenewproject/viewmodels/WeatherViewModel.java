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
    private double lat, lon;
    private String cityName;
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
        this.lat = location.getLatitude();
        this.lon = location.getLongitude();
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void loadDataWithCityName() {
        loadForecastDataWithCityName();
        loadCurrentDataWithCityName();
    }

    public void loadData() {
        loadCurrentData();
        loadForecastData();
    }

    private void loadForecastData() {
        WeatherService.getService().getForecastWeatherData(lat, lon, Constants.WEATHER_API_KEY, "metric")
                .enqueue(new Callback<ForecastResponseModel>() {
                    @Override
                    public void onResponse(Call<ForecastResponseModel> call, Response<ForecastResponseModel> response) {
                        if (response.code() == 200) {
                            forecastLiveData.postValue(response.body());
                            // Log.e("TAG"," response "+response.body().getCity().getName());
                        }
                    }

                    @Override
                    public void onFailure(Call<ForecastResponseModel> call, Throwable t) {
                        Log.e("ForcastLoad", "onFailure: " + t.getLocalizedMessage());

                    }
                });
    }

    private void loadCurrentData() {
        WeatherService.getService().getCurrentWeatherData(lat, lon, Constants.WEATHER_API_KEY, "metric")
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

    //load current and forecast data with city name-------------
    private void loadForecastDataWithCityName() {
        WeatherService.getService().getForecastWeatherDataWithCityName(cityName, Constants.WEATHER_API_KEY, "metric")
                .enqueue(new Callback<ForecastResponseModel>() {
                    @Override
                    public void onResponse(Call<ForecastResponseModel> call, Response<ForecastResponseModel> response) {
                        if (response.code() == 200) {
                            forecastLiveData.postValue(response.body());
                            // Log.e("TAG"," response "+response.body().getCity().getName());
                        }
                    }

                    @Override
                    public void onFailure(Call<ForecastResponseModel> call, Throwable t) {
                        Log.e("ForcastLoad", "onFailure: " + t.getLocalizedMessage());

                    }
                });



    }

    private void loadCurrentDataWithCityName() {
        WeatherService.getService().getCurrentWeatherDataWithCityName(cityName, Constants.WEATHER_API_KEY, "metric")
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
