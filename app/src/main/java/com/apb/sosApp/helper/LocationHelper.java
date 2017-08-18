package com.apb.sosApp.helper;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.apb.sosApp.listener.ApbLocationListener;
import com.apb.sosApp.service.UserService;

import static android.content.ContentValues.TAG;

public class LocationHelper {
    private final LocationManager mlocManager;
    private final String userId,role;
    private final Context context;

    public LocationHelper(Context context,String userId,String role ){
        this.context = context;
        this.userId = userId;
        this.role = role;

        mlocManager = (LocationManager) context.getSystemService(context.LOCATION_SERVICE);
    }

    public void sendCurrentLocation(){
        Log.i(TAG, "Sending location...");
        Location locationGPS = mlocManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        Location locationNet = mlocManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        Location loc;
        long GPSLocationTime = 0;
        if (null != locationGPS) { GPSLocationTime = locationGPS.getTime(); }

        long NetLocationTime = 0;

        if (null != locationNet) {
            NetLocationTime = locationNet.getTime();
        }

        if ( 0 < GPSLocationTime - NetLocationTime ) {
            loc=locationGPS;
        }
        else {
            loc=locationNet;
        }
        UserService userService = new UserService();
        userService.sendLocation(userId,loc.getLatitude(),loc.getLongitude(),role);

        if (ActivityCompat.checkSelfPermission(
                context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationListener mlocListener = new ApbLocationListener(userId,role,context);
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mlocListener);
    }
}
