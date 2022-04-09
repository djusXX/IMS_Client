package com.example.data.local.datasource;

import androidx.lifecycle.LiveData;

import com.example.domain.entities.BuddyEntity;
import com.example.domain.entities.CallEntity;
import com.example.domain.entities.MessageEntity;

import java.util.List;


public interface LocalDataSource {

    LiveData<List<BuddyEntity>> getAllBuddies();
    LiveData<List<BuddyEntity>> getBuddiesFor(String userSipUri);
    LiveData<BuddyEntity> getBuddy(String usrSipUri, String buddySipUri);
    void addBuddy(BuddyEntity buddyEntity);

    LiveData<List<MessageEntity>> getAllMessages();
    LiveData<List<MessageEntity>> getMessagesFor(String userSipUri);
    LiveData<List<MessageEntity>> getMessagesFor(String usrSipUri, String buddySipUri);
    void addMessage(MessageEntity message);

    LiveData<List<CallEntity>> getAllCalls();
    LiveData<List<CallEntity>> getCallsFor(String userSipUri);
    LiveData<List<CallEntity>> getCallsFor(String usrSipUri, String buddySipUri);
    void saveCall(CallEntity call);

}
