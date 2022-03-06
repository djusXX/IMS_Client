package com.example.ims_mobile_client.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.ims_mobile_client.R;
import com.example.ims_mobile_client.data.entities.BuddyEntity;
import com.example.ims_mobile_client.utils.AppBroadcastEventReceiver;
import com.example.ims_mobile_client.view_models.BuddyViewModel;

import net.gotev.sipservice.SipAccountData;
import net.gotev.sipservice.SipServiceCommand;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSIONS_REQUEST_CODE = 9999;

    protected AppBroadcastEventReceiver receiver = null;
    protected SipAccountData currentUser;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        requestPermissions();
        receiver = new AppBroadcastEventReceiver();
        receiver.register(this);

        if (savedInstanceState == null) {
            addLoginFragment();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (receiver != null)
            receiver.unregister(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        receiver.register(this);
    }

    private void addLoginFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_fragment_container, new LoginFragment(), LoginFragment.TAG)
                .commit();
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
            Toast.makeText(MainActivity.this, "ALL required permissions granted :)", Toast.LENGTH_SHORT).show();
        }
    }

    public void setAccount() {
        SipServiceCommand.setAccount(MainActivity.this, currentUser);
    }

    public void setCurrentUser(SipAccountData accData) {
        currentUser = accData;
    }

//    public void addBuddyListFragment() {
//        BuddyListFragment buddyListFragment = new BuddyListFragment();
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.main_fragment_container, buddyListFragment, BuddyListFragment.TAG)
//                .commit();
//    }

    public void addNewBuddyFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("addNewBuddy")
                .replace(R.id.main_fragment_container,
                        new NewBuddyFragment(currentUser.getIdUri()), null).commit();
    }
}
