package com.example.ims_mobile_client.calls;

import androidx.appcompat.app.AppCompatActivity;

public class IncomingCallActivity extends AppCompatActivity {
//    protected String accountID;
//    protected int callID;
//    protected String displayName;
//    protected String remoteUri;
//    protected boolean isVideo;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_incoming_call);
//        getExtraParams(getIntent());
//
//
//        String call_title = "Incoming " + (isVideo ? "video" : "voice") + " call from";
//        ((TextView) findViewById(R.id.incoming_call_title)).setText(call_title);
//
//        ((TextView) findViewById(R.id.caller_name)).setText(displayName + "\n(" + remoteUri + ")");
//
//        ((Button) findViewById(R.id.accept_call_button)).setOnClickListener(v -> {
//            if(callID >= 0) {
//                SipServiceCommand.acceptIncomingCall(this, accountID, callID, isVideo);
//                Intent intent = new Intent(IncomingCallActivity.this, ActiveCallActivity.class);
//                SavedData.getInstance(this).setString(AppConstants.USER_SIP_URI, accountID);
//                SavedData.getInstance(this).setInt(AppConstants.CALL_ID, callID);
//                SavedData.getInstance(this).setBoolean(AppConstants.CALL_IS_VIDEO, isVideo);
//                IncomingCallActivity.this.startActivity(intent);
//            }
//            finish();
//        });
//
//        ((Button) findViewById(R.id.reject_call_button)).setOnClickListener(v -> {
//            if(callID >= 0) {
//                SipServiceCommand.declineIncomingCall(this, accountID, callID);
//            }
//            finish();
//        });
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        eventReceiver.unregister(this);
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        eventReceiver.register(this);
//    }
//
//    CallEventsReceiver eventReceiver = new CallEventsReceiver() {
//        @Override
//        public void onCallState(String accountID, int callID, int callStateCode, int callStatusCode,
//                                long connectTimestamp, boolean isLocalHold, boolean isLocalMute,
//                                boolean isLocalVideoMute) {
//            super.onCallState(accountID, callID, callStateCode, callStatusCode, connectTimestamp,
//                              isLocalHold, isLocalMute, isLocalVideoMute);
//
//            if (pjsip_inv_state.PJSIP_INV_STATE_DISCONNECTED == callStateCode
//                    || pjsip_inv_state.PJSIP_INV_STATE_NULL == callStateCode) {
//                finish();
//            }
//        }
//    };
//
//    private void getExtraParams(Intent i) {
//        accountID = i.getStringExtra(PARAM_ACCOUNT_ID);
//        callID = i.getIntExtra(PARAM_CALL_ID, -1);
//        displayName = i.getStringExtra(PARAM_DISPLAY_NAME);
//        remoteUri = i.getStringExtra(PARAM_REMOTE_URI);
//        isVideo = i.getBooleanExtra(PARAM_IS_VIDEO, false);
//    }

}