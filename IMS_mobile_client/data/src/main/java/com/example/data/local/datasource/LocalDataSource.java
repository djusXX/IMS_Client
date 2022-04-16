package com.example.data.local.datasource;

import androidx.lifecycle.LiveData;

import com.example.domain.entities.BuddyEntity;
import com.example.domain.entities.CallEntity;
import com.example.domain.entities.MessageEntity;
import com.example.domain.entities.UserEntity;

import java.util.List;


public interface LocalDataSource {

    UserEntity getUser(String usrSpiUri);
    UserEntity getLastUser();
    void addUser(UserEntity userEntity);

    List<BuddyEntity> getAllBuddies();
    List<BuddyEntity> getBuddiesFor(String userSipUri);
    BuddyEntity getBuddy(String usrSipUri, String buddySipUri);
    void addBuddy(BuddyEntity buddyEntity);

    List<MessageEntity> getAllMessages();
    List<MessageEntity> getMessagesFor(String userSipUri);
    List<MessageEntity> getMessagesFor(String usrSipUri, String buddySipUri);
    void addMessage(MessageEntity message);

    List<CallEntity> getAllCalls();
    List<CallEntity> getCallsFor(String userSipUri);
    List<CallEntity> getCallsFor(String usrSipUri, String buddySipUri);
    void saveCall(CallEntity call);

}
