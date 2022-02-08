package com.example.ims_mobile_client.calls;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.ims_mobile_client.R;
import com.example.ims_mobile_client.common.AppConstants;
import com.example.ims_mobile_client.data.AppPreferencesHelper;

import net.gotev.sipservice.SipServiceCommand;

import org.pjsip.pjsua2.pjsip_inv_state;

import static net.gotev.sipservice.SipServiceConstants.PARAM_ACCOUNT_ID;
import static net.gotev.sipservice.SipServiceConstants.PARAM_CALL_ID;
import static net.gotev.sipservice.SipServiceConstants.PARAM_CONTACT_URI;
import static net.gotev.sipservice.SipServiceConstants.PARAM_DISPLAY_NAME;
import static net.gotev.sipservice.SipServiceConstants.PARAM_IS_VIDEO;
import static net.gotev.sipservice.SipServiceConstants.PARAM_REMOTE_URI;

public class OutgoingCallActivity extends AppCompatActivity {
    protected String accountID;
    protected int callID;
    protected String displayName;
    protected String remoteUri;
    protected boolean isVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outgoing_call);
        getExtraParams(getIntent());

        String call_title = "Outgoing " + (isVideo ? "video" : "voice") + " call to";
        ((TextView) findViewById(R.id.outgoing_call_title)).setText(call_title);
        ((TextView) findViewById(R.id.outgoing_contact_name)).setText(remoteUri + "\n(" + remoteUri + ")");

        ((Button) findViewById(R.id.cancel_call_button)).setOnClickListener(v -> {
            if(callID >= 0) {
                SipServiceCommand.hangUpActiveCalls(this, accountID);
            }
            finish();
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        eventReceiver.unregister(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        eventReceiver.register(this);
    }

    CallEventsReceiver eventReceiver = new CallEventsReceiver() {
        @Override
        public void onCallState(String accountID, int callID, int callStateCode, int callStatusCode,
                                long connectTimestamp, boolean isLocalHold, boolean isLocalMute,
                                boolean isLocalVideoMute) {
            super.onCallState(accountID, callID, callStateCode, callStatusCode, connectTimestamp,
                    isLocalHold, isLocalMute, isLocalVideoMute);

            if (pjsip_inv_state.PJSIP_INV_STATE_CONFIRMED == callStateCode) {
                Intent intent = new Intent(OutgoingCallActivity.this, ActiveCallActivity.class);
                AppPreferencesHelper.getInstance(OutgoingCallActivity.this).setString(AppConstants.USER_SIP_URI, accountID);
                AppPreferencesHelper.getInstance(OutgoingCallActivity.this).setInt(AppConstants.CALL_ID, callID);
                AppPreferencesHelper.getInstance(OutgoingCallActivity.this).setString(AppConstants.CALL_REMOTE_URI, remoteUri);
                AppPreferencesHelper.getInstance(OutgoingCallActivity.this).setBoolean(AppConstants.CALL_IS_VIDEO, isVideo);
                OutgoingCallActivity.this.startActivity(intent);
                finish();
            } else if (pjsip_inv_state.PJSIP_INV_STATE_DISCONNECTED == callStateCode
                    || pjsip_inv_state.PJSIP_INV_STATE_NULL == callStateCode) {
                finish();
            }
        }
    };

    private void getExtraParams(Intent i) {
        accountID = i.getStringExtra(PARAM_ACCOUNT_ID);
        displayName = i.getStringExtra(PARAM_DISPLAY_NAME);
        remoteUri = i.getStringExtra(PARAM_CONTACT_URI);
        isVideo = i.getBooleanExtra(PARAM_IS_VIDEO, false);
    }
}