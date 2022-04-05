package com.example.domain.repository;

import androidx.lifecycle.LiveData;

import com.example.domain.entities.BuddyEntity;
import com.example.domain.entities.CallEntity;
import com.example.domain.entities.MessageEntity;

import java.util.List;

import io.reactivex.Flowable;

public interface ImsMobileClientRepository {

    // Buddies
    Flowable<List<BuddyEntity>> getAllBuddies();
    Flowable<List<BuddyEntity>> getBuddiesFor(String userSipUri);

    // Messages
    Flowable<List<MessageEntity>> getAllMessages();
    Flowable<List<MessageEntity>> getMessagesFor(String userSipUri);
    Flowable<List<MessageEntity>> getMessagesFor(String usrSipUri, String buddySipUri);

    // Calls
    Flowable<List<CallEntity>> getAllCalls();
    Flowable<List<CallEntity>> getCallsFor(String userSipUri);
    Flowable<List<CallEntity>> getCallsFor(String usrSipUri, String buddySipUri);
}
