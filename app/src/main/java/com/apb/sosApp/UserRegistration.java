package com.apb.sosApp;

import android.content.Context;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.apb.sosApp.model.EmergencyContact;
import com.apb.sosApp.model.User;
import com.apb.sosApp.service.UserService;

import java.util.HashSet;

import static android.content.ContentValues.TAG;

public class UserRegistration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);
    }

    public void register(View view){
        Log.i(TAG, "UserRegistration started...");
        EmergencyContact emergencyContact = new EmergencyContact();
        TextView  textView = (TextView)findViewById(R.id.editTextEmergencyname);
        emergencyContact.setContactName(textView.getText().toString());
        textView = (TextView)findViewById(R.id.editTextEmergencyPhone);
        emergencyContact.setPhone(textView.getText().toString());

        User user = new User();
        textView = (TextView)findViewById(R.id.editTextUserId);
        user.setUserId(textView.getText().toString());
        textView = (TextView)findViewById(R.id.editTextFullname);
        user.setUserName(textView.getText().toString());
        textView = (TextView)findViewById(R.id.editTextPhone);
        user.setPhone(textView.getText().toString());
        textView = (TextView)findViewById(R.id.editTextAddress);
        user.setAddress(textView.getText().toString());
        ///////////////Added setMedicalInfo///dont use medialdetail class just add to user
        textView = (TextView)findViewById(R.id.editTextMedicalDetails);
        user.setMedicalDetails(textView.getText().toString());


        Context context = getApplicationContext();
        String androidId = Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        Log.i(TAG, "androidId=" + androidId);
        user.setDeviceId(androidId);

        HashSet<EmergencyContact> contacts = new HashSet<EmergencyContact>();
        contacts.add(emergencyContact);
        user.setEmergencyContact(contacts);


        UserService userService = new UserService();
        userService.registerUser(context,user);
        Toast

                .makeText(this, "Thank you your registration was successfull", Toast.LENGTH_LONG)

                .show();
    }

}
