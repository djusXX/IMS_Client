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
import com.example.ims_mobile_client.chats.ChatsActivity;
import com.example.ims_mobile_client.utils.AppConstants;
import com.example.ims_mobile_client.utils.AppPreferencesHelper;

import net.gotev.sipservice.BroadcastEventReceiver;
import net.gotev.sipservice.SipAccountData;
import net.gotev.sipservice.SipServiceCommand;

import org.pjsip.pjsua2.pjsip_status_code;


public class LoginActivity extends AppCompatActivity {
    private static final int PERMISSIONS_REQUEST_CODE = 9999;
    BroadcastEventReceiver eventReceiver = new LoginEventReceiver();
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


        String prevDisplayName = AppPreferencesHelper.getInstance(LoginActivity.this).getString(AppConstants.USER_DISPLAY_NAME);
        String prevName = AppPreferencesHelper.getInstance(LoginActivity.this).getString(AppConstants.USER_NAME);
        String prevRealm = AppPreferencesHelper.getInstance(LoginActivity.this).getString(AppConstants.USER_REALM);
        String prevPCSCF = AppPreferencesHelper.getInstance(LoginActivity.this).getString(AppConstants.USER_PCSCF);

        displayNameEditText.setText(prevDisplayName);
        usernameEditText.setText(prevName);
        realmEditText.setText(prevRealm);
        pcscfEditText.setText(prevPCSCF);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(displayNameEditText.getText().toString(),
                        usernameEditText.getText().toString(),
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
            if (accountID.isEmpty() && 400 == registrationStateCode) {
                SipServiceCommand.setAccount(LoginActivity.this, sipAccount);
            } else if (registrationStateCode == pjsip_status_code.PJSIP_SC_OK) {
                Intent intent = new Intent(LoginActivity.this, ChatsActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "error: " + registrationStateCode, Toast.LENGTH_SHORT).show();
            }
        }


    };

    private void login(String displayName, String username, String password, String realm, String pcscf) {
        sipAccount.setUsername(username);
        sipAccount.setPassword(password);
        sipAccount.setRealm(realm);
        String host = pcscf.substring(0,pcscf.indexOf(":"));
        String portStr = pcscf.substring(pcscf.indexOf(":") + 1);
        int port = Integer.parseInt(portStr);
        sipAccount.setHost(host);
        sipAccount.setPort(port);

        SipServiceCommand.getRegistrationStatus(this, sipAccount.getIdUri());

        AppPreferencesHelper.getInstance(LoginActivity.this).setString(AppConstants.USER_DISPLAY_NAME, displayName);
        AppPreferencesHelper.getInstance(LoginActivity.this).setString(AppConstants.USER_NAME, username);
        AppPreferencesHelper.getInstance(LoginActivity.this).setString(AppConstants.USER_REALM, realm);
        AppPreferencesHelper.getInstance(LoginActivity.this).setString(AppConstants.USER_PCSCF, pcscf);
        AppPreferencesHelper.getInstance(LoginActivity.this).setString(AppConstants.USER_SIP_URI, sipAccount.getIdUri());
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