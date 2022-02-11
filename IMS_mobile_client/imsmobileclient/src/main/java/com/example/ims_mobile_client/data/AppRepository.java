package com.example.ims_mobile_client.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.example.ims_mobile_client.data.Entities.BuddyEntity;
import com.example.ims_mobile_client.data.Entities.CallEntity;
import com.example.ims_mobile_client.data.Entities.MessageEntity;

import java.util.List;

public class AppRepository {

    private static AppRepository INSTANCE;

    private final AppDatabase db;
    private MediatorLiveData<List<CallEntity>> observedCalls;
    private MediatorLiveData<List<MessageEntity>> observedMessages;
    private MediatorLiveData<List<BuddyEntity>> observedBuddies;

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
            observedBuddies.postValue(buddyEntities);
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

//    public void addCall(CallEntity callEntity) {
//        AppDatabase.dbWriteExecutor.execute(() -> {
//            callDao.insert(callEntity);
//        });
//    }
//
//    public void addMessage(MessageEntity messageEntity) {
//        AppDatabase.dbWriteExecutor.execute(() -> {
//            messageDao.insert(messageEntity);
//        });
//    }
//
//    public void addBuddy(BuddyEntity buddyEntity) {
//        AppDatabase.dbWriteExecutor.execute(() -> {
//            buddyDao.insert(buddyEntity);
//        });
//    }
}
