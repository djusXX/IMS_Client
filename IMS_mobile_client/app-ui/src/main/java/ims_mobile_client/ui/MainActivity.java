package ims_mobile_client.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.splashscreen.SplashScreen;
import androidx.lifecycle.ViewModelProvider;

import dagger.hilt.android.AndroidEntryPoint;
import ims_mobile_client.presentation.viewModels.CallsViewModel;
import ims_mobile_client.presentation.viewModels.MessagesViewModel;
import ims_mobile_client.presentation.viewModels.UserViewModel;
import ims_mobile_client.ui.conversations.BuddyListFragment;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private static final int PERMISSIONS_REQUEST_CODE = 9999;
    private static final String TAG = MainActivity.class.getName();

    private UserViewModel userViewModel;
    private CallsViewModel userCalls;
    private MessagesViewModel userMessages;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        requestPermissions();
        userViewModel = new ViewModelProvider(this)
                .get(UserViewModel.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
        userViewModel.getUserRegistration().observe(this, userRegistration -> {
            if(!"LOGGED".equals(userRegistration.getRegStatusText())) {
                LoginFragment loginFragment = new LoginFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.main_fragment_container, loginFragment, LoginFragment.TAG)
                        .commit();
                return;
            }

            onUserLogged(userViewModel.getUserCredentials().getSipUri());
        });


    }

//    public void logInUser(int registrationStateCode) {
//        LoginFragment loginFragment = (LoginFragment) getSupportFragmentManager().findFragmentByTag(LoginFragment.TAG);
//        if (loginFragment != null) { loginFragment.logInCurrentUser(registrationStateCode); }
//    }
//
    public void onUserLogged(String accountID) {
        BuddyListFragment buddyListFragment = new BuddyListFragment(accountID, R.id.main_fragment_container);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment_container, buddyListFragment, BuddyListFragment.TAG)
                .commit();
    }
//
//    public void updateBuddyState(String ownerSipUri, String contactUri, String presStatus, String presText) {
//        BuddyListFragment buddyListFragment = (BuddyListFragment) getSupportFragmentManager().findFragmentByTag(BuddyListFragment.TAG);
//
//        if (buddyListFragment != null) {
//            buddyListFragment.updateBuddyState(ownerSipUri, contactUri, presStatus, presText);
//        }
//    }
//
//    public void onLoginError(int registrationStateCode) {
//        Toast.makeText(this, "error: " + registrationStateCode, Toast.LENGTH_SHORT).show();
//    }
//
//    public void loadConversationFragment(String usrSipUri, String buddy_sip_uri) {
//        ConversationFragment conversationFragment = new ConversationFragment(usrSipUri, buddy_sip_uri);
//        getSupportFragmentManager()
//                .beginTransaction()
//                .addToBackStack("Conversation")
//                .setReorderingAllowed(true)
//                .replace(R.id.main_fragment_container,
//                        conversationFragment, ConversationFragment.TAG).commit();
//    }
//
//    public void loadPreCallFragment(boolean isIncoming, String accountID, int callID, String displayName, String remoteUri, boolean isVideo) {
//        if (!accountID.equals(usrSipUri)) {
//            Logger.debug(TAG, "call for not logged user: " + remoteUri);
//            return;
//        }
//
//        PreCallFragment preCallFragment = new PreCallFragment(isIncoming, accountID, callID, displayName, remoteUri, isVideo);
//        getSupportFragmentManager()
//                .beginTransaction()
//                .addToBackStack("preCall")
//                .setReorderingAllowed(true)
//                .replace(R.id.main_fragment_container, preCallFragment, null).commit();
//    }
//
//    public void loadActiveCallFragment(String accountID, int callID) {
//
//        ActiveCallFragment activeCallFragment = new ActiveCallFragment(accountID, callID, true);
//        getSupportFragmentManager()
//                .beginTransaction()
//                .addToBackStack("activeCall")
//                .setReorderingAllowed(true)
//                .replace(R.id.main_fragment_container, activeCallFragment, null).commit();
//
//    }

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
