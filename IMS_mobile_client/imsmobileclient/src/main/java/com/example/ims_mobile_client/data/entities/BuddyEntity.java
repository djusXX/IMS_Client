package com.example.ims_mobile_client.data.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "buddies_table")
public class BuddyEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public String user_sip_uri;

    @NonNull
    public String buddy_sip_uri;

    @NonNull
    public String buddy_display_name;

    public BuddyEntity(String user_sip_uri, String buddy_sip_uri, String buddy_display_name) {
        this.user_sip_uri = user_sip_uri;
        this.buddy_sip_uri = buddy_sip_uri;
        this.buddy_display_name = buddy_display_name;
    }
}
