package com.example.domain.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;




@Entity(tableName = "buddies_table")
public class BuddyEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public String userSipUri;

    @NonNull
    public String buddySipUri;

    @NonNull
    public String buddyDisplayName;

    public String buddyStatusType;

    public String buddyStatusText;

    public BuddyEntity(@NonNull String userSipUri, @NonNull String buddySipUri, @NonNull String buddyDisplayName) {
        this.userSipUri = userSipUri;
        this.buddySipUri = buddySipUri;
        this.buddyDisplayName = buddyDisplayName;
    }

}
