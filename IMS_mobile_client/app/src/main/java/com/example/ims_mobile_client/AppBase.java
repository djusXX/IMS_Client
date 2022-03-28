package com.example.ims_mobile_client;

import android.app.Application;

import com.example.conversations.model.BaseApp;
import com.example.repository.data.AppDatabase;
import com.example.repository.data.AppRepository;

public class AppBase extends Application implements BaseApp {

    public AppDatabase getDB() {
        return AppDatabase.getInstance(this);
    }

    public AppRepository getRepository() {
        return AppRepository.getInstance(getDB());
    }
}
