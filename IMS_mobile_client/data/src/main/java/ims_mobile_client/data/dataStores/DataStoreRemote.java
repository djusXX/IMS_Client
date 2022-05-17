package ims_mobile_client.data.dataStores;

import java.util.List;

import javax.inject.Inject;

import ims_mobile_client.data.dataSources.RemoteDataSource;
import ims_mobile_client.data.models.BuddyEntity;
import ims_mobile_client.data.models.CallEntity;
import ims_mobile_client.data.models.MessageEntity;
import ims_mobile_client.data.models.UserEntity;
import io.reactivex.Completable;
import io.reactivex.Flowable;

public class DataStoreRemote implements DataStore {
    private final RemoteDataSource remoteDataSource;

    @Inject
    public DataStoreRemote(RemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
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
    public Completable addCall(CallEntity call) {
        return null;
    }
}
