package com.apb.sosApp.listener;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.apb.sosApp.service.UserService;

import static android.content.ContentValues.TAG;

public class ApbLocationListener implements LocationListener {
    private final Context context;
    private final UserService userService;
    private final String userId;
    private final String role;

    public ApbLocationListener(String userId,String role,Context context){
        this.userId = userId;
        this.role = role;
        this.context = context;
        this.userService = new UserService();
    }

    @Override
    public void onLocationChanged(Location loc) {
        Log.i(TAG, "Location changed");
        userService.sendLocation(userId,loc.getLatitude(),loc.getLongitude(),role);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {
        Toast.makeText(context, "Gps Enabled", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderDisabled(String s) {
        Toast.makeText(context, "Gps Disabled", Toast.LENGTH_SHORT).show();
    }
}
