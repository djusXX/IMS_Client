package com.example.data.local.datasource;

import com.example.domain.entities.BuddyEntity;
import com.example.domain.entities.CallEntity;
import com.example.domain.entities.MessageEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface LocalDataSource {

    Flowable<List<BuddyEntity>> getAllBuddies();
    Flowable<List<BuddyEntity>> getBuddiesFor(String userSipUri);
    Flowable<BuddyEntity> getBuddy(String usrSipUri, String buddySipUri);
    Completable addBuddy(BuddyEntity buddyEntity);

    Flowable<List<MessageEntity>> getAllMessages();
    Flowable<List<MessageEntity>> getMessagesFor(String userSipUri);
    Flowable<List<MessageEntity>> getMessagesFor(String usrSipUri, String buddySipUri);
    Completable addMessage(MessageEntity message);

    Flowable<List<CallEntity>> getAllCalls();
    Flowable<List<CallEntity>> getCallsFor(String userSipUri);
    Flowable<List<CallEntity>> getCallsFor(String usrSipUri, String buddySipUri);
    Completable saveCall(CallEntity call);

}
