package ims_mobile_client;

import android.util.Log;

import javax.inject.Inject;

import ims_mobile_client.domain.models.Buddy;
import ims_mobile_client.domain.models.Call;
import ims_mobile_client.domain.models.Message;
import ims_mobile_client.domain.models.User;
import ims_mobile_client.domain.service.IMCSipService;
import ims_mobile_client.pjsua2IMS.SipManager;
import io.reactivex.Completable;
import io.reactivex.Flowable;

public class IMCSipServiceImpl implements IMCSipService {
    public static final String TAG = IMCSipServiceImpl.class.getSimpleName();

    private final SipManager manager;

    @Inject
    public IMCSipServiceImpl(SipManager manager) {
        Log.d(TAG, "creating implementation of IMCSipService");
        this.manager = manager;
    }

    @Override
    public Completable logIn(User user) {
        Log.d(TAG, "called method logIn()");
        manager.logIn(user);
        return Completable.error(new Exception("Method Not Implemented"));
    }

    @Override
    public Completable logOut(User user) {
        return Completable.error(new Exception("Method Not Implemented"));
    }

    @Override
    public Completable setUserPresence(User user) {
        return Completable.error(new Exception("Method Not Implemented"));
    }

    @Override
    public Completable addBuddy(Buddy buddy) {
        return Completable.error(new Exception("Method Not Implemented"));
    }

    @Override
    public Completable removeBuddy(Buddy buddy) {
        return Completable.error(new Exception("Method Not Implemented"));
    }

    @Override
    public Flowable<Buddy> getBuddyPresence(Buddy buddy) {
        return Flowable.error(new Exception("Method Not Implemented"));
    }

    @Override
    public Completable sendMessage(Message message) {
        return Completable.error(new Exception("Method Not Implemented"));
    }

    @Override
    public Completable makeCall(Call call) {
        return Completable.error(new Exception("Method Not Implemented"));
    }

    @Override
    public Completable endCall(Call call) {
        return Completable.error(new Exception("Method Not Implemented"));
    }

    @Override
    public Completable acceptIncomingCall(Call call) {
        return Completable.error(new Exception("Method Not Implemented"));
    }

    @Override
    public Completable rejectIncomingCall(Call call) {
        return Completable.error(new Exception("Method Not Implemented"));
    }

    @Override
    public Completable transferCall(Call call) {
        return Completable.error(new Exception("Method Not Implemented"));
    }

    @Override
    public Completable toggleOnHold(Call call) {
        return Completable.error(new Exception("Method Not Implemented"));
    }

    @Override
    public Completable toggleMute(Call call) {
        return Completable.error(new Exception("Method Not Implemented"));
    }

    @Override
    public Completable toggleVideoMute(Call call) {
        return Completable.error(new Exception("Method Not Implemented"));
    }

    @Override
    public Completable switchCamera(Call call) {
        return Completable.error(new Exception("Method Not Implemented"));
    }

    @Override
    public Completable sendDTMF(Call call, String sequence) {
        return Completable.error(new Exception("Method Not Implemented"));
    }

    @Override
    public Flowable<User.Info> getUserInfo(String usrSipUri) {
        return null;
    }


    //    boolean startLib();
//    boolean stopLib();
//     loadNativeLibs()

//    /*** LOCAL USER ***/
//    void loginUser(UserEntity user);
//    void logoutCurrentUser();
//    void removeUser(UserEntity user);
//    void setCodecPriority(List<String> codecs);
//    List<String> getCodecPriorityList();
//    String getRegState(UserEntity user);
//    void refreshRegState(UserEntity user, int regTimeout, String params);
//
//
//    /*** CALLS ***/
//    void makeCall(CallEntity call);
//    void getCallStatus(CallEntity call);
//    void endCall(CallEntity call);
//    /**
//     * @param sequence should contain only [0-9, *, #]
//     * */
//    void sendDTMF(CallEntity call, String sequence);
//    void acceptIncomingCall(CallEntity call);
//    void rejectIncomingCall(CallEntity call);
//    void transferCall(CallEntity call);
//    void reconnectCall(); // caused changed IP, managed by Endpoint
//    void toggleOnHold(CallEntity call);
//    void toggleMute(CallEntity call);
//    void toggleVideoMute(CallEntity call);
//    void switchCamera(CallEntity call);
//    void setIncomingVideoFeed(CallEntity call, Surface surface);
//    void setOutgoingVideoFeed(CallEntity call, Surface surface);
//    void unsetOutgoingVideoFeed(CallEntity call, Surface surface);
//    void setOutgoingVideoOrientation(CallEntity call, int orientation); // Surface.ROTATION_0/90/180/270
//    void reconnectCall(); // caused changed IP, managed by Endpoint
//
//    /*** BUDDIES ***/
//    void addBuddy(BuddyEntity buddy);
//    void setBuddySubscription(BuddyEntity buddy, boolean subState);
//    void sendMessageTo(BuddyEntity buddy, MessageEntity message);
}
