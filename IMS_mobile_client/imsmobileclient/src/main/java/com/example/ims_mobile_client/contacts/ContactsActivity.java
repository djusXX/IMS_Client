package com.example.ims_mobile_client.contacts;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ims_mobile_client.R;

import java.util.ArrayList;

public class ContactsActivity extends AppCompatActivity {
    protected RecyclerView recyclerView;
    protected ContactAdapter contactAdapter;
    protected RecyclerView.LayoutManager layoutManager;
    protected ArrayList<Contact> contacts;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();

        setContentView(R.layout.activity_contacts);

        recyclerView = findViewById(R.id.contacts_recycler_viewer);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        contactAdapter = new ContactAdapter(ContactsActivity.this, contacts);
        recyclerView.setAdapter(contactAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.contacts_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (R.id.contacts_menu_search == id) {
            // TODO: implement
            return true;
        }
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
        contacts = new ArrayList<> ();
        int num_of_contacts = 20;
        for(int i = 0; i < num_of_contacts; i++) {
            contacts.add(new Contact("contact_" + i ));
        }
    }
}
