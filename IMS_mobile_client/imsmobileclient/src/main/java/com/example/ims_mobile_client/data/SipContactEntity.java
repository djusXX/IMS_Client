package com.example.ims_mobile_client.data;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
public class SipContactEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String localUserAccountId;
    public String contactDisplayName;
    public String contactSipUri;
}
