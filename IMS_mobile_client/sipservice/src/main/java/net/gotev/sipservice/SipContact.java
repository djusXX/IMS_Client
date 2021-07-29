package net.gotev.sipservice;

import org.pjsip.pjsua2.Buddy;
import org.pjsip.pjsua2.OnBuddyEvSubStateParam;
import org.pjsip.pjsua2.PresenceStatus;
import org.pjsip.pjsua2.SendInstantMessageParam;
import org.pjsip.pjsua2.SendTypingIndicationParam;
import org.pjsip.pjsua2.SipTxOption;

public class SipContact extends Buddy {

    private static final String LOG_TAG = SipContact.class.getSimpleName();

    private final SipService service;
    private SipAccount account;
    private SipContactConfig cfg;
    private PresenceStatus presenceStatus = new PresenceStatus();
    private SipContactInfo contactInfo = new SipContactInfo();

    protected SipContact(SipService service, SipAccount acc, SipContactConfig cfg) {
        super();
        this.service = service;
        this.account = acc;
        this.cfg = cfg;
    }

    public void create() throws Exception {
        create(account, cfg);
    }

    public SipAccount getAccount() { return account; }

    public SipContactConfig getConfig() { return cfg; }

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

    }

    @Override
    public void onBuddyState() {
        service.getBroadcastEmitter().onSipContactState(contactInfo, presenceStatus);
    }

    @Override
    public void onBuddyEvSubState(OnBuddyEvSubStateParam prm) {
        service.getBroadcastEmitter().onSipContactEvent(prm.getE());
    }

    public void notifyTyping(boolean isTyping, SipTxOption txOption) throws Exception {
        SendTypingIndicationParam prm = new SendTypingIndicationParam();
        prm.setIsTyping(isTyping);
        prm.setTxOption(txOption);
        sendTypingIndication(prm);
    }

}
