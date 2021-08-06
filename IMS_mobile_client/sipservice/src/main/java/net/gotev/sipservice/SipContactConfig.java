package net.gotev.sipservice;

import org.pjsip.pjsua2.BuddyConfig;

public class SipContactConfig extends BuddyConfig {
    private String displayName;

    public SipContactConfig() {
        super();
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }


}


