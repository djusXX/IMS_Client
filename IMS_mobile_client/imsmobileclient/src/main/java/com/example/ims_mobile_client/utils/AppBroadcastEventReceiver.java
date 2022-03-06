package com.example.ims_mobile_client.utils;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.ims_mobile_client.R;
import com.example.ims_mobile_client.data.entities.BuddyEntity;
import com.example.ims_mobile_client.ui.BuddyListFragment;
import com.example.ims_mobile_client.ui.MainActivity;
import com.example.ims_mobile_client.view_models.BuddyViewModel;

import net.gotev.sipservice.BroadcastEventReceiver;
import net.gotev.sipservice.Logger;
import net.gotev.sipservice.SipBuddyData;

import org.pjsip.pjsua2.pjsip_status_code;

public class AppBroadcastEventReceiver extends BroadcastEventReceiver {

    private static final String LOG_TAG = "AppBroadcastEventReceiver";

    private MainActivity appContext;

    @Override
    public void register(Context context) {
        Logger.debug(LOG_TAG, "registering " + LOG_TAG);
        super.register(context);
        appContext = (MainActivity) context;
    }

    @Override
    public void unregister(Context context) {
        Logger.debug(LOG_TAG, "unregistering " + LOG_TAG);
        super.unregister(context);
        appContext = null;
    }

    @Override
    public void onRegistration(String accountID, int registrationStateCode) {
        super.onRegistration(accountID, registrationStateCode);
        Logger.debug(LOG_TAG, "called onRegistration()");
        if (accountID.isEmpty() && 400 == registrationStateCode) {
            appContext.setAccount();
        } else if (registrationStateCode == pjsip_status_code.PJSIP_SC_OK) {
//            appContext.addBuddyListFragment();
            appContext.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_fragment_container, new BuddyListFragment(), BuddyListFragment.TAG)
                    .commit();
        } else {
            Toast.makeText(appContext, "error: " + registrationStateCode, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onBuddyAdded(String accountID, SipBuddyData buddyData) {
        super.onBuddyAdded(accountID, buddyData);
        BuddyEntity buddyEntity = new BuddyEntity(accountID, buddyData.getSipUri(), buddyData.getDisplayName());
        final BuddyViewModel buddyViewModel = new ViewModelProvider((ViewModelStoreOwner) appContext).get(BuddyViewModel.class);
        buddyViewModel.addBuddy(buddyEntity);
    }
}
