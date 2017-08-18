package com.apb.sosApp.helper;


import android.content.Context;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.content.ContentValues.TAG;

public class FileHelper {

    private final Context context;
    private File file;
    public FileHelper(Context context){
        this.context = context;
        File path = context.getFilesDir();
        file = new File(path, "registered.txt");
    }

    public boolean checkFileExist(){
        Log.i(TAG, "File Path :" + file.getAbsolutePath());
        return file.exists();
    }

    public void writeRegistration(String userType,String userId){
        Log.i(TAG, "Writing Registration to :" + file.getAbsolutePath());
        FileOutputStream stream = null;
        try {
            stream = new FileOutputStream(file);
            String line = userType + "," + userId;
            stream.write(line.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stream != null) {
                    stream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String readRegistration(){
        if(checkFileExist()){
            Log.i(TAG, "Reading Registration from :" + file.getAbsolutePath());
            BufferedInputStream in = null;
            try {
                in = new BufferedInputStream(new FileInputStream(file));
                int length = (int) file.length();
                byte[] bytes = new byte[length];
                in.read(bytes);
                String line = new String(bytes);
                return line;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if(in!=null){
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }else{
            Log.i(TAG, "Registration Not found at :" + file.getAbsolutePath());
            return null;
        }
        return null;
    }
}
