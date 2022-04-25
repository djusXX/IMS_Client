package ims_mobile_client.data.source;

import java.util.List;

import javax.inject.Inject;

import ims_mobile_client.data.entities.BuddyEntity;
import ims_mobile_client.data.entities.CallEntity;
import ims_mobile_client.data.entities.MessageEntity;
import ims_mobile_client.data.entities.UserEntity;
import ims_mobile_client.data.repository.DefaultDataStore;
import io.reactivex.Completable;
import io.reactivex.Flowable;

public class LocalDataStore implements DefaultDataStore {
    private final ims_mobile_client.data.repository.LocalDataStore LocalDataStore;

    @Inject
    public LocalDataStore(ims_mobile_client.data.repository.LocalDataStore LocalDataStore) {
        this.LocalDataStore = LocalDataStore;
    }

    @Override
    public Flowable<UserEntity> getLastUser() {
        return LocalDataStore.getLastUser();
    }

    @Override
    public Completable addUser(UserEntity userEntity) {
        return LocalDataStore.addUser(userEntity);
    }

    @Override
    public Flowable<List<BuddyEntity>> getBuddiesFor(String userSipUri) {
        return LocalDataStore.getBuddiesFor(userSipUri);
    }

    @Override
    public Completable addBuddy(BuddyEntity buddyEntity) {
        return LocalDataStore.addBuddy(buddyEntity);
    }

    @Override
    public Flowable<List<MessageEntity>> getMessagesFor(String usrSipUri, String buddySipUri) {
        return LocalDataStore.getMessagesFor(usrSipUri, buddySipUri);
    }

    @Override
    public Completable addMessage(MessageEntity message) {
        return LocalDataStore.addMessage(message);
    }

    @Override
    public Flowable<List<CallEntity>> getCallsFor(String usrSipUri, String buddySipUri) {
        return LocalDataStore.getCallsFor(usrSipUri, buddySipUri);
    }

    @Override
    public Completable saveCall(CallEntity call) {
        return LocalDataStore.saveCall(call);
    }
}
