package ims_mobile_client.domain.entities;

public class Buddy {

    private int id;
    private String usrSipUri;
    private String buddySipUri;
    private String buddyDisplayName;
    private String buddyStatusType;
    private String buddyStatusText;

    public Buddy(int id, String usrSipUri, String buddySipUri, String buddyDisplayName, String buddyStatusType, String buddyStatusText) {
        this.id = id;
        this.usrSipUri = usrSipUri;
        this.buddySipUri = buddySipUri;
        this.buddyDisplayName = buddyDisplayName;
        this.buddyStatusType = buddyStatusType;
        this.buddyStatusText = buddyStatusText;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsrSipUri() {
        return usrSipUri;
    }

    public void setUsrSipUri(String usrSipUri) {
        this.usrSipUri = usrSipUri;
    }

    public String getBuddySipUri() {
        return buddySipUri;
    }

    public void setBuddySipUri(String buddySipUri) {
        this.buddySipUri = buddySipUri;
    }

    public String getBuddyDisplayName() {
        return buddyDisplayName;
    }

    public void setBuddyDisplayName(String buddyDisplayName) {
        this.buddyDisplayName = buddyDisplayName;
    }

    public String getBuddyStatusType() {
        return buddyStatusType;
    }

    public void setBuddyStatusType(String buddyStatusType) {
        this.buddyStatusType = buddyStatusType;
    }

    public String getBuddyStatusText() {
        return buddyStatusText;
    }

    public void setBuddyStatusText(String buddyStatusText) {
        this.buddyStatusText = buddyStatusText;
    }
}
