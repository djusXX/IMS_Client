package com.example.ims_mobile_client.utils;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;

import com.example.ims_mobile_client.R;
import com.example.ims_mobile_client.calls.IncomingCallActivity;
import com.example.ims_mobile_client.data.entities.BuddyEntity;
import com.example.ims_mobile_client.data.entities.MessageEntity;
import com.example.ims_mobile_client.ui.BuddyListFragment;
import com.example.ims_mobile_client.ui.ConversationFragment;
import com.example.ims_mobile_client.ui.IncomingCallFragment;
import com.example.ims_mobile_client.ui.MainActivity;
import com.example.ims_mobile_client.ui.OutgoingCallFragment;
import com.example.ims_mobile_client.view_models.BuddyViewModel;
import com.example.ims_mobile_client.view_models.MessageViewModel;

import net.gotev.sipservice.BroadcastEventReceiver;
import net.gotev.sipservice.Logger;
import net.gotev.sipservice.SipBuddyData;

import org.pjsip.pjsua2.pjsip_inv_state;
import org.pjsip.pjsua2.pjsip_status_code;

import java.util.Date;

public class AppBroadcastEventReceiver extends BroadcastEventReceiver {

    private static final String LOG_TAG = "AppBroadcastEventReceiver";

    private MainActivity appContext;

    @Override
    public void register(Context context) {
        Logger.debug(LOG_TAG, "registering " + LOG_TAG);
        super.register(context);
        appContext = (MainActivity) context;
    }

    @Override
    public void unregister(Context context) {
        Logger.debug(LOG_TAG, "unregistering " + LOG_TAG);
        super.unregister(context);
        appContext = null;
    }

    @Override
    public void onRegistration(String accountID, int registrationStateCode) {
        super.onRegistration(accountID, registrationStateCode);
        Logger.debug(LOG_TAG, "called onRegistration()");
        if (accountID.isEmpty() && 400 == registrationStateCode) {
            appContext.setAccount();
        } else if (registrationStateCode == pjsip_status_code.PJSIP_SC_OK) {
//            appContext.addBuddyListFragment();
            appContext.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_fragment_container, new BuddyListFragment(appContext.getCurrentUser()), BuddyListFragment.TAG)
                    .commit();
        } else {
            Toast.makeText(appContext, "error: " + registrationStateCode, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onBuddyAdded(String accountID, SipBuddyData buddyData) {
        super.onBuddyAdded(accountID, buddyData);
        BuddyEntity buddyEntity = new BuddyEntity(accountID, buddyData.getSipUri(), buddyData.getDisplayName());
        final BuddyViewModel buddyViewModel = new ViewModelProvider(appContext).get(BuddyViewModel.class);
        buddyViewModel.addBuddy(buddyEntity);
        if (appContext.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            appContext.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_fragment_container,
                            new ConversationFragment(appContext.getCurrentUser(), buddyEntity), null).commit();
        }
    }

    @Override
    public void onOutgoingCall(String accountID, int callID, String number, boolean isVideo, boolean isVideoConference) {
        super.onOutgoingCall(accountID, callID, number, isVideo, isVideoConference);
        if (appContext.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            appContext.getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack("outgoingCall")
                    .replace(R.id.main_fragment_container,
                            new OutgoingCallFragment(), null).commit();
        }
    }

    @Override
    public void onCallState(String accountID, int callID, int callStateCode, int callStatusCode, long connectTimestamp, boolean isLocalHold, boolean isLocalMute, boolean isLocalVideoMute) {
        super.onCallState(accountID, callID, callStateCode, callStatusCode, connectTimestamp, isLocalHold, isLocalMute, isLocalVideoMute);
        if (pjsip_inv_state.PJSIP_INV_STATE_NULL < callStateCode && callStateCode < pjsip_inv_state.PJSIP_INV_STATE_CONFIRMED) {
            if (appContext.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                appContext.getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack("outgoingCall")
                        .replace(R.id.main_fragment_container,
                                new OutgoingCallFragment(), null).commit();
            }
        }
    }

    @Override
    protected void onMessageReceived(String from, String to, String body) {
        super.onMessageReceived(from, to, body);
        if(!appContext.getCurrentUser().getIdUri().equals(to)) {
            return;
        }

        final MessageViewModel messageViewModel = new ViewModelProvider(appContext).get(MessageViewModel.class);
        messageViewModel.addMessage(new MessageEntity(from, to, new Date().toString(), body));
    }

    @Override
    public void onIncomingCall(String accountID, int callID, String displayName, String remoteUri, boolean isVideo) {
        super.onIncomingCall(accountID, callID, displayName, remoteUri, isVideo);
        if (appContext.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            appContext.getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack("incomingCall")
                    .replace(R.id.main_fragment_container,
                            new IncomingCallFragment(), null).commit();
        }
    }
}
