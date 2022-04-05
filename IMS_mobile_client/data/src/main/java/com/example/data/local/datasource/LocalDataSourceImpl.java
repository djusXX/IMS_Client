package com.example.data.local.datasource;

import com.example.data.local.room.BuddyDao;
import com.example.data.local.room.CallDao;
import com.example.data.local.room.MessageDao;
import com.example.domain.entities.BuddyEntity;
import com.example.domain.entities.CallEntity;
import com.example.domain.entities.MessageEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

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
    public Flowable<List<BuddyEntity>> getAllBuddies() {
        return buddyDao.getAll();
    }

    @Override
    public Flowable<List<BuddyEntity>> getBuddiesFor(String userSipUri) {
        return buddyDao.getBuddiesFor(userSipUri);
    }

    @Override
    public Flowable<BuddyEntity> getBuddy(String usrSipUri, String buddySipUri) {
        return buddyDao.getBuddy(usrSipUri, buddySipUri);
    }

    @Override
    public Completable addBuddy(BuddyEntity buddy) {
        return buddyDao.insert(buddy);
    }

    @Override
    public Flowable<List<MessageEntity>> getAllMessages() {
        return messageDao.getAll();
    }

    @Override
    public Flowable<List<MessageEntity>> getMessagesFor(String userSipUri) {
        return messageDao.getMessagesFor(userSipUri);
    }

    @Override
    public Flowable<List<MessageEntity>> getMessagesFor(String usrSipUri, String buddySipUri) {
        return messageDao.getMessagesFor(usrSipUri, buddySipUri);
    }

    @Override
    public Completable addMessage(MessageEntity message) {
        return messageDao.insert(message);
    }

    @Override
    public Flowable<List<CallEntity>> getAllCalls() {
        return callDao.getAll();
    }

    @Override
    public Flowable<List<CallEntity>> getCallsFor(String userSipUri) {
        return callDao.getCallsFor(userSipUri);
    }

    @Override
    public Flowable<List<CallEntity>> getCallsFor(String usrSipUri, String buddySipUri) {
        return callDao.getCallsFor(usrSipUri, buddySipUri);
    }

    @Override
    public Completable saveCall(CallEntity call) {
        return callDao.insert(call);
    }
}
