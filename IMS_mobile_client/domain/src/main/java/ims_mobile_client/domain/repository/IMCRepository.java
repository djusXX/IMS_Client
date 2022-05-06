package ims_mobile_client.domain.repository;

import java.util.List;

import ims_mobile_client.domain.entities.Buddy;
import ims_mobile_client.domain.entities.Call;
import ims_mobile_client.domain.entities.Message;
import ims_mobile_client.domain.entities.User;
import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface IMCRepository {

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

}
