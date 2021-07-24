package com.example.ims_mobile_client.contacts;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ims_mobile_client.R;
import com.example.ims_mobile_client.calls.CallEventsReceiver;
import com.example.ims_mobile_client.calls.IncomingCallActivity;
import com.example.ims_mobile_client.login.LoginActivity;

import net.gotev.sipservice.BroadcastEventReceiver;
import net.gotev.sipservice.CallReconnectionState;
import net.gotev.sipservice.RtpStreamStats;
import net.gotev.sipservice.SipServiceCommand;

import java.time.Instant;
import java.util.ArrayList;

import static net.gotev.sipservice.SipServiceConstants.PARAM_ACCOUNT_ID;

public class ContactsActivity extends AppCompatActivity {
    protected RecyclerView recyclerView;
    protected ContactAdapter contactAdapter;
    protected RecyclerView.LayoutManager layoutManager;
    protected ArrayList<Contact> contacts = new ArrayList<>();
    protected String accountID;
    protected CallEventsReceiver eventReceiver = new CallEventsReceiver();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        accountID = getIntent().getStringExtra(PARAM_ACCOUNT_ID);
        initData();
        eventReceiver.register(this);

        setContentView(R.layout.activity_contacts);

        recyclerView = findViewById(R.id.contacts_recycler_viewer);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        contactAdapter = new ContactAdapter(ContactsActivity.this, contacts);
        recyclerView.setAdapter(contactAdapter);
//        requestPermissions();
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
    protected void onStop() {
        super.onStop();
        if (accountID != null)
            SipServiceCommand.removeAccount(this, accountID);
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
            startActivity(intent);
            return true;
        }
        if (R.id.contacts_menu_settings == id) {
            // TODO: implement
            return true;
        }
        if (R.id.contacts_menu_log_out == id) {
            // TODO: implement
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initData() {
        int num_of_contacts = 20;
        for(int i = 0; i < num_of_contacts; i++) {
            contacts.add(new Contact("contact_" + i, Instant.EPOCH.getEpochSecond() + (112345 * i)));
        }
    }

    private void getContactsFromDB() {
        // TODO get Contacts from application DB and add them to
        //  contactHistory.add(dbContact);
    }

    private void getContacts() {

    }

    private void requestPermissions() {
        String[] p = {
                Manifest.permission_group.CAMERA,
                Manifest.permission_group.MICROPHONE,
                Manifest.permission_group.STORAGE,
                Manifest.permission.RECORD_AUDIO
        };

        for (String perm : p) {
            if(checkSelfPermission(perm) != PackageManager.PERMISSION_GRANTED)
                requestPermissions(p, 789);
        }
    }



}
