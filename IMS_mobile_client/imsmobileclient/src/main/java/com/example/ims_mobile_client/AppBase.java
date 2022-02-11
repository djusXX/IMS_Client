package com.example.ims_mobile_client;

import android.app.Application;

import com.example.ims_mobile_client.data.AppDatabase;
import com.example.ims_mobile_client.data.AppRepository;

public class AppBase extends Application {

    public AppRepository getRepository() {
        return new AppRepository(this);
    }
}
