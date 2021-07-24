package com.example.ims_mobile_client.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ims_mobile_client.R;
import com.example.ims_mobile_client.contacts.ContactsActivity;

import net.gotev.sipservice.BroadcastEventReceiver;
import net.gotev.sipservice.Logger;
import net.gotev.sipservice.SipAccountData;
import net.gotev.sipservice.SipServiceCommand;

import org.pjsip.pjsua2.Account;
import org.pjsip.pjsua2.AccountConfig;
import org.pjsip.pjsua2.AuthCredInfo;
import org.pjsip.pjsua2.Endpoint;
import org.pjsip.pjsua2.EpConfig;
import org.pjsip.pjsua2.OnRegStateParam;
import org.pjsip.pjsua2.TransportConfig;
import org.pjsip.pjsua2.pjsip_status_code;
import org.pjsip.pjsua2.pjsip_transport_type_e;


public class LoginActivity extends AppCompatActivity {
    String accountID;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final EditText realmEditText = findViewById(R.id.realm);
        final EditText pcscfEditText = findViewById(R.id.pcscf);
        final Button loginButton = findViewById(R.id.login_button);
        eventReceiver.register(this);


        // ONLY FOR DEBUGGING
        usernameEditText.setText("alice");
        passwordEditText.setText("alice");
        realmEditText.setText("open-ims.test");
        pcscfEditText.setText("10.0.0.9:4060");

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString(),
                        realmEditText.getText().toString(),
                        pcscfEditText.getText().toString());
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
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

    public BroadcastEventReceiver eventReceiver = new BroadcastEventReceiver(){
        @Override
        public void onRegistration(String accountID, int registrationStateCode) {
            super.onRegistration(accountID, registrationStateCode);
            if(registrationStateCode == pjsip_status_code.PJSIP_SC_OK) {
                Intent intent = new Intent(LoginActivity.this, ContactsActivity.class);
                intent.putExtra(PARAM_ACCOUNT_ID, accountID);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "error: " + registrationStateCode, Toast.LENGTH_SHORT).show();
            }
        }
    };

    private void login(String username, String password, String realm, String pcscf) {
        SipAccountData sipAccount = new SipAccountData();
        sipAccount.setUsername(username);
        sipAccount.setPassword(password);
        sipAccount.setRealm(realm);
        String host = pcscf.substring(0,pcscf.indexOf(":"));
        String portStr = pcscf.substring(pcscf.indexOf(":") + 1);
        int port = Integer.parseInt(portStr);
        sipAccount.setHost(host);
        sipAccount.setPort(port);

        accountID = SipServiceCommand.setAccount(this, sipAccount);
    }

}