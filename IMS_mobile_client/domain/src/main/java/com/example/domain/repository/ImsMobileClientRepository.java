package com.example.domain.repository;

import androidx.lifecycle.LiveData;

import com.example.domain.entities.BuddyEntity;
import com.example.domain.entities.CallEntity;
import com.example.domain.entities.MessageEntity;

import java.util.List;

public interface ImsMobileClientRepository {

    // Buddies
    LiveData<List<BuddyEntity>> getAllBuddies();
    LiveData<List<BuddyEntity>> getBuddiesFor(String userSipUri);


    // Messages
    LiveData<List<MessageEntity>> getAllMessages();
    LiveData<List<MessageEntity>> getMessagesFor(String userSipUri);
    LiveData<List<MessageEntity>> getMessagesFor(String usrSipUri, String buddySipUri);

    // Calls
    LiveData<List<CallEntity>> getAllCalls();
    LiveData<List<CallEntity>> getCallsFor(String userSipUri);
    LiveData<List<CallEntity>> getCallsFor(String usrSipUri, String buddySipUri);
}
