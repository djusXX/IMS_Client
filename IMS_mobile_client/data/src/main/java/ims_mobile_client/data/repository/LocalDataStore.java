package ims_mobile_client.data.repository;

import java.util.List;

import ims_mobile_client.data.entities.BuddyEntity;
import ims_mobile_client.data.entities.CallEntity;
import ims_mobile_client.data.entities.MessageEntity;
import ims_mobile_client.data.entities.UserEntity;
import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface LocalDataStore {

    Flowable<UserEntity> getUser(String usrSpiUri);
    Flowable<UserEntity> getLastUser();
    Completable addUser(UserEntity userEntity);

    Flowable<List<BuddyEntity>> getBuddiesFor(String userSipUri);
    Flowable<BuddyEntity> getBuddy(String usrSipUri, String buddySipUri);
    Completable addBuddy(BuddyEntity buddyEntity);

    Flowable<List<MessageEntity>> getMessagesFor(String usrSipUri, String buddySipUri);
    Completable addMessage(MessageEntity message);

    Flowable<List<CallEntity>> getCallsFor(String usrSipUri, String buddySipUri);
    Completable saveCall(CallEntity call);

}
