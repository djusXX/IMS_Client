package ims_mobile_client.domain.pjsua2IMSservice;

import android.view.Surface;

import java.util.List;

/**
 * Provide Observables(RxJava) to get data from pjsua2 library
 * */
public interface pjsua2IMS {

//    boolean startLib();
//    boolean stopLib();


    /*** LOCAL USER ***/
    void loginUser(UserEntity user);
    void logoutCurrentUser();
    void removeUser(UserEntity user);
    void setCodecPriority(List<String> codecs);
    List<String> getCodecPriorityList();
    String getRegState(UserEntity user);
    void refreshRegState(UserEntity user, int regTimeout, String params);


    /*** CALLS ***/
    void makeCall(CallEntity call);
    void getCallStatus(CallEntity call);
    void endCall(CallEntity call);
    /**
     * @param sequence should contain only [0-9, *, #]
     * */
    void sendDTMF(CallEntity call, String sequence);
    void acceptIncomingCall(CallEntity call);
    void rejectIncomingCall(CallEntity call);
    void transferCall(CallEntity call);
    void reconnectCall(); // caused changed IP, managed by Endpoint
    void toggleOnHold(CallEntity call);
    void toggleMute(CallEntity call);
    void toggleVideoMute(CallEntity call);
    void setIncomingVideoFeed(CallEntity call, Surface surface);
    void setOutgoingVideoFeed(CallEntity call, Surface surface);
    void unsetOutgoingVideoFeed(CallEntity call, Surface surface);
    void setOutgoingVideoOrientation(CallEntity call, int orientation); // Surface.ROTATION_0/90/180/270
    void switchCamera(CallEntity call);


    /*** BUDDIES ***/
    void addBuddy(BuddyEntity buddy);
    void setBuddySubscription(BuddyEntity buddy, boolean subState);
    void sendMessageTo(BuddyEntity buddy, MessageEntity message);


    // loadNativeLibs()


}
