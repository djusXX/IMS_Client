package net.gotev.sipservice;

import org.pjsip.pjsua2.BuddyConfig;

public class SipContactConfig extends BuddyConfig {
    protected String uri;
    protected boolean subscribe;

    public SipContactConfig() {
        super();
    }

    public SipContactConfig(String uri, boolean subscribe) {
        super();
        setUri(uri);
        setSubscribe(subscribe);
    }

    @Override
    public String getUri() {
        return uri;
    }

    @Override
    public boolean getSubscribe() {
        return subscribe;
    }
}


