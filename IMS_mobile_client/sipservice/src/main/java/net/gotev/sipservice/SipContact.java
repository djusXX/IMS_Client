package net.gotev.sipservice;

import android.os.Build;
import android.widget.EditText;

import org.pjsip.pjsua2.Buddy;
import org.pjsip.pjsua2.BuddyConfig;
import org.pjsip.pjsua2.BuddyInfo;
import org.pjsip.pjsua2.OnBuddyEvSubStateParam;
import org.pjsip.pjsua2.PresenceStatus;
import org.pjsip.pjsua2.SendInstantMessageParam;
import org.pjsip.pjsua2.SendTypingIndicationParam;
import org.pjsip.pjsua2.SipTxOption;

import java.time.Instant;

public class SipContact extends Buddy implements Comparable<SipContact> {

    private static final String LOG_TAG = SipContact.class.getSimpleName();

    private final SipService service;
    private SipAccount account;
    private SipContactConfig cfg;
    private long lastActivity; // last in/out message or call
//    private String displayName;
    private PresenceStatus presenceStatus = new PresenceStatus();
    private SipContactInfo contactInfo = new SipContactInfo();

    protected SipContact(SipService service, SipAccount acc, SipContactConfig cfg) {
        super();
        this.service = service;
//        this.displayName = displayName;
        this.account = acc;
        this.cfg = cfg;
    }

    public void create() throws Exception {
        create(account, cfg);
    }

    public SipAccount getAccount() { return account; }

    public BuddyConfig getConfig() { return cfg; }

//    public String getDisplayName() { return displayName; }

    public long getLastActivity() { return lastActivity; }

    public boolean isValid() { return isValid(); }

    public SipContactInfo getInfo() {
        return getInfo();
    }

    public void setSubscribe(boolean subscribe) throws Exception {
        Logger.debug(LOG_TAG, "Changing subscription state of " + account.getData().getGuestDisplayName() + " to: " + subscribe);
        subscribePresence(subscribe);
    }

    public void updateStatus() throws Exception {
        updatePresence();
    }

    public void sendMessage(SipMessage msg) throws Exception {
        SendInstantMessageParam prm = new SendInstantMessageParam();
        prm.setContentType(msg.type);
        prm.setContent(msg.content);
        prm.setTxOption(msg.txOption);
        prm.setUserData(msg.userData);
        sendInstantMessage(prm);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            lastActivity = Instant.now().getEpochSecond();
        }
    }

    @Override
    public void onBuddyState() {
        service.getBroadcastEmitter().onBuddyState(account.getData().getIdUri(), cfg.getUri(), presenceStatus);
    }

    @Override
    public void onBuddyEvSubState(OnBuddyEvSubStateParam prm) {
        super.onBuddyEvSubState(prm);
    }

    public void notifyTyping(boolean isTyping, SipTxOption txOption) throws Exception {
        SendTypingIndicationParam prm = new SendTypingIndicationParam();
        prm.setIsTyping(isTyping);
        prm.setTxOption(txOption);
        sendTypingIndication(prm);
    }

    @Override
    public int compareTo(SipContact that) {
        return Long.compare(that.lastActivity, this.lastActivity);
    }
}
