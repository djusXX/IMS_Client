package com.example.ims_mobile_client.contacts;

import net.gotev.sipservice.SipAccount;

import org.pjsip.pjsua2.Buddy;
import org.pjsip.pjsua2.BuddyConfig;

public class Contact extends Buddy implements Comparable<Contact> {
    SipAccount account;
    BuddyConfig cfg;
    private long lastActivityTimeStamp; // in/out message or call
    private String name = "contact_name";


    public Contact(SipAccount acc, BuddyConfig cfg) {
        this.account = acc;
        this.cfg = cfg;
    }

    public Contact create() {
        return null;
    }


    public Contact(String name) {
        this.name = name;
    }

    public Contact(String name, long lastActivityTimeStamp) {
        this.name = name;
        this.lastActivityTimeStamp = lastActivityTimeStamp;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public int compareTo(Contact that) {
        return Long.compare(that.lastActivityTimeStamp, this.lastActivityTimeStamp);
    }
}
