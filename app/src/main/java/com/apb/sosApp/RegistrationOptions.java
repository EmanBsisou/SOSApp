package com.apb.sosApp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import static android.content.ContentValues.TAG;

public class RegistrationOptions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resgistration_options);
    }

    public void registerAsUser(View view){
        Log.i(TAG, "Register as User");
        Intent intent = new Intent(this, UserRegistration.class);
        startActivity(intent);
    }

    public void registerAsResponser(View view){
        Log.i(TAG, "Register as ResponserRegistration");
        Intent intent = new Intent(this, ResponserRegistration.class);
        startActivity(intent);
    }
}
