package ims_mobile_client.pjsua2IMS;

import android.util.Log;

import org.pjsip.pjsua2.Account;
import org.pjsip.pjsua2.Buddy;
import org.pjsip.pjsua2.BuddyConfig;
import org.pjsip.pjsua2.BuddyInfo;
import org.pjsip.pjsua2.PresenceStatus;
import org.pjsip.pjsua2.SendInstantMessageParam;

import java.util.List;

public class P2IBuddy extends Buddy {
    private static final String TAG = P2IBuddy.class.getSimpleName();

    private BuddyConfig buddyConfig;
    private String displayName;
    private PresenceStatus status;
//    private final List<P2IBuddy>

    public P2IBuddy(BuddyConfig buddyConfig) {
        super();
        this.buddyConfig = buddyConfig;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public BuddyInfo getBuddyInfo() {
        BuddyInfo buddyInfo = null;
        try {
            buddyInfo = this.getInfo();
        } catch (Exception e) {
            Log.e(TAG, "Buddy.getInfo() FAILED: " + e);
        }
        return buddyInfo;
    }

    public void sendMessage(String content) {
        SendInstantMessageParam param = new SendInstantMessageParam();
        param.setContent(content);
        try {
            sendInstantMessage(param);
        } catch (Exception e) {
            Log.e(TAG, "Buddy.sendInstantMessage() FAILED: " + e);
        }
    }

    @Override
    public void onBuddyState() {
        status = getBuddyInfo().getPresStatus();
    }

}
