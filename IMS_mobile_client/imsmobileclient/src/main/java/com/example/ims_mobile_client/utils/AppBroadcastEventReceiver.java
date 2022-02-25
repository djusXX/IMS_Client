package com.example.ims_mobile_client.utils;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.ims_mobile_client.chats.ChatsActivity;
import com.example.ims_mobile_client.ui.MainActivity;

import net.gotev.sipservice.BroadcastEventReceiver;
import net.gotev.sipservice.SipAccountData;
import net.gotev.sipservice.SipServiceCommand;

import org.pjsip.pjsua2.pjsip_status_code;

public class AppBroadcastEventReceiver extends BroadcastEventReceiver {

    private Context appContext;

    @Override
    public void register(Context context) {
        super.register(context);
        appContext = context;
    }

    @Override
    public void onRegistration(String accountID, int registrationStateCode) {
        super.onRegistration(accountID, registrationStateCode);
        if (accountID.isEmpty() && 400 == registrationStateCode) {
            ((MainActivity) appContext).setAccount();
        } else if (registrationStateCode == pjsip_status_code.PJSIP_SC_OK) {
            ((MainActivity) appContext).addConversationsFragment();
        } else {
            Toast.makeText(appContext, "error: " + registrationStateCode, Toast.LENGTH_SHORT).show();
        }
    }


}
