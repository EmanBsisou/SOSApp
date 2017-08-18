package com.apb.sosApp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.apb.sosApp.helper.FileHelper;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    public static final String USER_ID = "userId";
    public static final String USER_TYPE = "userType";
    public static String API_URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       API_URL = getResources().getString(R.string.api_url);
        Context context = getApplicationContext();
        FileHelper fileHelper = new FileHelper(context);
        String line = null;
        if(fileHelper.checkFileExist()){
            Log.i(TAG, "Alreday Registered User");
            line = fileHelper.readRegistration();
            String lines[] =line.split(",");
            Log.i(TAG, "User Type: " + lines[0]);
            Intent intent;
            if(lines[0].equalsIgnoreCase("User")){
                intent = new Intent(this, Emergency.class);
            }else{
                intent = new Intent(this, Responser.class);
            }
            intent.putExtra(USER_TYPE,lines[0]);
            intent.putExtra(USER_ID,lines[1]);
            startActivity(intent);
        }else{
            Log.i(TAG, "New User");
            Intent intent = new Intent(this, RegistrationOptions.class);
            startActivity(intent);
        }
    }

}
