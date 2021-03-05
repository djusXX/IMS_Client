package com.example.ims_mobile_client;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.ims_mobile_client.ui.main.SectionsPagerAdapter;

import org.pjsip.pjsua2.*;
import org.pjsip.pjsua2.Account;

class MyAccount extends Account {
    @Override
    public void onRegState(OnRegStateParam prm) {
        System.out.println("*** On registration state: " + prm.getCode() + prm.getReason());
    }
}

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);

        try {
            // Create endpoint
            Endpoint ep = new Endpoint();
            ep.libCreate();
            // Initialize endpoint
            EpConfig epConfig = new EpConfig();
            ep.libInit( epConfig );
            // Create SIP transport. Error handling sample is shown
            TransportConfig sipTpConfig = new TransportConfig();
            sipTpConfig.setPort(5060);
            ep.transportCreate(pjsip_transport_type_e.PJSIP_TRANSPORT_UDP, sipTpConfig);
            // Start the library
            ep.libStart();

            AccountConfig acfg = new AccountConfig();
            acfg.setIdUri("sip:test@pjsip.org");
            acfg.getRegConfig().setRegistrarUri("sip:pjsip.org");
            AuthCredInfo cred = new AuthCredInfo("digest", "*", "test", 0, "secret");
            acfg.getSipConfig().getAuthCreds().add( cred );
            // Create the account
            MyAccount acc = new MyAccount();
            acc.create(acfg);
            // Here we don't have anything else to do..
            Thread.sleep(10000);
            /* Explicitly delete the account.
             * This is to avoid GC to delete the endpoint first before deleting
             * the account.
             */
            acc.delete();

            // Explicitly destroy and delete endpoint
            ep.libDestroy();
            ep.delete();

        } catch (Exception e) {
            System.out.println(e);
            return;
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}