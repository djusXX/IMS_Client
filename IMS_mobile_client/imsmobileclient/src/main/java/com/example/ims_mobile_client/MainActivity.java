package com.example.ims_mobile_client;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.ims_mobile_client.contacts.Contact;
import com.example.ims_mobile_client.contacts.ContactsViewFragment;
import com.example.ims_mobile_client.data.model.LoggedInUser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    protected ArrayList<Contact> user_contacts;
    private LoggedInUser user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            ContactsViewFragment fragment = new ContactsViewFragment();
            transaction.replace(R.id.main_fragment, fragment);
            transaction.commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (R.id.app_bar_search == id) {
            // TODO: implement
            return true;
        }
        if (R.id.app_bar_add_contact == id) {
            // TODO: implement
            return true;
        }
        if (R.id.app_bar_settings == id) {
            // TODO: implement
            return true;
        }
        if (R.id.app_bar_log_out == id) {
            // TODO: implement
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
