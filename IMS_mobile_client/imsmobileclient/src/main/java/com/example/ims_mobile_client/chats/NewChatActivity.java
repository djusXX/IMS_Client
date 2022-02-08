package com.example.ims_mobile_client.chats;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ims_mobile_client.R;
import com.example.ims_mobile_client.common.AppConstants;
import com.example.ims_mobile_client.conversation.ConversationActivity;
import com.example.ims_mobile_client.data.AppPreferencesHelper;

import net.gotev.sipservice.SipBuddyData;
import net.gotev.sipservice.SipServiceCommand;

import static net.gotev.sipservice.SipServiceConstants.*;

public class NewChatActivity extends AppCompatActivity {
    String accountID;
    String userDisplayName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_new_chat);
        EditText displayName = findViewById(R.id.new_chat_display_name);
        EditText sipUri = findViewById(R.id.new_chat_sip_uri);
        Button add = findViewById(R.id.new_chat_add);
        Button cancel = findViewById(R.id.new_chat_cancel);
        accountID = getIntent().getStringExtra(PARAM_ACCOUNT_ID);
        userDisplayName = getIntent().getStringExtra(PARAM_DISPLAY_NAME);
        setTitle(userDisplayName + " - new chat ");


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String contactName = displayName.getText().toString();
                String contactSipUri = sipUri.getText().toString();


                if(!contactName.isEmpty() || !contactSipUri.isEmpty()) {
                    SipBuddyData buddyData = new SipBuddyData();
                    buddyData.setDisplayName(contactName);
                    buddyData.setSipUri(contactSipUri);
                    SipServiceCommand.addBuddy(NewChatActivity.this, accountID, buddyData);
                }
                Intent intent = new Intent(NewChatActivity.this, ConversationActivity.class);
                AppPreferencesHelper.getInstance(NewChatActivity.this).setString(AppConstants.USER_SIP_URI, accountID);
                AppPreferencesHelper.getInstance(NewChatActivity.this).setString(AppConstants.USER_DISPLAY_NAME, userDisplayName);
                AppPreferencesHelper.getInstance(NewChatActivity.this).setString(AppConstants.CONVERSATION_BUDDY_URI, contactSipUri);
                startActivity(intent);
                finish();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
