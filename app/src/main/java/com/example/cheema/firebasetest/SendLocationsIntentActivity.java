package com.example.cheema.firebasetest;

import android.Manifest;
import android.app.IntentService;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SendLocationsIntentActivity extends AppCompatActivity {

    LocationManager locationManager;
    LocationListener locationListener;

    Button btnLocation;
    TextView tvLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_locations_intent);


        btnLocation = (Button) findViewById(R.id.btnSendLocation);
        tvLocation = (TextView) findViewById(R.id.textView);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {

            // called when location is updated
            @Override
            public void onLocationChanged(Location location) {
                tvLocation.append("\n" + location.getLatitude() + " " + location.getLongitude());

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            // called if GPS is OFF
            @Override
            public void onProviderDisabled(String provider) {

                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(i);

            }
        };

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.INTERNET
            }, 10);
            return;
        }
        } else
        {
            configureButton();

        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 10:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    configureButton();
                    return;
                }
        }
    }

    private void configureButton() {
        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                locationManager.requestLocationUpdates("gps", 3000, 0, locationListener);
            }
        });

    }
}
