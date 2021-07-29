package com.example.ims_mobile_client.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class SipAccountDataEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String accountID;
    public String userDisplayName;
    public String username;
    public String password;
    public String realm;
    public String host;
    public long port;
    public boolean tcpTransport;
    public String authenticationType;
    public String contactUriParams;
    public int regExpirationTimeout;
    public String guestDisplayName;
    public String callId;
}
