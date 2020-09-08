package ht.solutions.plr.util;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import ht.solutions.plr.data.Session;

public class LocationManagerUtil implements LocationListener {
    private Context context;
    private LocationManager locationManager;
    private int mode = 0;
    private TextView tvGPS;

    public LocationManagerUtil(Context c) {
        this.context = c;
        init();
    }

    public LocationManagerUtil(Context c, TextView tvGPS) {
        this.context = c;
        this.tvGPS = tvGPS;
        init();
    }

    public LocationManagerUtil(Context c, TextView tvGPS, int mode) {
        this.context = c;
        this.tvGPS = tvGPS;
        this.mode = mode;
        init();
    }

    private void init() {
        this.locationManager = (LocationManager) this.context.getSystemService("location");
        Location location = this.locationManager.getLastKnownLocation(this.locationManager.getBestProvider(new Criteria(), false));
        if (location != null) {
            onLocationChanged(location);
            return;
        }
        this.locationManager.requestLocationUpdates("gps", 45000, 1.0f, this);
        if (this.locationManager != null) {
            location = this.locationManager.getLastKnownLocation("gps");
            if (location != null) {
                onLocationChanged(location);
            }
        }
    }

    public void onLocationChanged(Location location) {
        if (Session.getCurrentSuivi() != null) {
            Session.getCurrentSuivi().setLatitude(location.getLatitude());
            Session.getCurrentSuivi().setLongitude(location.getLongitude());
            Toast.makeText(this.context, "Coordonnees GPS capted", 0).show();
            this.tvGPS.setText("Lat : " + location.getLatitude() + ",Long : " + location.getLongitude());
        }
    }

    public void onProviderDisabled(String provider) {
        Toast.makeText(this.context, "Disabled provider " + provider, 0).show();
    }

    public void onProviderEnabled(String provider) {
        Toast.makeText(this.context, "Enabled new provider " + provider, 0).show();
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
    }
}
