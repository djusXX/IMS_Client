package ims_mobile_client.domain.models;

public class PresenceState {
    public final String presenceStatusType;
    public final String presenceStatusActivity;
    public final String presenceStatusText;
    public final String presenceNote;

    public PresenceState(String presenceStatusType, String presenceStatusActivity, String presenceStatusText, String presenceNote) {
        this.presenceStatusType = presenceStatusType;
        this.presenceStatusActivity = presenceStatusActivity;
        this.presenceStatusText = presenceStatusText;
        this.presenceNote = presenceNote;
    }
}
