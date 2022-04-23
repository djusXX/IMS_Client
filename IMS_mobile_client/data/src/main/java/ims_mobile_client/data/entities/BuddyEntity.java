package ims_mobile_client.data.entities;

public class BuddyEntity {

    private int id;
    private String userSipUri;
    private String buddySipUri;
    private String buddyDisplayName;

    public BuddyEntity(int id, String userSipUri, String buddySipUri, String buddyDisplayName) {
        this.id = id;
        this.userSipUri = userSipUri;
        this.buddySipUri = buddySipUri;
        this.buddyDisplayName = buddyDisplayName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserSipUri() {
        return userSipUri;
    }

    public void setUserSipUri(String userSipUri) {
        this.userSipUri = userSipUri;
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
}
