package com.example.eamonn.lab9;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements LocationListener {

    private TextView locationText;
    private TextView translatedLocation;
    private LocationManager locationManager;
    private long minTime = 500;
    private float minDistance = 1;
    private static final int MY_PERMISSION_GPS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        locationText = findViewById(R.id.location);
        translatedLocation = findViewById(R.id.translatedLocation);
        setUpLocation();
    }

    private void setUpLocation() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // Check if there is permission for location tracking already stored
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[] {Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSION_GPS);
        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTime, minDistance,
                    this);
        }
    }

    public void onLocationChanged(Location location) {

        String latestLocation = "";
        Geocoder geo = new Geocoder(MainActivity.this.getApplicationContext(), Locale.getDefault());

        List<Address> addresses = null;
        try {
            addresses = geo.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

            if (addresses.size() > 0) {
                String addressName = addresses.get(0).getFeatureName() + ", " +
                        addresses.get(0).getLocality() + ", " +
                        addresses.get(0).getAdminArea() + ", " +
                        addresses.get(0).getCountryName();
                translatedLocation.setText(addressName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (location != null) {
            latestLocation = String.format("Current Location: Latitude %1$s Longitude: %2$s",
                    Math.round(location.getLatitude()), Math.round(location.getLongitude()));

        }
        locationText.setText("GPS Location" + "\n" + latestLocation);
    }

    public void onProviderDisabled(String provider) { }

    public void onProviderEnabled(String provider) { }

    public void onStatusChanged(String provider, int status, Bundle extras) { }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        switch (requestCode) {
            case MY_PERMISSION_GPS:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    Toast.makeText(this, "Need your location!", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    protected void onResume() {
        super.onResume();
        Log.d("TrackingState", "Resuming location tracking");
    }

    protected  void onPause() {
        super.onPause();
        Log.d("TrackingState", "Paused location tracking");
    }

}
