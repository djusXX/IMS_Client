package com.example.ims_mobile_client.view_models;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.ims_mobile_client.AppBase;
import com.example.ims_mobile_client.data.AppRepository;
import com.example.ims_mobile_client.data.entities.BuddyEntity;

import java.util.List;

public class BuddyViewModel extends AndroidViewModel {

    private final AppRepository repository;
    private final LiveData<List<BuddyEntity>> buddies;

    public BuddyViewModel(@NonNull Application application) {
        super(application);

        repository = ((AppBase) application).getRepository();
        buddies = repository.getAllBuddies();
    }

    public LiveData<List<BuddyEntity>> getAllBuddies() { return buddies; }

    public LiveData<List<BuddyEntity>> getBuddiesFor(String userSipUri) {
        return repository.getBuddiesFor(userSipUri);
    }

    public void addBuddy(BuddyEntity buddyEntity) { repository.addBuddy(buddyEntity); }

}
