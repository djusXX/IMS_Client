package com.example.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;


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
        MediatorLiveData<UserEntity> data = new MediatorLiveData<>();
        data.setValue(dataSource.getUser(usrSpiUri));
        return data;
    }

    @Override
    public LiveData<UserEntity> getLastUser() {
        MediatorLiveData<UserEntity> ret = new MediatorLiveData<>();
        ret.setValue(dataSource.getLastUser());
        return ret;
    }

    @Override
    public void addUser(UserEntity userEntity) {
        dataSource.addUser(userEntity);
    }

    @Override
    public LiveData<List<BuddyEntity>> getAllBuddies() {
        return dataSource.getAllBuddies();
    }

    @Override
    public LiveData<List<BuddyEntity>> getBuddiesFor(String userSipUri) {
        return dataSource.getBuddiesFor(userSipUri);
    }

    @Override
    public LiveData<List<MessageEntity>> getAllMessages() {
        return dataSource.getAllMessages();
    }

    @Override
    public LiveData<List<MessageEntity>> getMessagesFor(String userSipUri) {
        return dataSource.getMessagesFor(userSipUri);
    }

    @Override
    public LiveData<List<MessageEntity>> getMessagesFor(String usrSipUri, String buddySipUri) {
        return dataSource.getMessagesFor(usrSipUri, buddySipUri);
    }

    @Override
    public LiveData<List<CallEntity>> getAllCalls() {
        return dataSource.getAllCalls();
    }

    @Override
    public LiveData<List<CallEntity>> getCallsFor(String userSipUri) {
        return dataSource.getCallsFor(userSipUri);
    }

    @Override
    public LiveData<List<CallEntity>> getCallsFor(String usrSipUri, String buddySipUri) {
        return dataSource.getCallsFor(usrSipUri, buddySipUri);
    }
}
