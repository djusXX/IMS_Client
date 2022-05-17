package ims_mobile_client.pjsua2IMS;

import android.util.Log;

import org.pjsip.pjsua2.Account;
import org.pjsip.pjsua2.Buddy;
import org.pjsip.pjsua2.BuddyConfig;
import org.pjsip.pjsua2.BuddyInfo;
import org.pjsip.pjsua2.SendInstantMessageParam;

public class P2IBuddy extends Buddy {
    private static final String TAG = P2IBuddy.class.getSimpleName();

    private BuddyConfig buddyConfig;

    public P2IBuddy(BuddyConfig buddyConfig) {
        super();
        this.buddyConfig = buddyConfig;
    }

    public BuddyInfo getUpdatedBuddyInfo() {
        BuddyInfo buddyInfo = null;
        try {
            buddyInfo = this.getInfo();
        } catch (Exception e) {
            Log.e(TAG, "Buddy.getInfo() FAILED: " + e);
        }
        return buddyInfo;
    }

    @Override
    public void sendInstantMessage(SendInstantMessageParam prm) throws Exception {
        // TODO: implement!!!
    }

    @Override
    public void onBuddyState() {
        // TODO: implement!!!
    }
}
