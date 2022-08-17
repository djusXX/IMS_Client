package ims_mobile_client.pjsua2IMS;

import ims_mobile_client.data.sip.SIPManager;
import ims_mobile_client.domain.models.Call;
import ims_mobile_client.domain.models.Message;
import ims_mobile_client.domain.models.PresenceStatus;
import ims_mobile_client.domain.models.User;
import ims_mobile_client.domain.models.UserLoggedStatus;
import io.reactivex.Completable;
import io.reactivex.Flowable;

public class SIPManagerImpl implements SIPManager {

    @Override
    public Completable registerUser(User u) {
        return null;
    }

    @Override
    public Flowable<UserLoggedStatus> getRegistrationState() {
        return null;
    }

    @Override
    public Flowable<PresenceStatus> getUserPresenceState() {
        return null;
    }

    @Override
    public Completable updateUserPresence(PresenceStatus presenceStatus) {
        return null;
    }

    @Override
    public Flowable<Call> getCurrentCall(String usrSipUri) {
        return null;
    }

    @Override
    public Flowable<Message> getIncomingMessageForUser(String usrSipUri) {
        return null;
    }

    @Override
    public Flowable<String> getLoggedUserSipUri() {
        return null;
    }

    @Override
    public Completable addNewBuddy(String buddySipUri, String buddyDisplayName) {
        return null;
    }
}
