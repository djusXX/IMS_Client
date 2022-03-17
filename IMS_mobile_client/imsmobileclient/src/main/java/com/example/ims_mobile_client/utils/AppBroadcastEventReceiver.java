package com.example.ims_mobile_client.utils;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;

import com.example.ims_mobile_client.data.entities.BuddyEntity;
import com.example.ims_mobile_client.data.entities.MessageEntity;
import com.example.ims_mobile_client.ui.MainActivity;
import com.example.ims_mobile_client.view_models.BuddyViewModel;
import com.example.ims_mobile_client.view_models.MessageViewModel;

import net.gotev.sipservice.BroadcastEventReceiver;
import net.gotev.sipservice.SipBuddyData;

import org.pjsip.pjsua2.pjsip_inv_state;
import org.pjsip.pjsua2.pjsip_status_code;

import java.util.Date;

public class AppBroadcastEventReceiver extends BroadcastEventReceiver {

    private static final String TAG = AppBroadcastEventReceiver.class.getName();

    @Override
    public void onRegistration(String accountID, int registrationStateCode) {
        super.onRegistration(accountID, registrationStateCode);
        if (accountID.isEmpty() && 400 == registrationStateCode) {
            ((MainActivity) getReceiverContext()).logInUser();
        } else if (registrationStateCode == pjsip_status_code.PJSIP_SC_OK) {
            ((MainActivity) getReceiverContext()).onUserLogged(accountID);
        } else {
            ((MainActivity) getReceiverContext()).onLoginError(registrationStateCode);
        }
    }

    @Override
    protected void onBuddyAdded(String accountID, SipBuddyData buddyData) {
        super.onBuddyAdded(accountID, buddyData);
        final BuddyViewModel buddyViewModel = new ViewModelProvider(((MainActivity) getReceiverContext())).get(BuddyViewModel.class);
        buddyViewModel.addBuddy(new BuddyEntity(accountID, buddyData));
    }

    @Override
    protected void onMessageReceived(String from, String to, String body) {
        super.onMessageReceived(from, to, body);
        final MessageViewModel messageViewModel = new ViewModelProvider(((MainActivity) getReceiverContext())).get(MessageViewModel.class);
        messageViewModel.addMessage(new MessageEntity(from, to, new Date().toString(), body));
    }

    @Override
    public void onOutgoingCall(String accountID, int callID, String number, boolean isVideo, boolean isVideoConference, boolean isTransfer) {
        super.onOutgoingCall(accountID, callID, number, isVideo, isVideoConference, isTransfer);
        if (((MainActivity) getReceiverContext()).getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            ((MainActivity) getReceiverContext()).loadPreCallFragment(false, accountID, callID, "", number, isVideo);
        }
    }

    @Override
    public void onCallState(String accountID, int callID, int callStateCode, int callStatusCode, long connectTimestamp) {
        super.onCallState(accountID, callID, callStateCode, callStatusCode, connectTimestamp);

        if (((MainActivity) getReceiverContext()).getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            if (callStateCode == pjsip_inv_state.PJSIP_INV_STATE_CONFIRMED) {
                ((MainActivity) getReceiverContext()).loadActiveCallFragment();
            }

            if (callStatusCode == pjsip_status_code.PJSIP_SC_DECLINE) {
                ((MainActivity) getReceiverContext()).getSupportFragmentManager().popBackStack();
            }

        }

    }


    @Override
    public void onIncomingCall(String accountID, int callID, String displayName, String remoteUri, boolean isVideo) {
        super.onIncomingCall(accountID, callID, displayName, remoteUri, isVideo);
        if (((MainActivity) getReceiverContext()).getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            ((MainActivity) getReceiverContext()).loadPreCallFragment(true, accountID, callID, displayName, remoteUri, isVideo);
        }
    }
}
