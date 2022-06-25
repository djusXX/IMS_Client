package ims_mobile_client;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import ims_mobile_client.domain.models.Buddy;
import ims_mobile_client.domain.models.Call;
import ims_mobile_client.domain.models.Message;
import ims_mobile_client.domain.models.User;
import ims_mobile_client.domain.service.IMSService;
import io.reactivex.Flowable;

public class IMSServiceImpl implements IMSService {
    public static final String TAG = IMSServiceImpl.class.getSimpleName();

    private final ImsCallingManager imsCallingManager;
    private final ImsMessagingManager imsMessagingManager;
    private final ImsUserManager imsUserManager;
    private final ImsBuddyManager imsBuddyManager;

    @Inject
    public IMSServiceImpl(ImsCallingManager imsCallingManager, ImsMessagingManager imsMessagingManager, ImsUserManager imsUserManager, ImsBuddyManager imsBuddyManager) {
        this.imsCallingManager = imsCallingManager;
        this.imsMessagingManager = imsMessagingManager;
        this.imsUserManager = imsUserManager;
        this.imsBuddyManager = imsBuddyManager;
        Log.d(TAG, "creating implementation of IMCSipService");
    }

    @Override
    public Flowable<List<User>> getUsers() {
        return null;
    }

    @Override
    public Flowable<List<Call>> getCalls() {
        return null;
    }

    @Override
    public Flowable<List<Buddy>> getBuddies() {
        return null;
    }

    @Override
    public Flowable<List<Message>> getMessages() {
        return null;
    }

    @Override
    public Flowable<User> getUserData(int id) {
        return null;
    }

    @Override
    public Flowable<Call> getCallData(int id) {
        return null;
    }

    @Override
    public Flowable<Buddy> getBuddyData(int id) {
        return null;
    }

    @Override
    public Flowable<Message> getMessageData(int id) {
        return null;
    }

    @Override
    public Flowable<User> setUser(User user) {
        return null;
    }

    @Override
    public Flowable<Call> setCall(Call call) {
        return null;
    }

    @Override
    public Flowable<Buddy> setBuddy(Buddy buddy) {
        return null;
    }

    @Override
    public Flowable<Message> setMessageData(Message message) {
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
