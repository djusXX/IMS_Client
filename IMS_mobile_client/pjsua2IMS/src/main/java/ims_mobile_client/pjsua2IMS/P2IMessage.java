package ims_mobile_client.pjsua2IMS;

public class P2IMessage {

    private String sipUriFrom;
    private String sipUriTo;
    private long time;
    private String content;

    public P2IMessage(String sipUriFrom, String sipUriTo, long time, String content) {
        this.sipUriFrom = sipUriFrom;
        this.sipUriTo = sipUriTo;
        this.time = time;
        this.content = content;
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
