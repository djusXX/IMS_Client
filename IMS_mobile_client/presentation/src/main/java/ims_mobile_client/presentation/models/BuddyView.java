package ims_mobile_client.presentation.models;

public class BuddyView {

    private int id;
    private String usrSipUri;
    private String buddySipUri;
    private String buddyDisplayName;

    // Buddy Presence
    private String buddyStatusType;
    private String buddyStatusActivity;
    private String buddyStatusText;
    private String note;
    private String rpidId;

    public BuddyView(int id, String usrSipUri, String buddySipUri, String buddyDisplayName, String buddyStatusType, String buddyStatusActivity, String buddyStatusText, String note, String rpidId) {
        this.id = id;
        this.usrSipUri = usrSipUri;
        this.buddySipUri = buddySipUri;
        this.buddyDisplayName = buddyDisplayName;
        this.buddyStatusType = buddyStatusType;
        this.buddyStatusActivity = buddyStatusActivity;
        this.buddyStatusText = buddyStatusText;
        this.note = note;
        this.rpidId = rpidId;
    }

    public BuddyView(int id, String usrSipUri, String buddySipUri, String buddyDisplayName) {
        this.id = id;
        this.usrSipUri = usrSipUri;
        this.buddySipUri = buddySipUri;
        this.buddyDisplayName = buddyDisplayName;
        this.buddyStatusType = null;
        this.buddyStatusActivity = null;
        this.buddyStatusText = null;
        this.note = null;
        this.rpidId = null;
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

    public String getBuddyStatusActivity() {
        return buddyStatusActivity;
    }

    public void setBuddyStatusActivity(String buddyStatusActivity) {
        this.buddyStatusActivity = buddyStatusActivity;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getRpidId() {
        return rpidId;
    }

    public void setRpidId(String rpidId) {
        this.rpidId = rpidId;
    }
}
