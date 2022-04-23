package ims_mobile_client.domain.service;

import ims_mobile_client.domain.entities.Buddy;
import ims_mobile_client.domain.entities.Call;
import ims_mobile_client.domain.entities.Message;
import ims_mobile_client.domain.entities.User;
import io.reactivex.Completable;

public interface pjsua2ImsService {

    Completable logIn(User user);
    Completable logOut(User user);
    Completable setUserPresence(User user);

    Completable addBuddy(Buddy buddy);
    Completable removeBuddy(Buddy buddy);
    Completable refreshBuddyPresence(Buddy buddy);
    Completable sendMessage(Message message);

    Completable makeCall(Call call);
    Completable endCall(Call call);
    Completable acceptIncomingCall(Call call);
    Completable rejectIncomingCall(Call call);
    Completable transferCall(Call call);
    Completable toggleOnHold(Call call);
    Completable toggleMute(Call call);
    Completable toggleVideoMute(Call call);
    Completable switchCamera(Call call);
    Completable sendDTMF(Call call, String sequence);
    Completable reconnectCall(); // caused changed IP, managed by Endpoint

}
