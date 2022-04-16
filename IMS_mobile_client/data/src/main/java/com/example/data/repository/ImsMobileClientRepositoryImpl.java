package com.example.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.example.data.local.datasource.LocalDataSource;
import com.example.domain.entities.BuddyEntity;
import com.example.domain.entities.CallEntity;
import com.example.domain.entities.MessageEntity;
import com.example.domain.entities.UserEntity;
import com.example.domain.repository.ImsMobileClientRepository;

import java.util.List;


public class ImsMobileClientRepositoryImpl implements ImsMobileClientRepository {

    private final LocalDataSource dataSource;

    public ImsMobileClientRepositoryImpl(LocalDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public LiveData<UserEntity> getUser(String usrSpiUri) {
        return new MutableLiveData<>(dataSource.getUser(usrSpiUri));
    }

    @Override
    public LiveData<UserEntity> getLastUser() {
        return new MutableLiveData<>(dataSource.getLastUser());
    }

    @Override
    public void addUser(UserEntity userEntity) {
        dataSource.addUser(userEntity);
    }

    @Override
    public LiveData<List<BuddyEntity>> getAllBuddies() {
        return new MutableLiveData<>(dataSource.getAllBuddies());
    }

    @Override
    public LiveData<List<BuddyEntity>> getBuddiesFor(String userSipUri) {
        return new MutableLiveData<>(dataSource.getBuddiesFor(userSipUri));
    }

    @Override
    public LiveData<List<MessageEntity>> getAllMessages() {
        return new MutableLiveData<>(dataSource.getAllMessages());
    }

    @Override
    public LiveData<List<MessageEntity>> getMessagesFor(String userSipUri) {
        return new MutableLiveData<>(dataSource.getMessagesFor(userSipUri));
    }

    @Override
    public LiveData<List<MessageEntity>> getMessagesFor(String usrSipUri, String buddySipUri) {
        return new MutableLiveData<>(dataSource.getMessagesFor(usrSipUri, buddySipUri));
    }

    @Override
    public LiveData<List<CallEntity>> getAllCalls() {
        return new MutableLiveData<>(dataSource.getAllCalls());
    }

    @Override
    public LiveData<List<CallEntity>> getCallsFor(String userSipUri) {
        return new MutableLiveData<>(dataSource.getCallsFor(userSipUri));
    }

    @Override
    public LiveData<List<CallEntity>> getCallsFor(String usrSipUri, String buddySipUri) {
        return new MutableLiveData<>(dataSource.getCallsFor(usrSipUri, buddySipUri));
    }
}
