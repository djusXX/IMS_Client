package ims_mobile_client.domain.repository;

import java.util.List;

import ims_mobile_client.domain.models.Buddy;
import ims_mobile_client.domain.models.Call;
import ims_mobile_client.domain.models.Message;
import ims_mobile_client.domain.models.PresenceStatus;
import ims_mobile_client.domain.models.User;
import ims_mobile_client.domain.models.UserLoggedStatus;
import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface IMSRepository {

    /*********** DataStore ***********/
    // User
    Flowable<User> getLastUser();
    Flowable<User> getUser(String userSipUri);
    Completable addUser(User user);

    // Buddies
    Flowable<List<Buddy>> getBuddiesFor(String userSipUri);
    Flowable<Buddy> getBuddy(String userSipUri, String buddySipUri);
    Completable addBuddy(Buddy buddy);

    // Messages
    Flowable<List<Message>> getMessagesFor(String usrSipUri, String buddySipUri);
    Completable addMessage(Message message);

    // Calls
    Flowable<List<Call>> getCallsFor(String usrSipUri, String buddySipUri);
    Completable addCall(Call call);


    /*********** IMS/SIP ***********/
    Flowable<UserLoggedStatus> getRegistrationState(String usrSipUri);

    Flowable<PresenceStatus> getUserPresenceState(String usrSipUri);

    Flowable<Message> getIncomingMessageForUser(String usrSipUri);

    Flowable<Call> getIncomingCallForUser(String usrSipUri);

    Completable registerUser(User u);

    Completable updateUserPresence(PresenceStatus presenceStatus);
}
