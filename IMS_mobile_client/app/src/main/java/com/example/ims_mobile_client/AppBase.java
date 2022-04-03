package com.example.ims_mobile_client;

import android.app.Application;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class AppBase extends Application {

    public AppDatabase getDB() {
        return AppDatabase.getInstance(this);
    }

    public AppRepository getRepository() {
        return AppRepository.getInstance(getDB());
    }
}
