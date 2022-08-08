package ims_mobile_client.ui.models;

public class MessageInfo {

    private int id;
    private boolean isIncoming;
    private String timestamp;
    private String content;

    public MessageInfo(boolean isIncoming, String timestamp, String content) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
