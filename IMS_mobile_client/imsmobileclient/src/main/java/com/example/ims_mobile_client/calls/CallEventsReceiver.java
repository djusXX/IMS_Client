package com.example.ims_mobile_client.calls;

import android.content.Context;
import android.content.Intent;

import com.example.ims_mobile_client.common.AppConstants;
import com.example.ims_mobile_client.data.AppPreferencesHelper;

import net.gotev.sipservice.BroadcastEventReceiver;
import net.gotev.sipservice.Logger;

import java.util.zip.Adler32;

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
        AppPreferencesHelper.getInstance(context).setString(AppConstants.USER_SIP_URI, accountID);
        AppPreferencesHelper.getInstance(context).setInt(AppConstants.CALL_ID, callID);
        AppPreferencesHelper.getInstance(context).setString(AppConstants.USER_DISPLAY_NAME, displayName);
        AppPreferencesHelper.getInstance(context).setString(AppConstants.CALL_REMOTE_URI, remoteUri);
        AppPreferencesHelper.getInstance(context).setBoolean(AppConstants.CALL_IS_VIDEO, isVideo);
        context.startActivity(intent);
    }



}

