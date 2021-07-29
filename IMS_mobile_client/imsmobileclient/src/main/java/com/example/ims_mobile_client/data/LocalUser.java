package com.example.ims_mobile_client.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class LocalUser {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String accountID;

    public String displayName;

}
