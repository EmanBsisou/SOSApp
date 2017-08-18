package com.apb.sosApp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.apb.sosApp.helper.LocationHelper;

import static android.content.ContentValues.TAG;

public class Emergency extends AppCompatActivity {

    private String userId,role;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);
        context = getApplicationContext();

        Intent intent = getIntent();
        userId = intent.getStringExtra(MainActivity.USER_ID);
        role = intent.getStringExtra(MainActivity.USER_TYPE);

        TextView textView = (TextView) findViewById(R.id.textViewUserId);
        textView.setText(userId);

    }

    public void alertEmergency(View view){
        Log.i(TAG, "Alert Emergency");
        LocationHelper locationHelper = new LocationHelper(context,userId,role);
        locationHelper.sendCurrentLocation();
    }
}
