package ims_mobile_client.domain.repository;

import java.util.List;

import ims_mobile_client.domain.models.Buddy;
import ims_mobile_client.domain.models.Call;
import ims_mobile_client.domain.models.Message;
import ims_mobile_client.domain.models.PresenceStatus;
import ims_mobile_client.domain.models.User;
import ims_mobile_client.domain.models.UserRegistrationStatus;
import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface IMSRepository {

    /*********** DataStore ***********/
    // User
    Flowable<User> getLastUser();
    Flowable<User> getUser(String userSipUri);
    Completable saveUser(User user);

    // Buddies
    Flowable<List<Buddy>> getBuddiesFor();
    Flowable<Buddy> getBuddy(String userSipUri, String buddySipUri);
    Completable saveBuddy(Buddy buddy);

    // Messages
    Flowable<List<Message>> getMessagesFor(String buddySipUri);
    Completable saveMessage(Message message);

    // Calls
    Flowable<List<Call>> getCallsFor(String usrSipUri, String buddySipUri);
    Completable saveCall(Call call);


    /*********** IMS/SIP ***********/
    Flowable<UserRegistrationStatus> getRegistrationState();

    Flowable<PresenceStatus> getUserPresenceState();

    Flowable<Message> getIncomingMessage(String usrSipUri);

    Flowable<Call> getCurrentCall();

    Flowable<String> getLoggedUserSipUri();

    Completable registerUser(User u);

    Completable updateUserPresence(PresenceStatus presenceStatus);

    Completable addNewBuddy(String buddySipUri, String buddyDisplayName);

    Completable sendMessage(String buddySipUri, String content);

    Completable makeCall(String buddySipUri, boolean isVideo);
}
