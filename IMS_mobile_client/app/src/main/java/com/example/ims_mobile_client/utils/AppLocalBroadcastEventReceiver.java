package com.example.ims_mobile_client.utils;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;

import com.example.ims_mobile_client.model.LocalBroadcastReceiver;
import com.example.ims_mobile_client.model.BuddyData;
import com.example.ims_mobile_client.view.MainActivity;

import java.util.Date;

public class AppLocalBroadcastEventReceiver implements LocalBroadcastReceiver {

    private static final String TAG = AppLocalBroadcastEventReceiver.class.getName();

    @Override
    public void onRegistration(String accountID, int registrationStateCode) {
        if (registrationStateCode == 200) {
            ((MainActivity) requireActivity()).onUserLogged(accountID);
        } else if (accountID.isEmpty() && (400 == registrationStateCode || 401 == registrationStateCode)) {
            ((MainActivity) getReceiverContext()).logInUser(registrationStateCode);
        } else {
            ((MainActivity) getReceiverContext()).onLoginError(registrationStateCode);
        }
    }


    @Override
    public void onBuddyAdded(String accountID, BuddyData buddyData) {
        final BuddyViewModel buddyViewModel = new ViewModelProvider(((MainActivity) getReceiverContext())).get(BuddyViewModel.class);
        buddyViewModel.addBuddy(new BuddyEntity(accountID, buddyData));
    }

    @Override
    public void onBuddyState(String ownerSipUri, String contactUri, String presStatus, String presText) {
        if (((MainActivity) getReceiverContext()).getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            ((MainActivity) getReceiverContext()).updateBuddyState(ownerSipUri, contactUri, presStatus, presText);
        }
    }

    @Override
    public void onMessageReceived(String from, String to, String body) {
        final MessageViewModel messageViewModel = new ViewModelProvider(((MainActivity) getReceiverContext())).get(MessageViewModel.class);
        messageViewModel.addMessage(new MessageEntity(from, to, new Date().toString(), body));
    }

    @Override
    public void onOutgoingCall(String accountID, int callID, String number, boolean isVideo, boolean isVideoConference, boolean isTransfer) {
        if (((MainActivity) getReceiverContext()).getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            ((MainActivity) getReceiverContext()).loadPreCallFragment(false, accountID, callID, "", number, isVideo);
        }
    }

    @Override
    public void onCallState(String accountID, int callID, int callStateCode, int callStatusCode, long connectTimestamp) {

        if (((MainActivity) getReceiverContext()).getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            if (callStateCode == pjsip_inv_state.PJSIP_INV_STATE_CONFIRMED) {
                ((MainActivity) getReceiverContext()).loadActiveCallFragment(accountID, callID);
            }

            if (callStateCode == pjsip_inv_state.PJSIP_INV_STATE_DISCONNECTED || callStateCode == pjsip_inv_state.PJSIP_INV_STATE_NULL) {
                ((MainActivity) getReceiverContext()).getSupportFragmentManager().popBackStack();
            }

            if (callStatusCode == pjsip_status_code.PJSIP_SC_DECLINE) {
                ((MainActivity) getReceiverContext()).getSupportFragmentManager().popBackStack();
            }


        }

    }


    @Override
    public void onIncomingCall(String accountID, int callID, String displayName, String remoteUri, boolean isVideo) {

        final CallViewModel callViewModel = new ViewModelProvider(((MainActivity) getReceiverContext())).get(CallViewModel.class);
        callViewModel.addCall(new CallEntity(remoteUri, accountID, isVideo, "", ""));

        if (((MainActivity) getReceiverContext()).getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            ((MainActivity) getReceiverContext()).loadPreCallFragment(true, accountID, callID, displayName, remoteUri, isVideo);
        }
    }
}