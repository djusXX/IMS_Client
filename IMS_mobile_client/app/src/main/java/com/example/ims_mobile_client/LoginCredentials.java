package com.example.ims_mobile_client;

import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class LoginCredentials {
    public String displayName;
    public String publicIdentity;
    public String privateIdentity;
    public String password;
    public String realm;
    public String proxyCSCFHost;
    public int proxyCSCFPort;

    public LoginCredentials() {
        this.displayName = "bob";
        this.publicIdentity = "sip:bob@open-ims.test";
        this.privateIdentity = "bob@open-ims.test";
        this.password = "bob";
        this.realm = "sip:open-ims.test";
        this.proxyCSCFHost = "10.0.0.9";
        this.proxyCSCFPort = 4060;
    }


    public LoginCredentials(View view) {
        EditText et = (EditText) view.findViewById(R.id.DisplayNameText);
        this.displayName = et.getText().toString();

        et = (EditText) view.findViewById(R.id.PublicIdentityText);
        this.publicIdentity = et.getText().toString();

        et = (EditText) view.findViewById(R.id.PrivateIdentityText);
        this.privateIdentity = et.getText().toString();

        et = (EditText) view.findViewById(R.id.PasswordText);
        this.password = et.getText().toString();

        et = (EditText) view.findViewById(R.id.RealmText);
        this.realm = et.getText().toString();

        et = (EditText) view.findViewById(R.id.ProxyCSCFHostText);
        this.proxyCSCFHost = et.getText().toString();

        et = (EditText) view.findViewById(R.id.ProxyCSCFPortText);
        try {
            this.proxyCSCFPort = Integer.parseUnsignedInt(et.getText().toString());
        } catch (Exception e) {
            this.proxyCSCFPort = 9999;
        }

    }
}
