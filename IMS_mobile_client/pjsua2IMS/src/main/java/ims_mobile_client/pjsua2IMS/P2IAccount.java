package ims_mobile_client.pjsua2IMS;

import android.util.Log;

import org.pjsip.pjsua2.Account;
import org.pjsip.pjsua2.AccountInfo;
import org.pjsip.pjsua2.BuddyConfig;
import org.pjsip.pjsua2.CallOpParam;
import org.pjsip.pjsua2.OnIncomingCallParam;
import org.pjsip.pjsua2.OnInstantMessageParam;
import org.pjsip.pjsua2.OnRegStateParam;
import org.pjsip.pjsua2.pjsip_status_code;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import ims_mobile_client.domain.models.PresenceStatus;
import ims_mobile_client.domain.models.StatusType;
import ims_mobile_client.domain.models.UserRegistrationStatus;

public class P2IAccount extends Account {
    private static final String TAG = P2IAccount.class.getSimpleName();

    private final P2IAccountData accountData;
    private final P2IHelperImpl helper;
    private final ConcurrentHashMap<String, P2IBuddy> buddies = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Integer, P2ICall> activeCalls = new ConcurrentHashMap<>();
    private final List<P2IMessage> messages = new ArrayList<>();

    public P2IAccount(P2IAccountData accountData, P2IHelperImpl helper) {
        super();
        this.accountData = accountData;
        this.helper = helper;
    }

    public void create() throws Exception {
        create(accountData.getAccountConfig(),true);
        helper.setLastRegStatus(UserRegistrationStatus.TRYING);
    }

    public void createGuest() throws Exception {
        create(accountData.getGuestAccountConfig());
    }

    public P2IHelperImpl getHelper() {
        return helper;
    }

    public P2IAccountData getAccountData() {
        return accountData;
    }

    public void addBuddy(String buddySipUri, String buddyDisplayName) {
        BuddyConfig buddyConfig = new BuddyConfig();
        buddyConfig.setUri(buddySipUri);
        buddyConfig.setSubscribe(true);

        P2IBuddy buddy = new P2IBuddy(buddyConfig);
        buddy.setDisplayName(buddyDisplayName);

        try {
            buddy.create(this, buddyConfig);
            if (buddyConfig.getSubscribe()) {
                buddy.subscribePresence(true);
            }
        } catch (Exception e) {
            Log.e(TAG, "FAILED to create/add buddy: " + e);
            buddy.delete();
            return;
        }

        buddies.put(buddySipUri, buddy);
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

    public P2ICall addIncomingCall(int callId) {
        P2ICall call = new P2ICall(this, callId);
        activeCalls.put(callId, call);
        return call;
    }

    public void removeCall(int callId) {
        P2ICall call = activeCalls.remove(callId);
        if (call == null) return;

        call.delete();
    }

    public P2ICall getCall(int callId) {
        return activeCalls.get(callId);
    }

    public P2ICall addOutgoingCall(String remoteSipUri, boolean isVideo) {
        if (activeCalls.size() > 0) {
            return null;
        }

        P2ICall call = new P2ICall(this);
        call.setVideoCallParam(isVideo);

        CallOpParam callOpParam = new CallOpParam();
        try {
            call.makeCall(remoteSipUri, callOpParam);
            activeCalls.put(call.getId(), call);
            return call;
        } catch (Exception e) {
            Log.e(TAG, "addOutgoingCall() FAILED: " + e);
        }

        return null;
    }
    
    @Override
    public void onRegState(OnRegStateParam prm) {
        Log.d(TAG, "Called method onRegState()");
        Log.d(TAG, "code: " + prm.getCode());
        Log.d(TAG, "reason: " + prm.getReason());
        Log.d(TAG, "expiration: " + prm.getExpiration());

        if (prm.getExpiration() == 0) helper.setLastRegStatus(UserRegistrationStatus.UNREGISTERED);
        else if (prm.getCode() == 200) helper.setLastRegStatus(UserRegistrationStatus.REGISTERED);
        else helper.setLastRegStatus(UserRegistrationStatus.UNKNOWN);
    }

    @Override
    public void onIncomingCall(OnIncomingCallParam prm) {
        Log.d(TAG, "Called method onIncomingCall()");
        P2ICall call = addIncomingCall(prm.getCallId());

        if (activeCalls.size() > 1) {
            call.sendBusyHereToIncomingCall();
            return;
        }

        try {
            CallOpParam callOpParam = new CallOpParam();
            callOpParam.setStatusCode(pjsip_status_code.PJSIP_SC_RINGING);
            call.answer(callOpParam);
            callOpParam.setStatusCode(pjsip_status_code.PJSIP_SC_OK);
            call.answer(callOpParam);
        } catch (Exception e) {
            Log.d(TAG, "Failed while answering call. ERROR: " + e);
        }
    }

    @Override
    public void onInstantMessage(OnInstantMessageParam prm) {
        Log.d(TAG, "======== Incoming pager ======== ");
        Log.d(TAG, "From     : " + prm.getFromUri());
        Log.d(TAG, "To       : " + prm.getToUri());
        Log.d(TAG, "Contact  : " + prm.getContactUri());
        Log.d(TAG, "Mimetype : " + prm.getContentType());
        Log.d(TAG, "Body     : " + prm.getMsgBody());
        messages.add(new P2IMessage(prm.getFromUri(), prm.getToUri(),
                new Date().getTime(), prm.getMsgBody()));
    }

    public List<P2IMessage> getMessages() {
        return messages;
    }

    public void setPresenceStatus(PresenceStatus presenceStatus) {
        Log.d(TAG, "Called method setPresenceStatus()");
        org.pjsip.pjsua2.PresenceStatus status = new org.pjsip.pjsua2.PresenceStatus();
        status.setStatus(presenceStatus.getPresenceStatusType().ordinal());
        status.setNote(presenceStatus.getPresenceStatusText());
        try {
            setOnlineStatus(status);
        } catch (Exception e) {
            Log.d(TAG, "Failed while setting user presence status. ERROR: " + e);
        }
        Log.d(TAG, "Setting user status successful");
    }
}
