package ims_mobile_client.presentation.models;

import ims_mobile_client.domain.models.PresenceStatus;

public class BuddyInfo {

    private String buddySipUri;
    private String buddyDisplayName;
    private PresenceStatus presenceStatus;

    public BuddyInfo(String buddySipUri, String buddyDisplayName) {
        this.buddySipUri = buddySipUri;
        this.buddyDisplayName = buddyDisplayName;
        this.presenceStatus = new PresenceStatus();
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

    public PresenceStatus getPresenceStatus() {
        return presenceStatus;
    }

    public void setPresenceStatus(PresenceStatus presenceStatus) {
        this.presenceStatus = presenceStatus;
    }
}
