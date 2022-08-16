package ims_mobile_client.domain.models;

import java.util.Date;

public class Message {

    private int id;
    private String sipUriFrom;
    private String sipUriTo;
    private String type;
    private long time;
    private String content;

    public Message(int id, String sipUriFrom, String sipUriTo, String type, long time, String content) {
        this.id = id;
        this.sipUriFrom = sipUriFrom;
        this.sipUriTo = sipUriTo;
        this.type = type;
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

    public String getTimeString() {
        return new Date(time).toString();
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
