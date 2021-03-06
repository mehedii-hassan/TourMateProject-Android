package com.example.tourmatenewproject.ui;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.tourmatenewproject.R;
import com.example.tourmatenewproject.adapters.ForecastWeatherAdapter;
import com.example.tourmatenewproject.adapters.TourExpenseAdapter;
import com.example.tourmatenewproject.databinding.ActivityWeatherBinding;
import com.example.tourmatenewproject.entities.current.CurrentResponseModel;
import com.example.tourmatenewproject.entities.forecast.ForecastResponseModel;
import com.example.tourmatenewproject.entities.forecast.ListItem;
import com.example.tourmatenewproject.utils.Constants;
import com.example.tourmatenewproject.utils.LocationPermissionService;
import com.example.tourmatenewproject.utils.WeatherHelperFunctions;
import com.example.tourmatenewproject.viewmodels.WeatherViewModel;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class WeatherActivity extends AppCompatActivity {
    private ActivityWeatherBinding binding;
    private final String TAG = WeatherActivity.class.getSimpleName();
    private WeatherViewModel weatherViewModel;
    private FusedLocationProviderClient providerClient;
    private ActivityResultLauncher<String> launcher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), result -> {
                if (result) {
                    //detect user location
                    detectUserLocation();
                } else {
                    //show dialog and explain why you need this permission
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWeatherBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //viewModel initialize before check location permission----------
        weatherViewModel = new ViewModelProvider(this)
                .get(WeatherViewModel.class);

        providerClient = LocationServices
                .getFusedLocationProviderClient(this);
        checkLocationPermission();

        ForecastWeatherAdapter forecastWeatherAdapter = new ForecastWeatherAdapter();
        binding.recyclerViewForecast.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.recyclerViewForecast.setAdapter(forecastWeatherAdapter);

        weatherViewModel.getCurrentLiveDate().observe(this, currentResponseModel -> {
            updateUI(currentResponseModel);
        });

        weatherViewModel.getForecastLiveDate().observe(this, new Observer<ForecastResponseModel>() {
            @Override
            public void onChanged(ForecastResponseModel forecastResponseModel) {
                forecastWeatherAdapter.submitNewForecastList(forecastResponseModel);
            }
        });
    }

    private void updateUI(CurrentResponseModel currentResponseModel) {
        binding.tvCurrentDate.setText(WeatherHelperFunctions.getFormattedDateTime(currentResponseModel.getDt(), "MMM dd, yyyy hh.mm aa"));
        binding.tvCurrentAdress.setText(currentResponseModel.getName() + "," + currentResponseModel.getSys().getCountry());
        binding.tvCurrentTemp.setText(
                String.format("%.0f\u00B0 C", currentResponseModel.getMain().getTemp())
        );

        binding.tvCurrentFeelsLike.setText(
                String.format("Feels like %.0f\u00B0 C", currentResponseModel.getMain().getFeelsLike())
        );
        binding.tvCurrentMaxMin.setText(
                String.format("Max %.0f\u00B0 Min %.0f\u00b0", currentResponseModel.getMain().getTempMax(), currentResponseModel.getMain().getTempMin())
        );

        final String iconUrl = Constants.ICON_PREFIX + currentResponseModel.getWeather().get(0).getIcon() + Constants.ICON_SUFFIX;
        Picasso.get().load(iconUrl).into(binding.ivCurrentIcon);
        binding.tvCurrentCondition.setText(currentResponseModel.getWeather().get(0).getDescription());
        binding.tvCurrentHumidity.setText("Humidity " + currentResponseModel.getMain().getHumidity() + "%");
        binding.tvCurrentPressure.setText("Pressure " + currentResponseModel.getMain().getPressure() + "hPa");

    }

    private void checkLocationPermission() {
        if (LocationPermissionService.isLocationPermissionGranted(this)) {
            //detect user location
            detectUserLocation();
        } else {
            LocationPermissionService.requestLocationPermission(launcher);
        }
    }

    private void detectUserLocation() {
        providerClient.getLastLocation()
                .addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location == null) return;
                        // double lat = location.getLatitude();
                        // double lng = location.getLongitude();
                        weatherViewModel.setLocation(location);
                        weatherViewModel.loadData();

                        //Log.e("WeatherApp","Lat:"+lat+",lon:"+lng);
                    }
                });
    }
}