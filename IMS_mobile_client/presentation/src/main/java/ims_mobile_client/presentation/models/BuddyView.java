package ims_mobile_client.presentation.models;

public class BuddyView {

    private int id;
    private String usrSipUri;
    private String buddySipUri;
    private String buddyDisplayName;

    public BuddyView(String usrSipUri, String buddySipUri, String buddyDisplayName) {
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


}
