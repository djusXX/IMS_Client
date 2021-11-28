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
import net.gotev.sipservice.SipContact;
import net.gotev.sipservice.SipServiceCommand;

import java.util.ArrayList;

import static net.gotev.sipservice.SipServiceConstants.PARAM_ACCOUNT_ID;
import static net.gotev.sipservice.SipServiceConstants.PARAM_DISPLAY_NAME;

public class ChatsActivity extends AppCompatActivity {
    protected RecyclerView recyclerView;
    protected ChatsAdapter chatsAdapter;
    protected RecyclerView.LayoutManager layoutManager;
    public ArrayList<SipContact> sipContacts = new ArrayList<>();
    protected String accountID;
    protected String displayName;
    protected CallEventsReceiver eventReceiver = new CallEventsReceiver() {
        @Override
        protected void onSipContactAdded(String contactUri, String displayName) {
            super.onSipContactAdded(contactUri, displayName);

            chatsAdapter.notifyDataSetChanged();
        }

        @Override
        protected void onSipContactState(String contactUri) {
            super.onSipContactState(contactUri);

        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        accountID = getIntent().getStringExtra(PARAM_ACCOUNT_ID);
        displayName = getIntent().getStringExtra(PARAM_DISPLAY_NAME);
        setTitle(displayName);

//        initData();

        eventReceiver.register(this);

        setContentView(R.layout.activity_chats);

        updateContactList();

        recyclerView = findViewById(R.id.chats_recycler_viewer);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        chatsAdapter = new ChatsAdapter(ChatsActivity.this, accountID, displayName, sipContacts);
        recyclerView.setAdapter(chatsAdapter);
    }

    private void updateContactList() {
        SipServiceCommand.getContacts(this, accountID);
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
            intent.putExtra(PARAM_ACCOUNT_ID, accountID);
            intent.putExtra(PARAM_DISPLAY_NAME, displayName);
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
        SipServiceCommand.addContact(this, accountID, "Bob", "sip:bob@open-ims.test", true);
//        sipContacts.start(contact_1);
//        sipContactDao.insert(new SipContactEntity(accountID, "Bob", "sip:bob@open-ims.test"));

//        SipServiceCommand.addContact(this, accountID, "Ela", "sip:ela@open-ims.test", true);
//        sipContacts.start(contact_1);
//        sipContactDao.insert(new SipContactEntity(accountID, "Ela", "sip:ela@open-ims.test"));

//        SipServiceCommand.addContact(this, accountID, "Gosia", "sip:gosia@open-ims.test", true);
//        sipContacts.start(contact_1);
//        sipContactDao.insert(new SipContactEntity(accountID, "Gosia", "sip:gosia@open-ims.test"));
    }


}
