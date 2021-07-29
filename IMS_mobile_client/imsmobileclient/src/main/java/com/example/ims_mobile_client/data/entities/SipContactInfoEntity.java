package com.example.ims_mobile_client.data.entities;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class SipContactInfoEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String uri;
    public String name;
    public boolean subEnabled;
    public boolean subState;
    public String subStateName;
    public int subTermCode;
    public String subTermReason;

    @Embedded
    public SipPresenceStatusEntity presenceStatus;
}
