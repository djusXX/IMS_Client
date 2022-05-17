package ims_mobile_client.pjsua2IMS;

import android.util.Log;

import org.pjsip.pjsua2.Account;
import org.pjsip.pjsua2.Buddy;
import org.pjsip.pjsua2.BuddyConfig;
import org.pjsip.pjsua2.BuddyInfo;
import org.pjsip.pjsua2.SendInstantMessageParam;

public class P2IBuddy extends Buddy {
    private static final String TAG = P2IBuddy.class.getSimpleName();

    private Account account;
    private BuddyConfig buddyConfig;

    public P2IBuddy(Account account, BuddyConfig buddyConfig) {
        super();
        this.account = account;
        this.buddyConfig = buddyConfig;
    }

    public void createBuddy() throws Exception {
        create(account, buddyConfig);
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
        super.sendInstantMessage(prm);
    }

    @Override
    public void onBuddyState() {

    }
}
