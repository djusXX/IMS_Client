package com.example.ims_mobile_client.contacts;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ims_mobile_client.R;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public class AddContactActivity extends AppCompatActivity {

    ArrayList<Contact> contacts;    // TODO: change to db connection or something similar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_contact);
        EditText displayName = findViewById(R.id.add_contact_display_name);
        EditText sipUri = findViewById(R.id.add_contact_sip_uri);
        Button add = findViewById(R.id.add_contact_add);
        Button cancel = findViewById(R.id.add_contact_cancel);





        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String contactName = displayName.getText().toString();
                String contactSipUri = sipUri.getText().toString();


                if(!contactName.isEmpty() || !contactSipUri.isEmpty()) {
                    contacts.add(new Contact(contactName, Instant.EPOCH.getEpochSecond()));
                }
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
