package com.example.ims_mobile_client.view_models;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.ims_mobile_client.AppBase;
import com.example.ims_mobile_client.data.AppRepository;
import com.example.ims_mobile_client.data.entities.BuddyEntity;

import net.gotev.sipservice.Logger;

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

    public BuddyEntity getBuddy(String ownerUri, String buddyUri) {
        if (buddies.getValue() == null) return null;
        return buddies.getValue().stream().filter(buddyEntity ->
                buddyEntity.user_sip_uri.equals(ownerUri) && buddyEntity.buddy_sip_uri.equals(buddyUri)).findFirst().orElse(null);
    }

    public void updateBuddy(String ownerSipUri, String contactUri, String presStatus, String presText) {
        Logger.debug("BUDDY_VIEW_MODEL", "calling updateBuddy()");
        LiveData<List<BuddyEntity>> userBuddies = repository.getBuddiesFor(ownerSipUri);

        List<BuddyEntity> buddyEntities = userBuddies.getValue();

        if (buddyEntities != null) {
            Logger.debug("BUDDY_VIEW_MODEL", "updating buddy");
            BuddyEntity buddy = buddyEntities.stream().filter(buddyEntity -> buddyEntity.buddy_sip_uri.equals(contactUri)).findFirst().orElse(null);
            buddy.buddy_status_type = presStatus;
            buddy.buddy_status_text = presText;
            userBuddies.notify();
        }

    }
}
