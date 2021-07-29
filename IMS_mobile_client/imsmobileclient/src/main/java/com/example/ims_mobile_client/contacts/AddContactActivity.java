package com.example.ims_mobile_client.contacts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ims_mobile_client.R;

import net.gotev.sipservice.SipContact;
import net.gotev.sipservice.SipServiceCommand;

import java.time.Instant;
import java.util.ArrayList;

import static net.gotev.sipservice.SipServiceConstants.*;

public class AddContactActivity extends AppCompatActivity {
    String accountID;
    String userDisplayName;
    protected ArrayList<String> sipContacts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_contact);
        EditText displayName = findViewById(R.id.add_contact_display_name);
        EditText sipUri = findViewById(R.id.add_contact_sip_uri);
        Button add = findViewById(R.id.add_contact_add);
        Button cancel = findViewById(R.id.add_contact_cancel);
        accountID = getIntent().getStringExtra(PARAM_ACCOUNT_ID);
        userDisplayName = getIntent().getStringExtra(PARAM_DISPLAY_NAME);
        sipContacts = getIntent().getStringArrayListExtra(PARAM_SIP_CONTACTS_ARRAY);
        setTitle(userDisplayName + " - Add Contact");


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String contactName = displayName.getText().toString();
                String contactSipUri = sipUri.getText().toString();


                if(!contactName.isEmpty() || !contactSipUri.isEmpty()) {
                    SipServiceCommand.addContact(AddContactActivity.this, accountID, contactName, contactSipUri, false);
                    sipContacts.add(contactSipUri);
                }
                Intent intent = new Intent(AddContactActivity.this, ContactsActivity.class);
                intent.putExtra(PARAM_ACCOUNT_ID, accountID);
                intent.putExtra(PARAM_DISPLAY_NAME, userDisplayName);
                intent.putStringArrayListExtra(PARAM_SIP_CONTACTS_ARRAY, sipContacts);
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
