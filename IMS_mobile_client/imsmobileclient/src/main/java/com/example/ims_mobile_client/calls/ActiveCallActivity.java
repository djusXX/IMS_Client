package com.example.ims_mobile_client.calls;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import com.example.ims_mobile_client.R;

import net.gotev.sipservice.SipServiceCommand;

import org.pjsip.pjsua2.pjsip_inv_state;

import static net.gotev.sipservice.SipServiceConstants.PARAM_ACCOUNT_ID;
import static net.gotev.sipservice.SipServiceConstants.PARAM_CALL_ID;
import static net.gotev.sipservice.SipServiceConstants.PARAM_DISPLAY_NAME;
import static net.gotev.sipservice.SipServiceConstants.PARAM_IS_VIDEO;
import static net.gotev.sipservice.SipServiceConstants.PARAM_REMOTE_URI;

public class ActiveCallActivity extends AppCompatActivity {
    protected String accountID;
    protected int callID;
    protected String displayName;
    protected String remoteUri;
    protected boolean isVideo;
    protected SurfaceView localVideo;
    protected SurfaceView remoteVideo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_call);
        getExtraParams(getIntent());


        ((Button) findViewById(R.id.mute_button)).setOnClickListener(v -> {
            if(callID >= 0) {
                SipServiceCommand.toggleCallMute(ActiveCallActivity.this, accountID, callID);
            }
        });

        ((Button) findViewById(R.id.end_button)).setOnClickListener(v -> {
            if(callID >= 0) {
                SipServiceCommand.hangUpCall(ActiveCallActivity.this, accountID, callID);
            }
            finish();
        });

        if (isVideo) {
            ((Button) findViewById(R.id.switch_camera_button)).setOnClickListener(v -> {
                if(callID >= 0) {
                    SipServiceCommand.switchVideoCaptureDevice(ActiveCallActivity.this, accountID, callID);
                }
            });

            localVideo = findViewById(R.id.local_user_view);
            remoteVideo = findViewById(R.id.remote_user_view);

            localVideo.getHolder().addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(SurfaceHolder surfaceHolder) {
                    SipServiceCommand.startVideoPreview(ActiveCallActivity.this, accountID, callID, localVideo.getHolder().getSurface());
                }

                @Override
                public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
                }

                @Override
                public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                    SipServiceCommand.stopVideoPreview(ActiveCallActivity.this, accountID, callID);
                }
            });

            remoteVideo.getHolder().addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(SurfaceHolder surfaceHolder) {
                }

                @Override
                public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
                    SipServiceCommand.setupIncomingVideoFeed(ActiveCallActivity.this, accountID, callID, surfaceHolder.getSurface());
                }

                @Override
                public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                    SipServiceCommand.setupIncomingVideoFeed(ActiveCallActivity.this, accountID, callID, null);
                }
            });

        } else {
            findViewById(R.id.switch_camera_button).setVisibility(View.GONE);
            findViewById(R.id.remote_user_view).setVisibility(View.GONE);
            findViewById(R.id.local_user_view).setVisibility(View.GONE);
        }
    }



    private void getExtraParams(Intent i) {
        accountID = i.getStringExtra(PARAM_ACCOUNT_ID);
        callID = i.getIntExtra(PARAM_CALL_ID, -1);
        displayName = i.getStringExtra(PARAM_DISPLAY_NAME);
        remoteUri = i.getStringExtra(PARAM_REMOTE_URI);
        isVideo = i.getBooleanExtra(PARAM_IS_VIDEO, false);
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

            if (pjsip_inv_state.PJSIP_INV_STATE_DISCONNECTED == callStateCode
                    || pjsip_inv_state.PJSIP_INV_STATE_NULL == callStateCode) {
                finish();
            }
        }
    };
}