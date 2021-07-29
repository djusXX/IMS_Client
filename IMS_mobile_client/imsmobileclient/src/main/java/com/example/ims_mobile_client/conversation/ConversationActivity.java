package com.example.ims_mobile_client.conversation;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ims_mobile_client.R;
import com.example.ims_mobile_client.calls.ActiveCallActivity;
import com.example.ims_mobile_client.calls.CallEventsReceiver;
import com.example.ims_mobile_client.calls.OutgoingCallActivity;
import com.example.ims_mobile_client.common.MessageType;

import net.gotev.sipservice.BroadcastEventReceiver;
import net.gotev.sipservice.SipServiceCommand;

import org.pjsip.pjsua2.pjsip_inv_state;

import java.util.ArrayList;

import static net.gotev.sipservice.SipServiceConstants.PARAM_ACCOUNT_ID;
import static net.gotev.sipservice.SipServiceConstants.PARAM_CONTACT_URI;
import static net.gotev.sipservice.SipServiceConstants.PARAM_DISPLAY_NAME;

public class ConversationActivity extends AppCompatActivity {
    protected RecyclerView recyclerView;
    protected ConversationAdapter conversationAdapter;
    protected RecyclerView.LayoutManager layoutManager;
    protected ArrayList<Message> messages;
    protected String accountID;
    protected String displayName;
    protected String contactUri;
    protected boolean isVideoCall;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);
        accountID = getIntent().getStringExtra(PARAM_ACCOUNT_ID);
        displayName = getIntent().getStringExtra(PARAM_DISPLAY_NAME);
        contactUri = getIntent().getStringExtra(PARAM_CONTACT_URI);
        initData();


        recyclerView = findViewById(R.id.conversation_recycler_viewer);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        conversationAdapter = new ConversationAdapter(ConversationActivity.this, messages);
        recyclerView.setAdapter(conversationAdapter);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.conversation_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (R.id.option_call_audio == id) {
            isVideoCall = false;
            try {
                SipServiceCommand.makeCall(ConversationActivity.this, accountID, contactUri);
            } catch (Exception exc) {
                Toast.makeText(ConversationActivity.this, "Error occurred while making Voice call:" + exc, Toast.LENGTH_LONG).show();
            }
            return true;
        }
        if (R.id.option_call_video == id) {
            isVideoCall = true;
            try {
                SipServiceCommand.makeCall(ConversationActivity.this, accountID, contactUri, true, false);
            } catch (Exception exc) {
                Toast.makeText(ConversationActivity.this, "Error occurred while making Video call:" + exc, Toast.LENGTH_LONG).show();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    CallEventsReceiver eventReceiver = new CallEventsReceiver() {
        @Override
        public void onOutgoingCall(String accountID, int callID, String number, boolean isVideo, boolean isVideoConference) {
            super.onOutgoingCall(accountID, callID, number, isVideo, isVideoConference);
            Intent intent = new Intent(ConversationActivity.this, OutgoingCallActivity.class);
            intent.putExtra(PARAM_ACCOUNT_ID, accountID);
            intent.putExtra(PARAM_IS_VIDEO, isVideo);
            intent.putExtra(PARAM_CONTACT_URI, contactUri);
            intent.putExtra(PARAM_DISPLAY_NAME, displayName);
            ConversationActivity.this.startActivity(intent);
            finish();
        }

        @Override
        public void onCallState(String accountID, int callID, int callStateCode, int callStatusCode,
                                long connectTimestamp, boolean isLocalHold, boolean isLocalMute,
                                boolean isLocalVideoMute) {
            super.onCallState(accountID, callID, callStateCode, callStatusCode, connectTimestamp,
                    isLocalHold, isLocalMute, isLocalVideoMute);

            if (pjsip_inv_state.PJSIP_INV_STATE_NULL < callStateCode && callStateCode < pjsip_inv_state.PJSIP_INV_STATE_CONFIRMED) {
                Intent intent = new Intent(ConversationActivity.this, OutgoingCallActivity.class);
                intent.putExtra(PARAM_ACCOUNT_ID, accountID);
                intent.putExtra(PARAM_CALL_ID, callID);
                intent.putExtra(PARAM_CONTACT_URI, contactUri);
                intent.putExtra(PARAM_DISPLAY_NAME, displayName);
                intent.putExtra(PARAM_IS_VIDEO, isVideoCall);
                ConversationActivity.this.startActivity(intent);
                finish();
            }
        }
    };


    private void initData() {
        messages = new ArrayList<> ();
        messages.add(new Message("message_text_1", MessageType.IN));
        messages.add(new Message("message_text_2", MessageType.OUT));
        messages.add(new Message("message_text_3", MessageType.IN));
        messages.add(new Message("message_text_4", MessageType.OUT));
        messages.add(new Message("message_text_5", MessageType.OUT));
        messages.add(new Message("message_text_6", MessageType.IN));
        messages.add(new Message("message_text_7", MessageType.IN));
    }
}
