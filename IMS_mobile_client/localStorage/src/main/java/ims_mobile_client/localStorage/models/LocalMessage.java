package ims_mobile_client.localStorage.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "messages_table")
public class LocalMessage {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String sipUriFrom;
    private String sipUriTo;
    private long time;
    private String content;


    public LocalMessage(int id, String sipUriFrom, String sipUriTo, long time, String content) {
        this.id = id;
        this.sipUriFrom = sipUriFrom;
        this.sipUriTo = sipUriTo;
        this.time = time;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSipUriFrom() {
        return sipUriFrom;
    }

    public void setSipUriFrom(String sipUriFrom) {
        this.sipUriFrom = sipUriFrom;
    }

    public String getSipUriTo() {
        return sipUriTo;
    }

    public void setSipUriTo(String sipUriTo) {
        this.sipUriTo = sipUriTo;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
