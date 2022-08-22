package ims_mobile_client.pjsua2IMS;

import org.pjsip.pjsua2.CallInfo;

import javax.inject.Inject;

import ims_mobile_client.data.sip.SIPManager;
import ims_mobile_client.domain.models.Call;
import ims_mobile_client.domain.models.Message;
import ims_mobile_client.domain.models.PresenceStatus;
import ims_mobile_client.domain.models.StatusType;
import ims_mobile_client.domain.models.User;
import ims_mobile_client.domain.models.UserRegistrationStatus;
import io.reactivex.Completable;
import io.reactivex.Flowable;

public class SIPManagerImpl implements SIPManager {
    private final P2IHelper helper;

    @Inject
    public SIPManagerImpl(P2IHelper helper) {
        this.helper = helper;
    }

    @Override
    public Flowable<UserRegistrationStatus> getRegistrationState() {
        return Flowable.defer(() -> Flowable.just(helper.getLastRegStatus()));
    }

    @Override
    public Flowable<PresenceStatus> getUserPresenceState() {
        return Flowable.defer(() -> Flowable.just(new PresenceStatus(
                helper.getCurrentAccount().getUpdatedAccountInfo().getOnlineStatus() ? StatusType.ONLINE : StatusType.OFFLINE,
                helper.getCurrentAccount().getUpdatedAccountInfo().getOnlineStatusText()
        )));
    }

    @Override
    public Flowable<Call> getCurrentCall(String usrSipUri) {
        return Flowable.defer(() -> Flowable.just(helper.getCurrentCall())).map(p2ICall -> {
            CallInfo ci = p2ICall.getInfo();
            return new Call(ci.getId(),ci.getLocalUri(), ci.getRemoteUri(), p2ICall.isVideoCall());
        });
    }

    @Override
    public Flowable<Message> getIncomingMessageForUser(String usrSipUri) {
        return Flowable.defer(() -> Flowable.just(helper.getCurrentAccount().getMessages()))
                .map(p2IMessages -> {

                    P2IMessage p2IMsg = p2IMessages.get(p2IMessages.size() - 1);
                    return new Message(-1, p2IMsg.getSipUriFrom(), p2IMsg.getSipUriTo(),
                            p2IMsg.getTime(), p2IMsg.getContent());
                });
    }

    @Override
    public Flowable<String> getLoggedUserSipUri() {
        return Flowable.defer(() -> Flowable.just(helper.getCurrentAccount().getInfo().getUri()));
    }



    @Override
    public Completable registerUser(User u) {
        return Completable.defer(() -> {
            helper.registerUser(u);
            return Completable.complete();
        });
    }

    @Override
    public Completable updateUserPresence(PresenceStatus presenceStatus) {
        return Completable.defer(() -> {
            helper.getCurrentAccount().setPresenceStatus(presenceStatus);
            return Completable.complete();
        });
    }

    @Override
    public Completable addNewBuddy(String buddySipUri, String buddyDisplayName) {
        return Completable.defer(() -> {
            helper.getCurrentAccount().addBuddy(buddySipUri, buddyDisplayName);
            return Completable.complete();
        });
    }
}
