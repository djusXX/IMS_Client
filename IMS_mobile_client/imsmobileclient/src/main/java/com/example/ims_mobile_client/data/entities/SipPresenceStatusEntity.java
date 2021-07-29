package com.example.ims_mobile_client.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class SipPresenceStatusEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public int status;
    public String statusText;
    public int statusActivity;
    public String statusNote;
    public String statusRpidId;
}
