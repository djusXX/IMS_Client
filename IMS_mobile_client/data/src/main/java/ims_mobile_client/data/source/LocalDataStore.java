package ims_mobile_client.data.source;

import java.util.List;

import javax.inject.Inject;

import ims_mobile_client.data.entities.BuddyEntity;
import ims_mobile_client.data.entities.CallEntity;
import ims_mobile_client.data.entities.MessageEntity;
import ims_mobile_client.data.entities.UserEntity;
import ims_mobile_client.data.repository.DefaultDataStore;
import ims_mobile_client.data.repository.dataLocal;
import io.reactivex.Completable;
import io.reactivex.Flowable;

public class LocalDataStore implements DefaultDataStore {
    private final dataLocal dataLocal;

    @Inject
    public LocalDataStore(dataLocal dataLocal) {
        this.dataLocal = dataLocal;
    }

    @Override
    public Flowable<UserEntity> getLastUser() {
        return dataLocal.getLastUser();
    }

    @Override
    public Completable addUser(UserEntity userEntity) {
        return dataLocal.addUser(userEntity);
    }

    @Override
    public Flowable<List<BuddyEntity>> getBuddiesFor(String userSipUri) {
        return dataLocal.getBuddiesFor(userSipUri);
    }

    @Override
    public Completable addBuddy(BuddyEntity buddyEntity) {
        return dataLocal.addBuddy(buddyEntity);
    }

    @Override
    public Flowable<List<MessageEntity>> getMessagesFor(String usrSipUri, String buddySipUri) {
        return dataLocal.getMessagesFor(usrSipUri, buddySipUri);
    }

    @Override
    public Completable addMessage(MessageEntity message) {
        return dataLocal.addMessage(message);
    }

    @Override
    public Flowable<List<CallEntity>> getCallsFor(String usrSipUri, String buddySipUri) {
        return dataLocal.getCallsFor(usrSipUri, buddySipUri);
    }

    @Override
    public Completable saveCall(CallEntity call) {
        return dataLocal.saveCall(call);
    }
}
