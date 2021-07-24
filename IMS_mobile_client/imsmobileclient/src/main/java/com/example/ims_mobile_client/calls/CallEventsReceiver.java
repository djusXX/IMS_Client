package com.example.ims_mobile_client.calls;

import android.content.Intent;
import net.gotev.sipservice.BroadcastEventReceiver;

public class CallEventsReceiver extends BroadcastEventReceiver {
        @Override
        public void onIncomingCall(String accountID, int callID, String displayName, String remoteUri, boolean isVideo) {
            super.onIncomingCall(accountID, callID, displayName, remoteUri, isVideo);
            Intent intent = new Intent(getReceiverContext(), IncomingCallActivity.class);
            intent.putExtra(PARAM_ACCOUNT_ID, accountID);
            intent.putExtra(PARAM_CALL_ID, callID);
            intent.putExtra(PARAM_DISPLAY_NAME, displayName);
            intent.putExtra(PARAM_REMOTE_URI, remoteUri);
            intent.putExtra(PARAM_IS_VIDEO, isVideo);
            getReceiverContext().startActivity(intent);
        }

    }

