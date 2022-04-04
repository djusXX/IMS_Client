package com.example.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.example.ims_mobile_client.model.DatabaseModel;
import com.example.data.entities.BuddyEntity;
import com.example.data.entities.CallEntity;
import com.example.data.entities.MessageEntity;

import java.util.List;

public class AppRepository implements DatabaseModel {

    private static AppRepository INSTANCE;

    private final AppDatabase db;
    private final MediatorLiveData<List<CallEntity>> observedCalls;
    private final MediatorLiveData<List<MessageEntity>> observedMessages;
    private final MediatorLiveData<List<BuddyEntity>> observedBuddies;

    public AppRepository(final AppDatabase db) {
        this.db = db;

        observedCalls = new MediatorLiveData<>();
        observedCalls.addSource(db.callDao().getAll(), callEntities -> {
            if(this.db.getDBCreated().getValue() != null) {
                observedCalls.postValue(callEntities);
            }
        });

        observedMessages = new MediatorLiveData<>();
        observedMessages.addSource(db.messageDao().getAll(), messageEntities -> {
            if(this.db.getDBCreated().getValue() != null) {
                observedMessages.postValue(messageEntities);
            }
        });

        observedBuddies = new MediatorLiveData<>();
        observedBuddies.addSource(db.buddyDao().getAll(), buddyEntities -> {
            if(this.db.getDBCreated().getValue() != null) {
                observedBuddies.postValue(buddyEntities);
            }
        });
    }

    public static AppRepository getInstance(final AppDatabase db) {
        if (INSTANCE == null) {
            synchronized (AppRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AppRepository(db);
                }
            }
        }
        return INSTANCE;
    }

    public LiveData<List<CallEntity>> getAllCalls() {
        return observedCalls;
    }

    public LiveData<List<MessageEntity>> getAllMessages() {
        return observedMessages;
    }

    public LiveData<List<BuddyEntity>> getAllBuddies() {
        return observedBuddies;
    }

    public void addCall(CallEntity callEntity) {
        AppDatabase.dbWriteExecutor.execute(() -> {
            db.callDao().insert(callEntity);
        });
    }

    public void addMessage(MessageEntity messageEntity) {
        AppDatabase.dbWriteExecutor.execute(() -> {
            db.messageDao().insert(messageEntity);
        });
    }

    public void addBuddy(BuddyEntity buddyEntity) {
        AppDatabase.dbWriteExecutor.execute(() -> {
            db.buddyDao().insert(buddyEntity);
        });
    }

    public LiveData<List<BuddyEntity>> getBuddiesFor(String userSipUri) {
        return db.buddyDao().getBuddiesFor(userSipUri);
    }

    public LiveData<List<MessageEntity>> getMessagesFor(String userSipUri) {
        return db.messageDao().getMessagesFor(userSipUri);
    }

    public LiveData<List<MessageEntity>> getMessagesFor(String usrSipUri, String buddySipUri) {
        return db.messageDao().getMessagesFor(usrSipUri, buddySipUri);
    }

    public LiveData<List<CallEntity>> getCallsFor(String userSipUri) {
        return db.callDao().getCallsFor(userSipUri);
    }

    public LiveData<List<CallEntity>> getCallsFor(String usrSipUri, String buddySipUri) {
        return db.callDao().getCallsFor(usrSipUri, buddySipUri);
    }
}
