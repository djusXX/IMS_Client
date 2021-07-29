package com.example.ims_mobile_client.data.entities;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class SipContactEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String contactDisplayName;

    public String contactSipUri;

    @Embedded
    public SipAccountDataEntity accountData;

    @Embedded
    public SipContactInfoEntity sipPresenceStatus;


}
