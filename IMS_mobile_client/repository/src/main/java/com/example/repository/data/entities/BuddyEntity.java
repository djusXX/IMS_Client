package com.example.repository.data.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;



import net.gotev.sipservice.SipBuddyData;

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

    public String buddy_status_type;

    public String buddy_status_text;

    public BuddyEntity(@NonNull String user_sip_uri, @NonNull String buddy_sip_uri, @NonNull String buddy_display_name) {
        this.user_sip_uri = user_sip_uri;
        this.buddy_sip_uri = buddy_sip_uri;
        this.buddy_display_name = buddy_display_name;
    }

    public BuddyEntity(@NonNull String user_sip_uri, @NonNull SipBuddyData buddyData) {
        this.user_sip_uri = user_sip_uri;
        buddy_sip_uri = buddyData.getBuddyUri();
        buddy_display_name = buddyData.getDisplayName();
    }

}
