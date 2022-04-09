package com.example.data.local.datasource;

import androidx.lifecycle.LiveData;

import com.example.data.local.room.BuddyDao;
import com.example.data.local.room.CallDao;
import com.example.data.local.room.MessageDao;
import com.example.domain.entities.BuddyEntity;
import com.example.domain.entities.CallEntity;
import com.example.domain.entities.MessageEntity;

import java.util.List;

public class LocalDataSourceImpl implements LocalDataSource {

    private final BuddyDao buddyDao;
    private final MessageDao messageDao;
    private final CallDao callDao;

    public LocalDataSourceImpl(BuddyDao buddyDao, MessageDao messageDao, CallDao callDao) {
        this.buddyDao = buddyDao;
        this.messageDao = messageDao;
        this.callDao = callDao;
    }

    @Override
    public LiveData<List<BuddyEntity>> getAllBuddies() {
        return buddyDao.getAll();
    }

    @Override
    public LiveData<List<BuddyEntity>> getBuddiesFor(String userSipUri) {
        return buddyDao.getBuddiesFor(userSipUri);
    }

    @Override
    public LiveData<BuddyEntity> getBuddy(String usrSipUri, String buddySipUri) {
        return buddyDao.getBuddy(usrSipUri, buddySipUri);
    }

    @Override
    public void addBuddy(BuddyEntity buddy) {
        buddyDao.insert(buddy);
    }

    @Override
    public LiveData<List<MessageEntity>> getAllMessages() {
        return messageDao.getAll();
    }

    @Override
    public LiveData<List<MessageEntity>> getMessagesFor(String userSipUri) {
        return messageDao.getMessagesFor(userSipUri);
    }

    @Override
    public LiveData<List<MessageEntity>> getMessagesFor(String usrSipUri, String buddySipUri) {
        return messageDao.getMessagesFor(usrSipUri, buddySipUri);
    }

    @Override
    public void addMessage(MessageEntity message) {
        messageDao.insert(message);
    }

    @Override
    public LiveData<List<CallEntity>> getAllCalls() {
        return callDao.getAll();
    }

    @Override
    public LiveData<List<CallEntity>> getCallsFor(String userSipUri) {
        return callDao.getCallsFor(userSipUri);
    }

    @Override
    public LiveData<List<CallEntity>> getCallsFor(String usrSipUri, String buddySipUri) {
        return callDao.getCallsFor(usrSipUri, buddySipUri);
    }

    @Override
    public void saveCall(CallEntity call) {
        callDao.insert(call);
    }
}
