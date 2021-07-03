package com.example.ims_mobile_client;

import android.content.Context;
import android.view.View;
import android.widget.EditText;

import net.gotev.sipservice.SipAccountData;
import net.gotev.sipservice.SipServiceCommand;

public class User {
    private String name;
    private String password;
    private String realm;
    private String pcscfHost;
    private long pcscfPort;
    private TransportType transport = TransportType.UDP;
    private AuthType authentication = AuthType.digest;
    private String additionalParams;
    private int registerTimeout = 300; // seconds

    public User() {
        // ONLY FOR DEBUGGING !!!!!!!!!!!!!!!
        this.name = "bob";
        this.password = "bob";
        this.realm = "open-ims.test";
        this.pcscfHost = "10.0.0.9";
        this.pcscfPort = 4060;
    }

    public User(View view) {
        EditText et = (EditText) view.findViewById(R.id.NameText);
        this.name = et.getText().toString();

        et = (EditText) view.findViewById(R.id.PasswordText);
        this.password = et.getText().toString();

        et = (EditText) view.findViewById(R.id.RealmText);
        this.realm = et.getText().toString();

        et = (EditText) view.findViewById(R.id.PCSCFHostText);
        this.pcscfHost = et.getText().toString();

        et = (EditText) view.findViewById(R.id.PCSCFPortText);
        try {
            this.pcscfPort = Integer.parseUnsignedInt(et.getText().toString());
        } catch (Exception e) {
            this.pcscfPort = 9999;
        }
    }
    
    public void logIn(Context context) {
        SipAccountData sad = toSAD();
        SipServiceCommand.setAccount(context, sad);
    }

    private SipAccountData toSAD() {
        SipAccountData sad = new SipAccountData();


        return sad;
    }
}
