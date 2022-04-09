package com.example.domain.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "local_users_table")
public class UserEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String displayName;

    @NonNull
    public String userName;

    @NonNull
    public String realm;

    @NonNull
    public String pcscf;

    @NonNull
    public String usrSipUri;

    public UserEntity(String displayName, @NonNull String userName, @NonNull String realm, @NonNull String pcscf) {
        this.displayName = displayName;
        this.userName = userName;
        this.realm = realm;
        this.pcscf = pcscf;
        usrSipUri = getSipUri();
    }

    private String getSipUri() {
        return "sip:" + userName + "@" + realm;
    }

}
