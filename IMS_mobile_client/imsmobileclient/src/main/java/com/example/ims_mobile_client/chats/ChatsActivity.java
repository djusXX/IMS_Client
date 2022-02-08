package com.example.ims_mobile_client.chats;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ims_mobile_client.R;
import com.example.ims_mobile_client.calls.CallEventsReceiver;
import com.example.ims_mobile_client.common.AppConstants;
import com.example.ims_mobile_client.data.AppPreferencesHelper;
import com.example.ims_mobile_client.login.LoginActivity;

import net.gotev.sipservice.SipBuddyData;
import net.gotev.sipservice.SipServiceCommand;

import java.util.ArrayList;

import static net.gotev.sipservice.SipServiceConstants.PARAM_ACCOUNT_ID;
import static net.gotev.sipservice.SipServiceConstants.PARAM_DISPLAY_NAME;

public class ChatsActivity extends AppCompatActivity {
    protected RecyclerView recyclerView;
    protected ChatsAdapter chatsAdapter;
    protected RecyclerView.LayoutManager layoutManager;
    public ArrayList<SipBuddyData> sipBuddies = new ArrayList<>();
    protected String accountID;
    protected String displayName;
    protected CallEventsReceiver eventReceiver = new CallEventsReceiver() {
//        @Override
//        protected void onBuddyState(String contactUri) {
//            super.onBuddyState(contactUri);
//
//        }
//
        @Override
        protected void onBuddyAdded(String accountID, SipBuddyData buddyData) {
            super.onBuddyAdded(accountID, buddyData);
//            sipBuddies.add(buddyData);
            chatsAdapter.addBuddy(buddyData);
            chatsAdapter.notifyDataSetChanged();
        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        accountID = AppPreferencesHelper.getInstance(ChatsActivity.this).getString(AppConstants.USER_SIP_URI);
        displayName = AppPreferencesHelper.getInstance(ChatsActivity.this).getString(AppConstants.USER_DISPLAY_NAME);
        setTitle(displayName);

        initData();

        refreshBuddyList();

        eventReceiver.register(this);

        setContentView(R.layout.activity_chats);


        recyclerView = findViewById(R.id.chats_recycler_viewer);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        chatsAdapter = new ChatsAdapter(ChatsActivity.this, accountID, displayName, sipBuddies);
        recyclerView.setAdapter(chatsAdapter);
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
        getMenuInflater().inflate(R.menu.chats_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
//        if (R.id.contacts_menu_search == id) {
//            // TODO: implement
//            return true;
//        }
        if (R.id.chats_menu_new_chat == id) {
            Intent intent = new Intent(ChatsActivity.this, NewChatActivity.class);
            startActivity(intent);
//            finish();
            return true;
        }
        if (R.id.chats_menu_settings == id) {
            // TODO: implement
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initData() {
        SipBuddyData buddyData = new SipBuddyData();
        buddyData.setDisplayName("BOB");
        buddyData.setSipUri("sip:bob@open-ims.test");
        sipBuddies.add(buddyData);

    }

    private void refreshBuddyList() {
        for (SipBuddyData buddyData : sipBuddies) {
            SipServiceCommand.addBuddy(this, accountID, buddyData);
        }
    }


}
