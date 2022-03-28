package com.example.repository.data.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "messages_table")
public class MessageEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public String sip_uri_FROM;

    @NonNull
    public String sip_uri_TO;

    @NonNull
    public String timestamp;    // time of sending/receiving the message

    @NonNull
    public String content;

    public MessageEntity(String sip_uri_FROM, String sip_uri_TO, String timestamp, String content) {
        this.sip_uri_FROM = sip_uri_FROM;
        this.sip_uri_TO = sip_uri_TO;
        this.timestamp = timestamp;
        this.content = content;
    }
}
