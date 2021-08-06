package com.example.ims_mobile_client.login;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ims_mobile_client.R;
import com.example.ims_mobile_client.contacts.ContactsActivity;

import net.gotev.sipservice.BroadcastEventReceiver;
import net.gotev.sipservice.SipAccountData;
import net.gotev.sipservice.SipServiceCommand;

import org.pjsip.pjsua2.Account;
import org.pjsip.pjsua2.pjsip_status_code;


public class LoginActivity extends AppCompatActivity {
    private static final int PERMISSIONS_REQUEST_CODE = 9999;
    BroadcastEventReceiver eventReceiver = new LoginEventReceiver();
    String displayName;
    SipAccountData sipAccount = new SipAccountData();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        requestPermissions();

        final EditText displayNameEditText = findViewById(R.id.display_name);
        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final EditText realmEditText = findViewById(R.id.realm);
        final EditText pcscfEditText = findViewById(R.id.pcscf);
        final Button loginButton = findViewById(R.id.login_button);
        eventReceiver.register(this);


        // ONLY FOR DEBUGGING
        displayNameEditText.setText("ALICE");
        usernameEditText.setText("alice");
        passwordEditText.setText("alice");
        realmEditText.setText("open-ims.test");
        pcscfEditText.setText("10.0.0.9:4060");

        displayName = displayNameEditText.getText().toString();
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
    protected void onPause() {
        super.onPause();
        if (eventReceiver != null)
            eventReceiver.unregister(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        eventReceiver.register(this);
    }

    private class LoginEventReceiver extends BroadcastEventReceiver {
        @Override
        public void onRegistration(String accountID, int registrationStateCode) {
            super.onRegistration(accountID, registrationStateCode);
            if ("" == accountID && 400 == registrationStateCode) {
                SipServiceCommand.setAccount(LoginActivity.this, sipAccount);
            } else if (registrationStateCode == pjsip_status_code.PJSIP_SC_OK) {
                Intent intent = new Intent(LoginActivity.this, ContactsActivity.class);
                intent.putExtra(PARAM_ACCOUNT_ID, accountID);
                intent.putExtra(PARAM_DISPLAY_NAME, displayName);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "error: " + registrationStateCode, Toast.LENGTH_SHORT).show();
            }
        }


    };

    private void login(String username, String password, String realm, String pcscf) {
        sipAccount.setUsername(username);
        sipAccount.setPassword(password);
        sipAccount.setRealm(realm);
        String host = pcscf.substring(0,pcscf.indexOf(":"));
        String portStr = pcscf.substring(pcscf.indexOf(":") + 1);
        int port = Integer.parseInt(portStr);
        sipAccount.setHost(host);
        sipAccount.setPort(port);

//        SipServiceCommand.setAccount(this, sipAccount);
        SipServiceCommand.getRegistrationStatus(this, sipAccount.getIdUri());
    }

    private void requestPermissions() {
        String[] p = {
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        if (!checkPermissionsGranted(p)) {
            ActivityCompat.requestPermissions(this, p, PERMISSIONS_REQUEST_CODE);
        }
    }

    protected boolean checkPermissionsGranted(String[] perm) {
        for (String p : perm) {
            if(ContextCompat.checkSelfPermission(this, p) != PackageManager.PERMISSION_GRANTED)
                return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (PERMISSIONS_REQUEST_CODE == requestCode) {
            for (int res : grantResults) {
                if(res != PackageManager.PERMISSION_GRANTED)
                    return;
            }
            Toast.makeText(LoginActivity.this, "ALL required permissions granted :)", Toast.LENGTH_SHORT).show();
        }
    }
}