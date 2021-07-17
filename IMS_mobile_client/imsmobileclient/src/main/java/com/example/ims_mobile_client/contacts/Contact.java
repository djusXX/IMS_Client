package com.example.ims_mobile_client.contacts;

public class Contact {
    private String name = "contact_name";
    String sipUri;

    public Contact(String name) {
        this.name = name;
    }

    public Contact(String name, String sipUri) {
        this.name = name;
        this.sipUri = sipUri;
    }

    public String getName() {
        return this.name;
    }
}
