package com.example.ims_mobile_client.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.ims_mobile_client.data.DAOs.BuddyDao;
import com.example.ims_mobile_client.data.DAOs.CallDao;
import com.example.ims_mobile_client.data.DAOs.MessageDao;
import com.example.ims_mobile_client.data.Entities.BuddyEntity;
import com.example.ims_mobile_client.data.Entities.CallEntity;
import com.example.ims_mobile_client.data.Entities.MessageEntity;

import java.util.List;

public class AppRepository {

    private CallDao callDao;
    private MessageDao messageDao;
    private BuddyDao buddyDao;

    public AppRepository(Application application) {
        AppDatabase db = AppDatabase.getDB(application);
        callDao = db.callDao();
        messageDao = db.messageDao();
        buddyDao = db.buddyDao();
    }

    public LiveData<List<CallEntity>> getAllCalls() {
        return callDao.getAll();
    }

    public LiveData<List<MessageEntity>> getAllMessages() {
        return messageDao.getAll();
    }

    public LiveData<List<BuddyEntity>> getAllBuddies() {
        return buddyDao.getAll();
    }

    public void addCall(CallEntity callEntity) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            callDao.insert(callEntity);
        });
    }

    public void addMessage(MessageEntity messageEntity) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            messageDao.insert(messageEntity);
        });
    }

    public void addBuddy(BuddyEntity buddyEntity) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            buddyDao.insert(buddyEntity);
        });
    }
}
