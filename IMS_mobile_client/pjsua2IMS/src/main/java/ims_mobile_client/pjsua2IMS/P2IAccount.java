package ims_mobile_client.pjsua2IMS;

import android.util.Log;

import org.pjsip.pjsua2.Account;
import org.pjsip.pjsua2.AccountConfig;
import org.pjsip.pjsua2.AccountInfo;
import org.pjsip.pjsua2.BuddyConfig;
import org.pjsip.pjsua2.OnIncomingCallParam;
import org.pjsip.pjsua2.OnInstantMessageParam;
import org.pjsip.pjsua2.OnRegStateParam;
import java.util.concurrent.ConcurrentHashMap;

public class P2IAccount extends Account {
    private static final String TAG = P2IAccount.class.getSimpleName();

    private final AccountData accountData;
    private final ConcurrentHashMap<String, P2IBuddy> buddies = new ConcurrentHashMap<>();

    public P2IAccount(AccountData accountData) {
        super();
        this.accountData = accountData;
    }

    public void create() throws Exception {
        create(accountData.getAccountConfig(),true);
    }

    public P2IBuddy addBuddy(BuddyConfig buddyConfig) {
        P2IBuddy buddy = new P2IBuddy(buddyConfig);

        try {
            buddy.create(this, buddyConfig);
            if (buddyConfig.getSubscribe()) {
                buddy.subscribePresence(true);
            }
        } catch (Exception e) {
            Log.e(TAG, "FAILED to create/add buddy: " + e);
            return null;
        }

        buddies.put(buddyConfig.getUri(), buddy);
        return buddy;
    }

    public void removeBuddy(String buddyUri) {
        buddies.remove(buddyUri);
        P2IBuddy buddy = buddies.get(buddyUri);
        buddies.remove(buddyUri);
        if (buddy != null) {
            buddy.delete();
        }
    }
    
    public AccountInfo getUpdatedAccountInfo() {
        AccountInfo accountInfo = null;
        try {
            accountInfo = this.getInfo();
        } catch (Exception e) {
            Log.e(TAG, "Account.getInfo() FAILED: " + e);
        }
        return accountInfo;
    }
    
    @Override
    public void onRegState(OnRegStateParam prm) {
        Log.d(TAG, "Called method onRegState()");
        Log.d(TAG, "code: " + prm.getCode());
        Log.d(TAG, "reason: " + prm.getReason());
        Log.d(TAG, "expiration: " + prm.getExpiration());
        // TODO: notify
    }

    @Override
    public void onIncomingCall(OnIncomingCallParam prm) {
        Log.d(TAG, "Called method onIncomingCall()");
//        P2ICall call = new P2ICall(this, prm.getCallId(), );
        // TODO: implement and notify!!!
    }

    @Override
    public void onInstantMessage(OnInstantMessageParam prm) {
        Log.d(TAG, "======== Incoming pager ======== ");
        Log.d(TAG, "From     : " + prm.getFromUri());
        Log.d(TAG, "To       : " + prm.getToUri());
        Log.d(TAG, "Contact  : " + prm.getContactUri());
        Log.d(TAG, "Mimetype : " + prm.getContentType());
        Log.d(TAG, "Body     : " + prm.getMsgBody());
        // TODO: handle new Msg!!!
    }
}
