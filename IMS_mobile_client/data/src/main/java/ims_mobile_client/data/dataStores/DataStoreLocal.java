package ims_mobile_client.data.dataStores;

import java.util.List;

import javax.inject.Inject;

import ims_mobile_client.data.models.BuddyEntity;
import ims_mobile_client.data.models.CallEntity;
import ims_mobile_client.data.models.MessageEntity;
import ims_mobile_client.data.models.UserEntity;
import ims_mobile_client.data.dataSources.LocalDataSource;
import io.reactivex.Completable;
import io.reactivex.Flowable;

public class DataStoreLocal implements DataStore {
    private final LocalDataSource localSource;

    @Inject
    public DataStoreLocal(LocalDataSource localSource) {
        this.localSource = localSource;
    }

    @Override
    public Flowable<UserEntity> getUser(String usrSpiUri) {
        return localSource.getUser(usrSpiUri);
    }

    @Override
    public Flowable<UserEntity> getLastUser() {
        return localSource.getLastUser();
    }

    @Override
    public Completable addUser(UserEntity userEntity) {
        return localSource.addUser(userEntity);
    }

    @Override
    public Flowable<List<BuddyEntity>> getBuddiesFor(String userSipUri) {
        return localSource.getBuddiesFor(userSipUri);
    }

    @Override
    public Flowable<BuddyEntity> getBuddy(String usrSipUri, String buddySipUri) {
        return null;
    }

    @Override
    public Completable addBuddy(BuddyEntity buddyEntity) {
        return localSource.addBuddy(buddyEntity);
    }

    @Override
    public Flowable<List<MessageEntity>> getMessagesFor(String usrSipUri, String buddySipUri) {
        return localSource.getMessagesFor(usrSipUri, buddySipUri);
    }

    @Override
    public Completable addMessage(MessageEntity message) {
        return localSource.addMessage(message);
    }

    @Override
    public Flowable<List<CallEntity>> getCallsFor(String usrSipUri, String buddySipUri) {
        return localSource.getCallsFor(usrSipUri, buddySipUri);
    }

    @Override
    public Completable addCall(CallEntity call) {
        return localSource.addCall(call);
    }
}
