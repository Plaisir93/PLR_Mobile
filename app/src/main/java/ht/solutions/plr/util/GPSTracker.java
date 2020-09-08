package ht.solutions.plr.util;

import android.app.AlertDialog.Builder;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

public class GPSTracker extends Service implements LocationListener {
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;
    private static final long MIN_TIME_BW_UPDATES = 6000;
    double accuracy;
    boolean canGetLocation = false;
    boolean isGPSEnabled = false;
    boolean isNetworkEnabled = false;
    double latitude;
    Location location;
    protected LocationManager locationManager;
    double longitude;
    private final Context mContext;

    class C01341 implements OnClickListener {
        C01341() {
        }

        public void onClick(DialogInterface dialog, int which) {
            GPSTracker.this.mContext.startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
        }
    }

    class C01352 implements OnClickListener {
        C01352() {
        }

        public void onClick(DialogInterface dialog, int which) {
            dialog.cancel();
        }
    }

    public GPSTracker(Context context) {
        this.mContext = context;
        stopUsingGPS();
        getLocation();
    }

    public Location getLocation() {
        try {
            this.locationManager = (LocationManager) this.mContext.getSystemService("location");
            this.isGPSEnabled = this.locationManager.isProviderEnabled("gps");
            if (this.isGPSEnabled) {
                this.location = this.locationManager.getLastKnownLocation("gps");
                this.locationManager.requestLocationUpdates("gps", MIN_TIME_BW_UPDATES, 10.0f, this);
             //   Toast.makeText(this.mContext, "Location set by GPS", 1).show();
            } else {
                MessageDialog.message(this.mContext, "", "Veuillez activer le GPS");
            }
            this.isNetworkEnabled = this.locationManager.isProviderEnabled("network");
            if (this.isNetworkEnabled && this.location == null) {
                this.location = this.locationManager.getLastKnownLocation("network");
                this.locationManager.requestLocationUpdates("network", MIN_TIME_BW_UPDATES, 10.0f, this);
             //   Toast.makeText(this.mContext, "Location set by Network", 1).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.location;
    }

    public void stopUsingGPS() {
        if (this.locationManager != null) {
            this.locationManager.removeUpdates(this);
        }
        this.location = null;
    }

    public double getLatitude() {
        if (this.location != null) {
            this.latitude = this.location.getLatitude();
        } else {
            this.longitude = 0.0d;
            this.latitude = 0.0d;
            this.accuracy = 0.0d;
        }
        return this.latitude;
    }

    public double getLongitude() {
        if (this.location != null) {
            this.longitude = this.location.getLongitude();
        } else {
            this.longitude = 0.0d;
            this.latitude = 0.0d;
            this.accuracy = 0.0d;
        }
        return this.longitude;
    }

    public double getAccuracy() {
        if (this.location != null) {
            this.accuracy = (double) this.location.getAccuracy();
        }
        return this.accuracy;
    }

    public boolean canGetLocation() {
        return this.canGetLocation;
    }

    public void showSettingsAlert() {
        Builder alertDialog = new Builder(this.mContext);
        alertDialog.setTitle("Paramètres GPS");
        alertDialog.setMessage("Le GPS par Satellite  n'est pas activé. Voulez-vous l'activer?");
        alertDialog.setPositiveButton("Oui", new C01341());
        alertDialog.setNegativeButton("Non", new C01352());
        alertDialog.show();
    }

    public void onLocationChanged(Location location) {
    }

    public void onProviderDisabled(String provider) {
    }

    public void onProviderEnabled(String provider) {
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    public IBinder onBind(Intent arg0) {
        return null;
    }

    private boolean isOnline() {
        NetworkInfo netInfo = ((ConnectivityManager) this.mContext.getSystemService("connectivity")).getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }
}
