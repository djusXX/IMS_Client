package ims_mobile_client.domain.service;

import java.util.List;

import ims_mobile_client.domain.models.Buddy;
import ims_mobile_client.domain.models.Call;
import ims_mobile_client.domain.models.Message;
import ims_mobile_client.domain.models.User;
import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface IMCSipService {

    Completable logIn(User user);
    Completable logOut(User user);
    Completable setUserPresence(User user);

    Completable addBuddy(Buddy buddy);
    Completable removeBuddy(Buddy buddy);
    Flowable<Buddy> getBuddyPresence(Buddy buddy);
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

    // to handle calls, messages, userState changes use below
    Flowable<List<User>> getConfiguredUsers();
    Flowable<List<Call>> getActiveCalls(/*String usrSipUri*/);
    Flowable<List<Message>> getMessages();  // handle incoming message
}
