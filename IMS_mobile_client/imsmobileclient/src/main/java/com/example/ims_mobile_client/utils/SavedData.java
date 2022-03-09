package com.example.ims_mobile_client.utils;

import android.content.Context;
import android.content.SharedPreferences;


public class SavedData {
    private final SharedPreferences sharedPreferences;

    private static SavedData INSTANCE = null;
    private static final String TAG = SavedData.class.getSimpleName();

    private SavedData(Context context) {
        sharedPreferences = context.getSharedPreferences("imsmobileclient_prefs", Context.MODE_PRIVATE);;
    }

    public static SavedData getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new SavedData(context);
        }
        return INSTANCE;
    }

    public void setString(String key, String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }

    public String getString(String key) {
        return sharedPreferences.getString(key,"");
    }

    public void setBoolean(String key, boolean value) {
        sharedPreferences.edit().putBoolean(key, value).apply();
    }

    public boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key,false);
    }

    public void setInt(String key, int value) {
        sharedPreferences.edit().putInt(key, value).apply();
    }

    public int getInt(String key) {
        return sharedPreferences.getInt(key, -1);
    }
}