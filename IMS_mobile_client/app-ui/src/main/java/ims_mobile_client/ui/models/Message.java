package ims_mobile_client.ui.models;

public class Message {

    private boolean isIncoming;
    private String timestamp;
    private String content;

    public Message(boolean isIncoming, String timestamp, String content) {
        this.isIncoming = isIncoming;
        this.timestamp = timestamp;
        this.content = content;
    }

    public boolean isIncoming() {
        return isIncoming;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getContent() {
        return content;
    }
}
