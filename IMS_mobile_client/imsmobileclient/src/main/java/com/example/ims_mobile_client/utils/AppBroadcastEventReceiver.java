package com.example.ims_mobile_client.utils;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;

import com.example.ims_mobile_client.R;
import com.example.ims_mobile_client.data.entities.BuddyEntity;
import com.example.ims_mobile_client.data.entities.MessageEntity;
import com.example.ims_mobile_client.model.AppBuddy;
import com.example.ims_mobile_client.ui.BuddyListFragment;
import com.example.ims_mobile_client.ui.ConversationFragment;
import com.example.ims_mobile_client.ui.IncomingCallFragment;
import com.example.ims_mobile_client.ui.LoginFragment;
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

//    private static MainActivity mainActivity = null;
//
//    @Override
//    public void register(Context context) {
//        super.register(context);
//        mainActivity = (MainActivity) context;
//    }
//
//    @Override
//    public void unregister(Context context) {
//        super.unregister(context);
//    }

    @Override
    protected void onBuddyAdded(String accountID, SipBuddyData buddyData) {
        super.onBuddyAdded(accountID, buddyData);
        if (((MainActivity) getReceiverContext()).getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            ConversationFragment conversationFragment = new ConversationFragment(accountID, buddyData.getBuddyUri());
            ((MainActivity) getReceiverContext()).getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack("BuddyAdded")
                    .setReorderingAllowed(true)
                    .replace(R.id.main_fragment_container, conversationFragment , null).commit();
        }
    }

//    @Override
//    public void onOutgoingCall(String accountID, int callID, String number, boolean isVideo, boolean isVideoConference) {
//        super.onOutgoingCall(accountID, callID, number, isVideo, isVideoConference);
//        if (((MainActivity) getReceiverContext()).getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
//            OutgoingCallFragment outgoingCallFragment = new OutgoingCallFragment();
//            ((MainActivity) getReceiverContext()).getSupportFragmentManager()
//                    .beginTransaction()
//                    .addToBackStack("outgoingCall")
//                    .setReorderingAllowed(true)
//                    .replace(R.id.main_fragment_container, outgoingCallFragment
//                            , null).commit();
//        }
//    }
//
//    @Override
//    public void onCallState(String accountID, int callID, int callStateCode, int callStatusCode, long connectTimestamp, boolean isLocalHold, boolean isLocalMute, boolean isLocalVideoMute) {
//        super.onCallState(accountID, callID, callStateCode, callStatusCode, connectTimestamp, isLocalHold, isLocalMute, isLocalVideoMute);
//        if (pjsip_inv_state.PJSIP_INV_STATE_NULL < callStateCode && callStateCode < pjsip_inv_state.PJSIP_INV_STATE_CONFIRMED) {
//            if (((MainActivity) getReceiverContext()).getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
//                OutgoingCallFragment outgoingCallFragment = new OutgoingCallFragment();
//                ((MainActivity) getReceiverContext()).getSupportFragmentManager()
//                        .beginTransaction()
//                        .addToBackStack("outgoingCall")
//                        .setReorderingAllowed(true)
//                        .replace(R.id.main_fragment_container, outgoingCallFragment, null).commit();
//            }
//        }
//    }

    @Override
    protected void onMessageReceived(String from, String to, String body) {
        super.onMessageReceived(from, to, body);
        final MessageViewModel messageViewModel = new ViewModelProvider(((MainActivity) getReceiverContext())).get(MessageViewModel.class);
        messageViewModel.addMessage(new MessageEntity(from, to, new Date().toString(), body));
    }

    @Override
    public void onIncomingCall(String accountID, int callID, String displayName, String remoteUri, boolean isVideo) {
        super.onIncomingCall(accountID, callID, displayName, remoteUri, isVideo);
        if (((MainActivity) getReceiverContext()).getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            IncomingCallFragment incomingCallFragment = new IncomingCallFragment();
            ((MainActivity) getReceiverContext()).getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack("incomingCall")
                    .setReorderingAllowed(true)
                    .replace(R.id.main_fragment_container, incomingCallFragment, null).commit();
        }
    }
}
