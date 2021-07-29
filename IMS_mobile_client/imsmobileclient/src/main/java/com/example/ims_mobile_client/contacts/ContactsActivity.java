package com.example.ims_mobile_client.contacts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ims_mobile_client.R;
import com.example.ims_mobile_client.calls.CallEventsReceiver;

import net.gotev.sipservice.Logger;
import net.gotev.sipservice.SipAccountData;
import net.gotev.sipservice.SipContact;
import net.gotev.sipservice.SipService;
import net.gotev.sipservice.SipServiceCommand;

import java.time.Instant;
import java.util.ArrayList;

import static net.gotev.sipservice.SipServiceConstants.PARAM_ACCOUNT_ID;
import static net.gotev.sipservice.SipServiceConstants.PARAM_DISPLAY_NAME;
import static net.gotev.sipservice.SipServiceConstants.PARAM_SIP_CONTACTS_ARRAY;

public class ContactsActivity extends AppCompatActivity {
    protected RecyclerView recyclerView;
    protected ContactAdapter contactAdapter;
    protected RecyclerView.LayoutManager layoutManager;
    public ArrayList<String> sipContacts = new ArrayList<>();
    protected String accountID;
    protected String displayName;
    protected CallEventsReceiver eventReceiver = new CallEventsReceiver() {
        // TODO add emitter and receiver for onBuddyState
    };
    static boolean firstStart = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        accountID = getIntent().getStringExtra(PARAM_ACCOUNT_ID);
        displayName = getIntent().getStringExtra(PARAM_DISPLAY_NAME);
        setTitle(displayName);

        if (firstStart) {
            initData();
            firstStart = false;
        } else {
            sipContacts = getIntent().getStringArrayListExtra(PARAM_SIP_CONTACTS_ARRAY);
        }
        eventReceiver.register(this);

        setContentView(R.layout.activity_contacts);

        recyclerView = findViewById(R.id.contacts_recycler_viewer);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        contactAdapter = new ContactAdapter(ContactsActivity.this, accountID, displayName, sipContacts);
        recyclerView.setAdapter(contactAdapter);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        if (eventReceiver.isRegistered)
            eventReceiver.unregister(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        eventReceiver.register(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.contacts_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
//        if (R.id.contacts_menu_search == id) {
//            // TODO: implement
//            return true;
//        }
        if (R.id.contacts_menu_add_contact == id) {
            Intent intent = new Intent(ContactsActivity.this, AddContactActivity.class);
            intent.putExtra(PARAM_ACCOUNT_ID, accountID);
            intent.putExtra(PARAM_DISPLAY_NAME, displayName);
            intent.putStringArrayListExtra(PARAM_SIP_CONTACTS_ARRAY, sipContacts);
            startActivity(intent);
            finish();
            return true;
        }
        if (R.id.contacts_menu_settings == id) {
            // TODO: implement
            return true;
        }
//        if (R.id.contacts_menu_log_out == id) {
//            // TODO: implement
//            return true;
//        }
        return super.onOptionsItemSelected(item);
    }

    private void initData() {
        String contact_1 = SipServiceCommand.addContact(this, accountID, "Bob", "sip:bob@open-ims.test", false);
        sipContacts.add(contact_1);

        contact_1 = SipServiceCommand.addContact(this, accountID, "Ela", "sip:ela@open-ims.test", false);
        sipContacts.add(contact_1);

        contact_1 = SipServiceCommand.addContact(this, accountID, "Gosia", "sip:gosia@open-ims.test", false);
        sipContacts.add(contact_1);
    }

    private void getContactsFromDB() {
        // TODO get Contacts from application DB and add them to
        //  contactHistory.add(dbContact);
    }

    private void getContacts() {

    }

}
