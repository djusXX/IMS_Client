package net.gotev.sipservice;

import org.pjsip.pjsua2.Buddy;
import org.pjsip.pjsua2.OnBuddyEvSubStateParam;
import org.pjsip.pjsua2.SendInstantMessageParam;
import org.pjsip.pjsua2.SendTypingIndicationParam;
import org.pjsip.pjsua2.SipTxOption;

public class SipContact extends Buddy {

    private static final String LOG_TAG = SipContact.class.getSimpleName();

    private final SipService service;
    private SipContactConfig cfg;


    public SipContact(SipService service, SipContactConfig cfg) {
        super();
        this.service = service;
        this.cfg = cfg;
    }

    public SipContactConfig getConfig() { return cfg; }

    @Override
    public void onBuddyState() {
        Logger.debug(LOG_TAG, "called onBuddyState() of " + cfg.getUri());
        service.getBroadcastEmitter().sipContactState(this);
    }

    @Override
    public void onBuddyEvSubState(OnBuddyEvSubStateParam prm) {
        Logger.debug(LOG_TAG, "called onBuddyEvSubState() of " + cfg.getUri() + ", event type: " + prm.getE().getType());
        Logger.debug(LOG_TAG, "whole message: " + prm.getE().getBody().getTxMsg().getTdata().getWholeMsg());

        service.getBroadcastEmitter().onSipContactEvent(prm.getE());
    }

    public void notifyTyping(boolean isTyping, SipTxOption txOption) throws Exception {
        SendTypingIndicationParam prm = new SendTypingIndicationParam();
        prm.setIsTyping(isTyping);
        prm.setTxOption(txOption);
        sendTypingIndication(prm);
    }

}
