package com.example.ims_mobile_client.data.entities;


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
    public String sip_uri_FROM;

    @NonNull
    public String sip_uri_TO;

    @NonNull
    public boolean is_video;

    @NonNull
    public String begin_timestamp;

    @NonNull
    public String end_timestamp;

    public CallEntity(String sip_uri_FROM, String sip_uri_TO, boolean is_video, String begin_timestamp, String end_timestamp) {
        this.sip_uri_FROM = sip_uri_FROM;
        this.sip_uri_TO = sip_uri_TO;
        this.is_video = is_video;
        this.begin_timestamp = begin_timestamp;
        this.end_timestamp = end_timestamp;
    }
}
