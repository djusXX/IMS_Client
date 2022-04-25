package ims_mobile_client.localDataSource;

import java.util.List;

import javax.inject.Inject;

import ims_mobile_client.data.entities.BuddyEntity;
import ims_mobile_client.data.entities.CallEntity;
import ims_mobile_client.data.entities.MessageEntity;
import ims_mobile_client.data.entities.UserEntity;
import ims_mobile_client.data.repository.LocalDataStore;
import ims_mobile_client.localDataSource.db.AppDatabase;
import io.reactivex.Completable;
import io.reactivex.Flowable;

public class LocalDataStoreImpl implements LocalDataStore {

    private final AppDatabase db;


    @Inject
    public LocalDataStoreImpl(AppDatabase db) {
        this.db = db;
    }


    @Override
    public Flowable<UserEntity> getUser(String usrSpiUri) {
        return null;
    }

    @Override
    public Flowable<UserEntity> getLastUser() {
        return null;
    }

    @Override
    public Completable addUser(UserEntity userEntity) {
        return null;
    }

    @Override
    public Flowable<List<BuddyEntity>> getBuddiesFor(String userSipUri) {
        return null;
    }

    @Override
    public Flowable<BuddyEntity> getBuddy(String usrSipUri, String buddySipUri) {
        return null;
    }

    @Override
    public Completable addBuddy(BuddyEntity buddyEntity) {
        return null;
    }

    @Override
    public Flowable<List<MessageEntity>> getMessagesFor(String usrSipUri, String buddySipUri) {
        return null;
    }

    @Override
    public Completable addMessage(MessageEntity message) {
        return null;
    }

    @Override
    public Flowable<List<CallEntity>> getCallsFor(String usrSipUri, String buddySipUri) {
        return null;
    }

    @Override
    public Completable saveCall(CallEntity call) {
        return null;
    }
}
