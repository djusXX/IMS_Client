package com.example.ims_mobile_client.model;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;

import com.example.ims_mobile_client.view.MainActivity;

import java.util.Date;

public interface LocalBroadcastReceiver {

    void onRegistration(String accountID, int registrationStateCode);

    void onBuddyAdded(String accountID, BuddyData buddyData);
    void onBuddyState(String ownerSipUri, String contactUri, String presStatus, String presText);

    void onMessageReceived(String from, String to, String body);

    void onOutgoingCall(String accountID, int callID, String number, boolean isVideo, boolean isVideoConference, boolean isTransfer);

    void onCallState(String accountID, int callID, int callStateCode, int callStatusCode, long connectTimestamp);
    void onIncomingCall(String accountID, int callID, String displayName, String remoteUri, boolean isVideo);
}
