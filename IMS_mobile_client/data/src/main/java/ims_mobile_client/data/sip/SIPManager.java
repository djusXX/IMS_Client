package ims_mobile_client.data.sip;

import ims_mobile_client.domain.models.Call;
import ims_mobile_client.domain.models.Message;
import ims_mobile_client.domain.models.PresenceStatus;
import ims_mobile_client.domain.models.User;
import ims_mobile_client.domain.models.UserLoggedStatus;
import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface SIPManager {

    Completable registerUser(User u);
    Flowable<UserLoggedStatus> getRegistrationState(String usrSipUri);
    Flowable<PresenceStatus> getUserPresenceState(String usrSipUri);
    Completable updateUserPresence(PresenceStatus presenceStatus);


    // addBuddy
    // addBuddies (List)
    // getBuddyPresence


    // getIncomingCall
    // getCallState
    // getCallMediaState


    Flowable<Call> getIncomingCallForUser(String usrSipUri);


    Flowable<Message> getIncomingMessageForUser(String usrSipUri);
}
