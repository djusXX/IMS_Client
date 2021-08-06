package com.example.ims_mobile_client.calls;

import android.content.Context;
import android.content.Intent;
import net.gotev.sipservice.BroadcastEventReceiver;
import net.gotev.sipservice.Logger;

public class CallEventsReceiver extends BroadcastEventReceiver {
//    public boolean isRegistered = false;
    Context context;


    @Override
    public void register(Context context) {
        super.register(context);
//        isRegistered = true;
        this.context = context;
    }

    @Override
    public void unregister(Context context) {
        super.unregister(context);
//        isRegistered = false;
    }

    @Override
    public void onIncomingCall(String accountID, int callID, String displayName, String remoteUri, boolean isVideo) {
        super.onIncomingCall(accountID, callID, displayName, remoteUri, isVideo);
        Intent intent = new Intent(context, IncomingCallActivity.class);
        intent.putExtra(PARAM_ACCOUNT_ID, accountID);
        intent.putExtra(PARAM_CALL_ID, callID);
        intent.putExtra(PARAM_DISPLAY_NAME, displayName);
        intent.putExtra(PARAM_REMOTE_URI, remoteUri);
        intent.putExtra(PARAM_IS_VIDEO, isVideo);
        context.startActivity(intent);
    }



}

