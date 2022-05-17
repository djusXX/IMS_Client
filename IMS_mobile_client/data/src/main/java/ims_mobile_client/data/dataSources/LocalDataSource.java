package ims_mobile_client.data.dataSources;

import java.util.List;

import ims_mobile_client.data.models.BuddyEntity;
import ims_mobile_client.data.models.CallEntity;
import ims_mobile_client.data.models.MessageEntity;
import ims_mobile_client.data.models.UserEntity;
import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface LocalDataSource {

    Flowable<UserEntity> getUser(String usrSpiUri);
    Flowable<UserEntity> getLastUser();
    Completable addUser(UserEntity userEntity);

    Flowable<List<BuddyEntity>> getBuddiesFor(String userSipUri);
    Flowable<BuddyEntity> getBuddy(String usrSipUri, String buddySipUri);
    Completable addBuddy(BuddyEntity buddyEntity);

    Flowable<List<MessageEntity>> getMessagesFor(String usrSipUri, String buddySipUri);
    Completable addMessage(MessageEntity message);

    Flowable<List<CallEntity>> getCallsFor(String usrSipUri, String buddySipUri);
    Completable addCall(CallEntity call);

}
