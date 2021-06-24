package com.example.ims_mobile_client;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.pjsip.pjsua2.*;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        findViewById(R.id.buttonClear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout loginLayout = (LinearLayout) findViewById(R.id.loginLayout);
                for(int i = 0; i < loginLayout.getChildCount(); i++) {
                    if(loginLayout.getChildAt(i) instanceof LinearLayout) {
                        LinearLayout childLayout = (LinearLayout) loginLayout.getChildAt(i);
                        if (childLayout.getChildCount() > 1) {
                            EditText et = (EditText) childLayout.getChildAt(1);
                            et.setText("");
                        }

                    }
                }
            }
        });

        findViewById(R.id.buttonLogIn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                LoginCredentials logCred = new LoginCredentials(findViewById(R.id.loginLayout));
                LoginCredentials logCred = new LoginCredentials();

                try {
                    // Create endpoint
                    Endpoint ep = new Endpoint();
                    ep.libCreate();
                    // Initialize endpoint
                    EpConfig epConfig = new EpConfig();
                    ep.libInit(epConfig);
                    // Create SIP transport. Error handling sample is shown
                    TransportConfig sipTpConfig = new TransportConfig();
                    sipTpConfig.setPort((long) logCred.proxyCSCFPort);
//                    sipTpConfig.setPublicAddress(logCred.publicIdentity);
                    ep.transportCreate(pjsip_transport_type_e.PJSIP_TRANSPORT_UDP, sipTpConfig);
                    // Start the library
                    ep.libStart();

                    AccountConfig acfg = new AccountConfig();
                    acfg.setIdUri(logCred.publicIdentity);
                    acfg.getRegConfig().setRegistrarUri(logCred.realm);
                    AuthCredInfo cred = new AuthCredInfo("digest", "*", "bob", 0, "secret");
                    acfg.getSipConfig().getAuthCreds().add( cred );
                    // Create the account
                    Account acc = new Account();
                    acc.create(acfg);
                    // Here we don't have anything else to do..

                } catch (Exception e) {
                    new AlertDialog.Builder(LoginActivity.this)
                            .setTitle("Cannot log in to server")
                            .setMessage("error: " + e.toString())
                            .setNeutralButton("CLOSE", (dialog, which) -> dialog.dismiss())
                            .show();
                }
            }
        });

    }




}