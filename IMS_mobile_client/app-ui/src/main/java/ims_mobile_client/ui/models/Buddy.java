package ims_mobile_client.ui.models;

public class Buddy {

    private String sipUri;
    private String displayName;
    private String statusType;
    private String statusText;

    public Buddy(String sipUri, String displayName, String statusType, String statusText) {
        this.sipUri = sipUri;
        this.displayName = displayName;
        this.statusType = statusType;
        this.statusText = statusText;
    }

    public String getSipUri() {
        return sipUri;
    }

    public void setSipUri(String sipUri) {
        this.sipUri = sipUri;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getStatusType() {
        return statusType;
    }

    public void setStatusType(String statusType) {
        this.statusType = statusType;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }
}
