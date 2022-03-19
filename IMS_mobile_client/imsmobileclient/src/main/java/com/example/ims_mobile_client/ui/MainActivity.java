package com.example.ims_mobile_client.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.ims_mobile_client.R;
import com.example.ims_mobile_client.ui.buddies.BuddyListFragment;
import com.example.ims_mobile_client.ui.calls.ActiveCallFragment;
import com.example.ims_mobile_client.ui.calls.PreCallFragment;
import com.example.ims_mobile_client.ui.conversations.ConversationFragment;
import com.example.ims_mobile_client.utils.AppBroadcastEventReceiver;

import net.gotev.sipservice.Logger;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSIONS_REQUEST_CODE = 9999;
    private static final String TAG = MainActivity.class.getName();

    protected AppBroadcastEventReceiver receiver = null;

    private static String usrSipUri;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        requestPermissions();

        if (savedInstanceState == null) {
            receiver = new AppBroadcastEventReceiver();
            addLoginFragment();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        receiver.unregister(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        receiver.register(this);
    }

    private void addLoginFragment() {
        LoginFragment loginFragment = new LoginFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_fragment_container, loginFragment, LoginFragment.TAG)
                .commit();
    }

    public void logInUser(int registrationStateCode) {
        LoginFragment loginFragment = (LoginFragment) getSupportFragmentManager().findFragmentByTag(LoginFragment.TAG);
        if (loginFragment != null) { loginFragment.logInCurrentUser(registrationStateCode); }
    }

    public void onUserLogged(String accountID) {
        usrSipUri = accountID;
        BuddyListFragment buddyListFragment = new BuddyListFragment(accountID);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment_container, buddyListFragment, BuddyListFragment.TAG)
                .commit();
    }

    public void onLoginError(int registrationStateCode) {
        Toast.makeText(this, "error: " + registrationStateCode, Toast.LENGTH_SHORT).show();
    }

    public void loadConversationFragment(String usrSipUri, String buddy_sip_uri) {
        ConversationFragment conversationFragment = new ConversationFragment(usrSipUri, buddy_sip_uri);
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("Conversation")
                .setReorderingAllowed(true)
                .replace(R.id.main_fragment_container,
                        conversationFragment, ConversationFragment.TAG).commit();
    }

    public void loadPreCallFragment(boolean isIncoming, String accountID, int callID, String displayName, String remoteUri, boolean isVideo) {
        if (!accountID.equals(usrSipUri)) {
            Logger.debug(TAG, "call for not logged user: " + remoteUri);
            return;
        }

        PreCallFragment preCallFragment = new PreCallFragment(isIncoming, accountID, callID, displayName, remoteUri, isVideo);
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("preCall")
                .setReorderingAllowed(true)
                .replace(R.id.main_fragment_container, preCallFragment, null).commit();
    }

    public void loadActiveCallFragment(String accountID, int callID) {

        ActiveCallFragment activeCallFragment = new ActiveCallFragment(accountID, callID, true);
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("activeCall")
                .setReorderingAllowed(true)
                .replace(R.id.main_fragment_container, activeCallFragment, null).commit();

    }

    /** TODO: Consider update below checking permissions!!!!!!!!!!!!!!!!! **/
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
            Toast.makeText(MainActivity.this, "ALL required permissions granted :)", Toast.LENGTH_SHORT).show();
        }
    }

}
