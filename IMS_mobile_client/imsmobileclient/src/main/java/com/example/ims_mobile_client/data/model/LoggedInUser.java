package com.example.ims_mobile_client.data.model;

import android.view.View;
import android.widget.EditText;

import com.example.ims_mobile_client.R;
import com.example.ims_mobile_client.common.AuthType;
import com.example.ims_mobile_client.common.TransportType;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    private String userId;
    private String name;
    private String password;
    private String realm;
    private String pcscfHost;
    private long pcscfPort;
    private TransportType transport = TransportType.UDP;
    private AuthType authentication = AuthType.digest;
    private String additionalParams;
    private int registerTimeout = 300; // seconds

    public LoggedInUser() {
        // ONLY FOR DEBUGGING !!!!!!!!!!!!!!!
        this.name = "bob";
        this.password = "bob";
        this.realm = "open-ims.test";
        this.pcscfHost = "10.0.0.9";
        this.pcscfPort = 4060;
    }

    public LoggedInUser(String username, String password, String realm, String pcscf) {
        this.name = username;
        this.password = password;
        this.realm = realm;
        this.pcscfHost = pcscf.substring(0,pcscf.indexOf(":") - 1);
        String portStr = pcscf.substring(pcscf.indexOf(":") + 1);
        this.pcscfPort = Integer.parseInt(portStr);
    }

    public String getUserId() { return userId; }

    public String getName() { return name; }

    public String getPassword() { return password; }

    public String getRealm() { return realm; }

    public String getPcscf() { return pcscfHost + ":" + pcscfPort; }

}