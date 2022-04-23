package com.example.data.local.datasource;

import androidx.lifecycle.LiveData;

import com.example.data.local.room.AppDatabase;
import com.example.data.local.room.BuddyDao;
import com.example.data.local.room.CallDao;
import com.example.data.local.room.MessageDao;
import com.example.data.local.room.UserDao;
import com.example.domain.entities.BuddyEntity;
import com.example.domain.entities.CallEntity;
import com.example.domain.entities.MessageEntity;
import com.example.domain.entities.UserEntity;

import java.util.List;

public class LocalDataStoreImpl implements LocalDataSource {

    private final AppDatabase db;
    public LocalDataStoreImpl(AppDatabase db) {
        this.db = db;
    }


    @Override
    public UserEntity getUser(String usrSpiUri) {
        return db.userDao().getUser(usrSpiUri);
    }

    @Override
    public UserEntity getLastUser() {
        return db.userDao().getLastLogged();
    }

    @Override
    public void addUser(UserEntity userEntity) {
        db.userDao().insert(userEntity);
    }

    @Override
    public List<BuddyEntity> getAllBuddies() {
        return db.buddyDao().getAll();
    }

    @Override
    public List<BuddyEntity> getBuddiesFor(String userSipUri) {
        return db.buddyDao().getBuddiesFor(userSipUri);
    }

    @Override
    public BuddyEntity getBuddy(String usrSipUri, String buddySipUri) {
        return db.buddyDao().getBuddy(usrSipUri, buddySipUri);
    }

    @Override
    public void addBuddy(BuddyEntity buddy) {
        db.buddyDao().insert(buddy);
    }

    @Override
    public List<MessageEntity> getAllMessages() {
        return db.messageDao().getAll();
    }

    @Override
    public List<MessageEntity> getMessagesFor(String userSipUri) {
        return db.messageDao().getMessagesFor(userSipUri);
    }

    @Override
    public List<MessageEntity> getMessagesFor(String usrSipUri, String buddySipUri) {
        return db.messageDao().getMessagesFor(usrSipUri, buddySipUri);
    }

    @Override
    public void addMessage(MessageEntity message) {
        db.messageDao().insert(message);
    }

    @Override
    public List<CallEntity> getAllCalls() {
        return db.callDao().getAll();
    }

    @Override
    public List<CallEntity> getCallsFor(String userSipUri) {
        return db.callDao().getCallsFor(userSipUri);
    }

    @Override
    public List<CallEntity> getCallsFor(String usrSipUri, String buddySipUri) {
        return db.callDao().getCallsFor(usrSipUri, buddySipUri);
    }

    @Override
    public void saveCall(CallEntity call) {
        db.callDao().insert(call);
    }
}
