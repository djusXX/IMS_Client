package ims_mobile_client.domain.models;

public class Buddy {

    private int id;
    private String usrSipUri;
    private String buddySipUri;
    private String buddyDisplayName;

    private PresenceStatus presenceStatus = null;

    public Buddy(int id, String usrSipUri, String buddySipUri, String buddyDisplayName) {
        this.id = id;
        this.usrSipUri = usrSipUri;
        this.buddySipUri = buddySipUri;
        this.buddyDisplayName = buddyDisplayName;
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

    public PresenceStatus getPresenceState() {
        return presenceStatus;
    }

    public void updatePresenceState(PresenceStatus presenceStatus) {
        this.presenceStatus = presenceStatus;
    }
}
