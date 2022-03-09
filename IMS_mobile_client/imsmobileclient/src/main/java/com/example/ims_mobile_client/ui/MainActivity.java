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
import com.example.ims_mobile_client.utils.AppBroadcastEventReceiver;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSIONS_REQUEST_CODE = 9999;

    protected static AppBroadcastEventReceiver receiver = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        requestPermissions();

        if (savedInstanceState == null) {
            addLoginFragment();
            receiver = new AppBroadcastEventReceiver();
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
