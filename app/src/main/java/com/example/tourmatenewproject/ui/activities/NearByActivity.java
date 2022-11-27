package com.example.tourmatenewproject.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.app.ActivityCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.WindowManager;
import com.example.tourmatenewproject.R;
import com.example.tourmatenewproject.databinding.ActivityNearbyBinding;
import com.example.tourmatenewproject.entities.PlaceModel;
import com.example.tourmatenewproject.utils.Constants;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.chip.Chip;

public class NearByActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap googleMap;
    private ActivityNearbyBinding binding;
    private FusedLocationProviderClient client;

    private LocationRequest locationRequest;
    private LocationCallback locationCallback;
    private static final int Request_code = 101;
    private double lat, lon;
    private boolean isTrafficEnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNearbyBinding.inflate(getLayoutInflater());
        //Remove status bar & make activity fullScreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(binding.getRoot());

        client = LocationServices.getFusedLocationProviderClient(this);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapId);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }


        binding.btnMapTypeGM.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(v.getContext(), v);
            popupMenu.inflate(R.menu.map_type_menu);
            popupMenu.setForceShowIcon(true);
            popupMenu.show();
            popupMenu.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()) {
                    case R.id.mapDefault:
                        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                        break;
                    case R.id.mapSatellite:
                        googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                        break;
                    case R.id.mapTerrain:
                        googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                        break;
                }
                return true;
            });
        });
        binding.btnEnableTrafic.setOnClickListener(v -> {
            if (isTrafficEnable) {
                if (googleMap != null) {
                    googleMap.setTrafficEnabled(false);
                    isTrafficEnable = false;
                }
            } else {

                if (googleMap != null) {
                    googleMap.setTrafficEnabled(true);
                    isTrafficEnable = true;
                }
            }

        });
        binding.btnCurrentLocation.setOnClickListener(v -> onMapReady(googleMap));

        for (PlaceModel placeModel : Constants.placesNameList) {
            Chip chip = new Chip(this);
            chip.setText(placeModel.getName());
            chip.setId(placeModel.getId());
            chip.setPadding(8, 8, 8, 8);
            chip.setTextColor(getResources().getColor(R.color.white, null));
            chip.setChipBackgroundColor(getResources().getColorStateList(R.color.primaryColor, null));
            chip.setChipIcon(ResourcesCompat.getDrawable(getResources(), placeModel.getDrawableId(), null));
            chip.setCheckable(true);
            chip.setCheckedIconVisible(false);
            binding.placesGroup.addView(chip);
        }

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        this.googleMap = googleMap;
        getCurrentLocation();
        LatLng currentLocation = new LatLng(lat, lon);
        googleMap.addMarker(new MarkerOptions()
                .position(currentLocation)
                .title("My location")
        );
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation));


    }

    private void getCurrentLocation() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_code);

        }

        locationRequest = LocationRequest.create();
        locationRequest.setInterval(60000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setFastestInterval(5000);
        locationCallback = new LocationCallback() {

            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                //super.onLocationResult(locationResult);
                //Toast.makeText(MapsActivity.this, "Location result is " + locationResult, Toast.LENGTH_SHORT).show();
                if (locationResult == null) {
                    //Toast.makeText(MapsActivity.this, "current location is null", Toast.LENGTH_SHORT).show();

                }
                for (Location location : locationResult.getLocations()) {
                    if (location != null) {
                        // Toast.makeText(MapsActivity.this, "current location is " + location.getLongitude(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        };

        client.requestLocationUpdates(locationRequest, locationCallback, null);

        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    lat = location.getLatitude();
                    lon = location.getLongitude();

                    LatLng latLng = new LatLng(lat, lon);
                    googleMap.addMarker(new MarkerOptions().position(latLng).title("current location"));
                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                }
            }
        });
    }
}