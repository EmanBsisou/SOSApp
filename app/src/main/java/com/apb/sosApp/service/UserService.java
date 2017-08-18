package com.apb.sosApp.service;

import android.content.Context;
import android.util.Log;

import com.apb.sosApp.MainActivity;
import com.apb.sosApp.helper.FileHelper;
import com.apb.sosApp.model.Responser;
import com.apb.sosApp.model.User;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import static android.content.ContentValues.TAG;

public class UserService{

    public void registerResponser(final Context context,final String androidId){
        new Thread(new Runnable() {
            HttpURLConnection httpURLConnection=null;
            @Override
            public void run() {
                Log.i(TAG, "Authorizing responser...");
                try {
                    String respnserId="";
                    URL url = new URL(MainActivity.API_URL + "/responser/" +  androidId);
                    Log.i(TAG, url.toString());
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    int responseCode = httpURLConnection.getResponseCode();
                    String response = httpURLConnection.getResponseMessage();
                    String responserId="";
                    String res = new String(response.getBytes());
                    Log.i(TAG, "Response: " + responseCode + " " + response);
                    if(responseCode==200){
                        BufferedReader br = new BufferedReader(
                                new InputStreamReader(httpURLConnection.getInputStream()));
                        StringBuilder sb = new StringBuilder();
                        String line;
                        while ((line = br.readLine()) != null) {
                            sb.append(line);
                        }
                        br.close();
                        String data = new String(sb.toString());
                        Log.i(TAG, "Data: " + data);
                        Gson gson = new Gson();
                        Responser responser = gson.fromJson(data,Responser.class);

                        if (responser!=null){
                            respnserId= responser.getResponserId();
                            Log.i(TAG, "responserId: " + responserId);
                            saveUser(context,responser.getResponserId(),"Responser");
                        }
                        else{

                        }


                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if(httpURLConnection!=null){
                        httpURLConnection.disconnect();
                    }
                }
            }
        }).start();
    }
    public void registerUser(final Context context,final User user){

        new Thread(new Runnable(){
            @Override
            public void run() {
                Log.i(TAG, "Registering user...");
                HttpURLConnection httpURLConnection=null;
                try {
                    URL url = new URL(MainActivity.API_URL + "/user");
                    Log.i(TAG, url.toString());
                    httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setRequestProperty("Content-Type", "application/json");
                    httpURLConnection.connect();

                    Gson gson = new Gson();
                    String userJson = gson.toJson(user);
                    Log.i(TAG, userJson);
                    DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
                    wr.writeBytes(userJson);
                    wr.flush();
                    wr.close();
                    int responseCode = httpURLConnection.getResponseCode();
                    String response = httpURLConnection.getResponseMessage();
                    Log.i(TAG, "Response: " + responseCode + " " + response);
                    if(responseCode==200) {
                        saveUser(context, user.getUserId(), "User");
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if(httpURLConnection!=null){
                        httpURLConnection.disconnect();
                    }
                }
            }
        }).start();
    }

    private void saveUser(Context context,String userId,String userType){
        Log.i(TAG, "Saving user locally...Id: " + userId + " " +  userType);
        FileHelper fileHelper = new FileHelper(context);
        fileHelper.writeRegistration(userType,userId);
    }

    public void sendLocation(final String userId, final double lat, final double lng,final String role){
        new Thread(new Runnable(){
            @Override
            public void run() {
                Log.i(TAG, "Sending alert...");
                HttpURLConnection httpURLConnection=null;
                URL url = null;
                try {
                    StringBuilder urlBuilder = new StringBuilder(MainActivity.API_URL );
                    urlBuilder.append("/geolocation/")
                            .append(userId)
                            .append("?lat=")
                            .append(lat)
                            .append("&lng=")
                            .append(lng)
                            .append("&role=")
                            .append(role);

                    url = new URL(urlBuilder.toString());
                    Log.i(TAG, url.toString());
                    httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.connect();
                    int responseCode = httpURLConnection.getResponseCode();
                    String response = httpURLConnection.getResponseMessage();
                    Log.i(TAG, "Response: " + responseCode + " " + response);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if(httpURLConnection!=null){
                        httpURLConnection.disconnect();
                    }
                }

            }
        }).start();
    }
}
