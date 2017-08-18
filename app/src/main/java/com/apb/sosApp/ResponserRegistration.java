package com.apb.sosApp;

import android.content.Context;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.apb.sosApp.service.UserService;

import static android.content.ContentValues.TAG;

public class ResponserRegistration extends AppCompatActivity {
    private String androidId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_responser_registration);
        Context context = getApplicationContext();
        androidId = Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        TextView textView = (TextView) findViewById(R.id.textViewAndroidId);
        textView.setText(androidId);
    }

        public void registerResponser(View view){

        Log.i(TAG, "Registering responser...");
        UserService userService = new UserService();
        userService.registerResponser(getApplicationContext(),androidId);
            Toast

                    .makeText(this, "Thank you your registration was successfull", Toast.LENGTH_LONG)

                    .show();
    }
}
