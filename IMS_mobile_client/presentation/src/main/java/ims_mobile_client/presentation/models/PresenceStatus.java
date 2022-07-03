package ims_mobile_client.presentation.models;

public class PresenceStatus {
    private String presenceStatusType;
    private String presenceStatusActivity;
    private String presenceStatusText;
    private String presenceNote;

    public PresenceStatus() {
        presenceStatusType = "NOT_SET";
        presenceStatusActivity = "NOT_SET";
        presenceStatusText = "NOT_SET";
        presenceNote = "NOT_SET";
    }

    public PresenceStatus(String presenceStatusType, String presenceStatusActivity, String presenceStatusText, String presenceNote) {
        this.setPresenceStatusType(presenceStatusType);
        this.setPresenceStatusActivity(presenceStatusActivity);
        this.setPresenceStatusText(presenceStatusText);
        this.setPresenceNote(presenceNote);
    }

    public String getPresenceStatusType() {
        return presenceStatusType;
    }

    public void setPresenceStatusType(String presenceStatusType) {
        this.presenceStatusType = presenceStatusType;
    }

    public String getPresenceStatusActivity() {
        return presenceStatusActivity;
    }

    public void setPresenceStatusActivity(String presenceStatusActivity) {
        this.presenceStatusActivity = presenceStatusActivity;
    }

    public String getPresenceStatusText() {
        return presenceStatusText;
    }

    public void setPresenceStatusText(String presenceStatusText) {
        this.presenceStatusText = presenceStatusText;
    }

    public String getPresenceNote() {
        return presenceNote;
    }

    public void setPresenceNote(String presenceNote) {
        this.presenceNote = presenceNote;
    }
}
