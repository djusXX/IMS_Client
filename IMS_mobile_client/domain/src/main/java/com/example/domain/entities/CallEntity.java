package com.example.domain.entities;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 *
 *  Class to keep all in/out calls.
 *
 * */
@Entity(tableName = "calls_table")
public class CallEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public String sipUriFROM;

    @NonNull
    public String sipUriTO;

    public boolean isVideo;

    @NonNull
    public String beginTimestamp;

    @NonNull
    public String endTimestamp;

    public CallEntity(@NonNull String sipUriFROM, @NonNull String sipUriTO, boolean isVideo, @NonNull String beginTimestamp, @NonNull String endTimestamp) {
        this.sipUriFROM = sipUriFROM;
        this.sipUriTO = sipUriTO;
        this.isVideo = isVideo;
        this.beginTimestamp = beginTimestamp;
        this.endTimestamp = endTimestamp;
    }
}
