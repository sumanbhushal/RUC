package com.ruc.bhushalsuman.ruc;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends Activity implements LocationListener {

    MapView myMapView;
    LocationManager locationManager;

    int deviceWidth, deviceHeight;
    double userLatitude;
    double userLongitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        myMapView = (MapView) findViewById(R.id.myview);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        //getting the Width and Height of Device in pixels
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowmanager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        windowmanager.getDefaultDisplay().getMetrics(displayMetrics);
        deviceWidth = displayMetrics.widthPixels;
        deviceHeight = displayMetrics.heightPixels;

        Log.d(" Width & Hight", "" + deviceHeight + " " + deviceWidth);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            String[] permission = {Manifest.permission.ACCESS_FINE_LOCATION};
            ActivityCompat.requestPermissions(this, permission, 123);
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        myMapView.startAnimation();

    }

    @Override
    protected void onPause() {
        super.onPause();
        myMapView.stopAnimation();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            String[] permission = {Manifest.permission.ACCESS_FINE_LOCATION};
            ActivityCompat.requestPermissions(this, permission, 123);
            return;
        }
        locationManager.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(Location location) {

        userLatitude = location.getLatitude();
        userLongitude = location.getLongitude();

        myMapView.setLocationCoordinates(userLatitude, userLongitude, deviceHeight, deviceWidth);
        myMapView.setShowLocation();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
