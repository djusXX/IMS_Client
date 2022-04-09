package com.example.domain.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "messages_table")
public class MessageEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public String sipUriFROM;

    @NonNull
    public String sipUriTO;

    @NonNull
    public String timestamp;    // time of sending/receiving the message

    @NonNull
    public String content;

    public MessageEntity(String sipUriFROM, String sipUriTO, String timestamp, String content) {
        this.sipUriFROM = sipUriFROM;
        this.sipUriTO = sipUriTO;
        this.timestamp = timestamp;
        this.content = content;
    }
}
