package com.apb.sosApp;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.apb.sosApp.helper.LocationHelper;

import static android.content.ContentValues.TAG;

public class Responser extends AppCompatActivity {
//user role context
    Intent intent;
    String userId;
    String role;
    static Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_responser);
        Intent intent = getIntent();
        String userId = intent.getStringExtra(MainActivity.USER_ID);
        String role = intent.getStringExtra(MainActivity.USER_TYPE);
        Context context = getApplicationContext();

       // TextView textView = (TextView) findViewById(R.id.textViewResponserId);
        //textView.setText(userId);
        LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        LocationHelper locationHelper = new LocationHelper(context,userId,role);//new
        locationHelper.sendCurrentLocation();// call these two lines
        WebView webView = (WebView)findViewById(R.id.webViewID);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://sos.au-syd.mybluemix.net/#/?userId="+ userId);
    }

    //view for the button
        public void resendLocation(View view){
        Log.i(TAG, "resend Location");
        LocationHelper locationHelper = new LocationHelper(context,userId,role);
        locationHelper.sendCurrentLocation();
    }

    public static void alert(){
        Toast

                .makeText(context, "Thank you your registration was successfull", Toast.LENGTH_LONG)

                .show();
    }

}
