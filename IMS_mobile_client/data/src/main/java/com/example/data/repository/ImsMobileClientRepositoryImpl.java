package com.example.data.repository;

import android.service.autofill.UserData;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.example.data.di.AppExecutors;
import com.example.data.local.datasource.LocalDataSource;
import com.example.domain.entities.BuddyEntity;
import com.example.domain.entities.CallEntity;
import com.example.domain.entities.MessageEntity;
import com.example.domain.entities.UserEntity;
import com.example.domain.repository.ImsMobileClientRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ImsMobileClientRepositoryImpl implements ImsMobileClientRepository {

    private final LocalDataSource dataSource;
    private final AppExecutors appExecutors;

    @Inject
    public ImsMobileClientRepositoryImpl(AppExecutors appExecutors, LocalDataSource dataSource) {
        this.dataSource = dataSource;
        this.appExecutors = appExecutors;
    }



    @Override
    public LiveData<UserEntity> getUser(String usrSpiUri) {
        MediatorLiveData<UserEntity> data = new MediatorLiveData<>();
        data.setValue(dataSource.getUser(usrSpiUri));
        return data;
    }

    @Override
    public void addUser(UserEntity userEntity) {
        appExecutors.diskIO().execute(() -> {dataSource.addUser(userEntity);});
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
