package com.example.data.repository;

import com.example.data.local.datasource.LocalDataSource;
import com.example.domain.entities.BuddyEntity;
import com.example.domain.entities.CallEntity;
import com.example.domain.entities.MessageEntity;
import com.example.domain.repository.ImsMobileClientRepository;

import java.util.List;

import io.reactivex.Flowable;

public class ImsMobileClientRepositoryImpl implements ImsMobileClientRepository {

    private final LocalDataSource dataSource;

    public ImsMobileClientRepositoryImpl(LocalDataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public Flowable<List<BuddyEntity>> getAllBuddies() {
        return dataSource.getAllBuddies();
    }

    @Override
    public Flowable<List<BuddyEntity>> getBuddiesFor(String userSipUri) {
        return dataSource.getBuddiesFor(userSipUri);
    }

    @Override
    public Flowable<List<MessageEntity>> getAllMessages() {
        return dataSource.getAllMessages();
    }

    @Override
    public Flowable<List<MessageEntity>> getMessagesFor(String userSipUri) {
        return dataSource.getMessagesFor(userSipUri);
    }

    @Override
    public Flowable<List<MessageEntity>> getMessagesFor(String usrSipUri, String buddySipUri) {
        return dataSource.getMessagesFor(usrSipUri, buddySipUri);
    }

    @Override
    public Flowable<List<CallEntity>> getAllCalls() {
        return dataSource.getAllCalls();
    }

    @Override
    public Flowable<List<CallEntity>> getCallsFor(String userSipUri) {
        return dataSource.getCallsFor(userSipUri);
    }

    @Override
    public Flowable<List<CallEntity>> getCallsFor(String usrSipUri, String buddySipUri) {
        return dataSource.getCallsFor(usrSipUri, buddySipUri);
    }
}
