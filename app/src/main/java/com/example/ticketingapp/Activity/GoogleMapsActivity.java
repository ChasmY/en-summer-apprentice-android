package com.example.ticketingapp.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.ticketingapp.Model.Dto.VenueDto;
import com.example.ticketingapp.R;
import com.example.ticketingapp.Service.ApiService;
import com.example.ticketingapp.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GoogleMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final int Request_CODE = 101;
    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private Location currentLocation;
    private Marker selectedMarker;
    private Polyline currentPolyline;

    // Set the predefined location (e.g., Bucharest, Romania)
    private static final LatLng PREDEFINED_LOCATION = new LatLng(46.78080801752438, 23.61217073745509);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        checkPermission();
    }

    private void getCurrentLocation() {
        // Set manually the coordinates for the current location
        double currentLat = 46.77570338319261;  // Desired latitude
        double currentLng = 23.594104897915756;  // Desired longitude

        currentLocation = new Location("Current Location");
        currentLocation.setLatitude(currentLat);
        currentLocation.setLongitude(currentLng);

        LatLng currentLatLng = new LatLng(currentLat, currentLng);
        mMap.addMarker(new MarkerOptions()
                .position(currentLatLng)
                .title("Current Location")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        drawPolyline(currentLatLng, selectedMarker.getPosition());
    }

    private void drawPolyline(LatLng origin, LatLng destination) {
        if (currentPolyline != null) {
            currentPolyline.remove();
        }

        currentPolyline = mMap.addPolyline(new PolylineOptions()
                .add(origin, destination)
                .color(Color.BLUE)
                .width(5));

        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(origin);
        builder.include(destination);
        LatLngBounds bounds = builder.build();
        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.addMarker(new MarkerOptions()
                .position(PREDEFINED_LOCATION)
                .title("Current Location")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(PREDEFINED_LOCATION, 10f));

        mMap.setOnMapClickListener(latLng -> {
            if (selectedMarker != null) {
                selectedMarker.remove();
            }

            selectedMarker = mMap.addMarker(new MarkerOptions().position(latLng).title("Selected Destination"));
            selectedMarker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

            getCurrentLocation();
        });

        // Use AsyncTask to load the map in a new thread
        new LoadMapAsyncTask().execute();
    }

    private void checkPermission() {
        if (!(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, Request_CODE);
        } else {
            // Permissions granted, you can proceed with loading the map or other operations
            new LoadMapAsyncTask().execute();
        }
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case Request_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permissions granted, you can proceed with loading the map or other operations
                    new LoadMapAsyncTask().execute();
                } else {
                    // Permissions denied, you can display a message to the user or take other actions
                    Log.e("GoogleMap_Activ", "Permissions denied.");
                }
                break;
        }
    }

    private class LoadMapAsyncTask extends AsyncTask<Void, Void, List<VenueDto>> {

        @Override
        protected List<VenueDto> doInBackground(Void... params) {
            try {
                // Create a Retrofit object
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://10.0.2.2:7042/api/")  // Replace with the correct database address
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                // Create an instance of the ApiService interface
                ApiService apiService = retrofit.create(ApiService.class);

                // Make the HTTP request to get data from the endpoint
                Call<List<VenueDto>> call = apiService.getAllVenues_AllCoordinates();
                Response<List<VenueDto>> response = call.execute();

                // Check if the request was successful and return the data
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("VENUECALL", "Status" + response.body());
                    return response.body();
                }

            } catch (IOException e) {
                e.printStackTrace();
                Log.e("GoogleMap_Activ", "Error getting data: " + e.getMessage());
            } catch (Exception ex) {
                ex.printStackTrace();
                Log.e("GoogleMap_Activ", "Uncaught exception: " + ex.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<VenueDto> locations) {
            super.onPostExecute(locations);

            // Check if mMap is not null before adding markers
            if (mMap != null) {
                // Add markers on the map for each location
                if (locations != null && locations.size() > 0) {
                    for (VenueDto location : locations) {
                        LatLng latLng = new LatLng(location.getLatitude().doubleValue(), location.getLongitude().doubleValue());
                        Log.d("LATLNG", "Status " + latLng);
                        mMap.addMarker(new MarkerOptions()
                                .position(latLng)
                                .title(location.getLocation())
                                .icon(bitmapDescriptor(getApplicationContext(), R.drawable.pin_rosu, 80, 80)));
                    }
                } else {
                    Log.e("GoogleMap_Activ", "Unable to get data from the database.");
                }
            } else {
                Log.e("GoogleMap_Activ", "mMap is null in onPostExecute");
            }
        }

    }

    private BitmapDescriptor bitmapDescriptor(Context context, int vectorResId, int width, int height) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        if (vectorDrawable != null) {
            vectorDrawable.setBounds(0, 0, width, height);
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            vectorDrawable.draw(canvas);
            return BitmapDescriptorFactory.fromBitmap(bitmap);
        } else {
            Log.e("GoogleMap_Activ", "Vector drawable is null");
            return BitmapDescriptorFactory.defaultMarker();  // Default marker if vector drawable is null
        }
    }
}